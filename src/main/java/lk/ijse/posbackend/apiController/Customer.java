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
        System.out.println("calling customer get method");
        if (!request.getContentType().toLowerCase().startsWith("application/json") || request.getContentType() == null) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        try (var writer = response.getWriter()){

            System.out.println("Inside customer post method");

            CustomerDto customerDTO = jsonb.fromJson(request.getReader(), CustomerDto.class);
            System.out.println(customerDTO);
            boolean isSave = customerBO.saveCustomer(customerDTO);

            if (isSave) {
                writer.write("Customer saved successfully");
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Customer not saved");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(String.valueOf(e));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling customer get method");



    }


}
