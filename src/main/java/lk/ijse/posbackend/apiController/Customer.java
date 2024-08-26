package lk.ijse.posbackend.apiController;


import jakarta.json.JsonException;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.posbackend.bo.BOFactory;
import lk.ijse.posbackend.bo.custom.CustomerBO;
import lk.ijse.posbackend.dto.CustomerDto;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/customer")
public class Customer extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling customer doPost method");
        if (!request.getContentType().toLowerCase().startsWith("application/json") || request.getContentType() == null) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        try (var writer = response.getWriter()){

            System.out.println("calling customer save method");

            CustomerDto customerDTO = jsonb.fromJson(request.getReader(), CustomerDto.class);
            System.out.println(customerDTO);
            boolean result = customerBO.saveCustomer(customerDTO);

            if (result) {
                writer.write("Customer saved successfully");
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer save failed");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(String.valueOf(e));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling customer get method");

        String all = request.getParameter("all");
        String nextid = request.getParameter("nextid");



        if (all != null) {
            try (var writer = response.getWriter()) {
                writer.write(jsonb.toJson(customerBO.getAllCustomers()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
        }else if (nextid != null) {
            try (var writer = response.getWriter()) {
                writer.write(jsonb.toJson(customerBO.generateCustomerId()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
        }




    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling customer put method");
        if (!request.getContentType().toLowerCase().startsWith("application/json") || request.getContentType() == null) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        try (var writer = response.getWriter()){
        CustomerDto customerDTO = jsonb.fromJson(request.getReader(), CustomerDto.class);
            System.out.println(customerDTO);
            boolean isUpdate = customerBO.updateCustomer(customerDTO);

            if (isUpdate) {
                writer.write("Customer successfully updated");

                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer not updated");

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (var writer = resp.getWriter()){



            String id = req.getParameter("id");
            boolean isDelete = customerBO.deleteCustomer(id);

            if (isDelete) {
                writer.write("Customer deleted successfully");

                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer deletion failed");

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }


}
