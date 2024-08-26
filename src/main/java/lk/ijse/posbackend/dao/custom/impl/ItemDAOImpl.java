package lk.ijse.posbackend.dao.custom.impl;

import lk.ijse.posbackend.dao.SQLUtil;
import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM item");

        ArrayList<Item> getallItem = new ArrayList<>();

        while (rst.next()) {
            Item item = new Item(rst.getString("id"), rst.getString("name"), rst.getDouble("unit_price"), rst.getInt("qty"));
            getallItem.add(item);
        }

        return getallItem;
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        System.out.println("calling item save DAO method");
        return SQLUtil.execute("INSERT INTO item (id, name, unit_price, qty) VALUES (?,?,?,?)",
                entity.getId(),entity.getName(),entity.getUnitPrice(),entity.getQtyOnHand());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        System.out.println("calling DAO Item update DAO method " );
        return SQLUtil.execute("UPDATE item SET name=?, unit_price=?, qty=? WHERE id=?",
                entity.getName(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE id=?",id);
    }

    @Override
    public String generateID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM item ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }
}
