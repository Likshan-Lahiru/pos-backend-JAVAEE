package lk.ijse.posbackend.dao;

import lk.ijse.posbackend.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.posbackend.dao.custom.impl.ItemDAOImpl;
import lk.ijse.posbackend.dao.custom.impl.OrderDAOImpl;
import lk.ijse.posbackend.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDER_DETAIL
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAIL:
                return new OrderDetailsDAOImpl();
            default:
                return null;
        }
    }
}
