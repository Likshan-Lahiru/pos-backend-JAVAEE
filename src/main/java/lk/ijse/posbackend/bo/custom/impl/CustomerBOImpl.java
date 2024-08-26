package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.CustomerBO;
import lk.ijse.posbackend.dao.DAOFactory;
import lk.ijse.posbackend.dao.custom.CustomerDAO;
import lk.ijse.posbackend.dto.CustomerDto;
import lk.ijse.posbackend.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : allCustomers) {
            customerDTOS.add(new CustomerDto(customer.getId(), customer.getName(), customer.getAddress(), customer.getContact()));
        }

        return customerDTOS;
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {

        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getContact()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateID();
    }
}
