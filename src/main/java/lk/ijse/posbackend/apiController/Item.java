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
import lk.ijse.posbackend.bo.custom.ItemBO;
import lk.ijse.posbackend.dto.CustomerDto;
import lk.ijse.posbackend.dto.ItemDto;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/item")
public class Item extends HttpServlet {

    JsonbConfig config = new JsonbConfig().withFormatting(true);
    Jsonb jsonb = JsonbBuilder.create(config);
    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling Item doPost method");
        if (!request.getContentType().toLowerCase().startsWith("application/json") || request.getContentType() == null) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }
        try (var writer = response.getWriter()){

            System.out.println("calling item save method");

            ItemDto itemDto = jsonb.fromJson(request.getReader(), ItemDto.class);
            System.out.println(itemDto);
            boolean result = itemBO.saveItem(itemDto);

            if (result) {
                writer.write("item saved successfully");
                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("item save failed");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(String.valueOf(e));
        }

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling Item get method");

        String all = request.getParameter("all");
        String nextid = request.getParameter("nextid");

        if (all != null) {
            try (var writer = response.getWriter()) {
                writer.write(jsonb.toJson(itemBO.getAllItem()));
            } catch (JsonException | SQLException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
        }else if (nextid != null) {
            try (var writer = response.getWriter()) {
                writer.write(jsonb.toJson(itemBO.generateItemId()));
                System.out.println(itemBO.generateItemId());
            } catch (JsonException | SQLException | ClassNotFoundException e) {

                throw new RuntimeException(e);
            }
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("calling Item put method");
        if (!request.getContentType().toLowerCase().startsWith("application/json") || request.getContentType() == null) {
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        }

        try (var writer = response.getWriter()){

             ItemDto itemDTO = jsonb.fromJson(request.getReader(), ItemDto.class);
            System.out.println(itemDTO);
            boolean isUpdate = itemBO.updateItem(itemDTO);

            if (isUpdate) {
                writer.write("Item updated successfully");

                response.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item not updated");

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("calling Item delete method");
        try (var writer = resp.getWriter()){



            String id = req.getParameter("id");
            System.out.println(id);
            boolean isDelete = itemBO.deleteItem(id);


            if (isDelete) {
                writer.write("Item successfully deleted");

                resp.setStatus(HttpServletResponse.SC_CREATED);
            } else {
                writer.write("Item Delete failed");

                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (JsonException | SQLException | ClassNotFoundException e) {

            throw new RuntimeException(e);
        }
    }
}




