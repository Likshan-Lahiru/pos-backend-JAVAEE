package lk.ijse.posbackend.bo.custom.impl;

import lk.ijse.posbackend.bo.custom.ItemBO;
import lk.ijse.posbackend.dao.DAOFactory;
import lk.ijse.posbackend.dao.custom.ItemDAO;
import lk.ijse.posbackend.dto.CustomerDto;
import lk.ijse.posbackend.dto.ItemDto;
import lk.ijse.posbackend.entity.Customer;
import lk.ijse.posbackend.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<ItemDto> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> allItem = itemDAO.getAll();
        ArrayList<ItemDto> itemsDto = new ArrayList<>();

        for (Item item : allItem) {
            itemsDto.add(new ItemDto(item.getId(),item.getName(),item.getUnitPrice(),item.getQtyOnHand()));
        }

        return itemsDto;
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public  boolean saveItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        System.out.println("calling item save bo method");
        return itemDAO.save(new Item(dto.getId(), dto.getName(), dto.getUnitPrice(), dto.getQty()));
    }

    @Override
    public boolean updateItem(ItemDto dto) throws SQLException, ClassNotFoundException {
        System.out.println("calling bo update item " );
        return itemDAO.update(new Item(dto.getId(), dto.getName(), dto.getUnitPrice(), dto.getQty()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateID();
    }

    @Override
    public ItemDto search(String newItemCode) throws SQLException, ClassNotFoundException {
        System.out.println("calling bo search bo method ");
        Item item = itemDAO.search(newItemCode);
        return new ItemDto(item.getId(), item.getName(), item.getUnitPrice(), item.getQtyOnHand());
    }


}
