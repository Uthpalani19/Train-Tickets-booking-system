package dao;

import dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOFactory(){}
    public static DAOFactory getInstance(){
        return (daoFactory==null)?(daoFactory=new DAOFactory()):daoFactory;
    }
    public enum DAOType{
       LOGIN,CUSTOMER,DRIVER,PAYMENT,RESERVATION,SCHEDULE,TRAIN,QUERY
    }
    public <T extends SuperDAO>T getDAO(DAOFactory.DAOType DAOType){
        switch (DAOType){
            case LOGIN:return (T)new loginDAOImpl();
            case CUSTOMER: return (T)new customerDAOImpl();
            case DRIVER:return (T)new driverDAOImpl();
            case PAYMENT:return (T)new paymentDAOImpl();
            case RESERVATION:return (T)new reservationDAOImpl();
            case SCHEDULE:return (T)new scheduleDAOImpl();
            case TRAIN:return (T)new trainDAOImpl();
            case QUERY:return (T)new QueryDAOImpl();
            default:return null;
        }
    }
}
