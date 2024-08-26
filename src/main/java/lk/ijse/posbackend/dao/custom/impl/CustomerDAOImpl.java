package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.SQLUtil;
import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> getAllCustomer = new ArrayList<>();

        while (rst.next()) {
            Customer entity = new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getString("contact"));
            System.out.println(entity);
            getAllCustomer.add(entity);

        }

        return getAllCustomer;
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer (id, name, address, contact) VALUES (?,?,?,?)",
                customer.getId(),customer.getName(),customer.getAddress(),customer.getContact());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET name=?, address=?, contact=? WHERE id=?",
                entity.getName(),entity.getAddress(), entity.getContact(), entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT id FROM customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int generatedCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", generatedCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE id=?",id + "");
        rst.next();
        Customer entity = new Customer(id + "", rst.getString("name"), rst.getString("address"), rst.getString("contact"));

        return entity;
    }


}
