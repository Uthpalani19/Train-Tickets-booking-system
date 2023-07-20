package bo;

import bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){}
    public static BOFactory getInstance(){
        return (boFactory==null)?(boFactory=new BOFactory()):boFactory;
    }
    public enum BOType{
       LOGIN,CUSTOMER,DRIVER,PAYMENT,RESERVATION,SCHEDULE,TRAIN
    }
    public <T>T getBO(BOType boType){
        switch (boType){
            case LOGIN:return (T)new loginBOImpl();
            case CUSTOMER: return (T)new customerBOImpl();
            case DRIVER:return (T)new driverBOImpl();
            case PAYMENT:return (T)new paymentBOImpl();
            case RESERVATION:return (T)new reservationBOImpl();
            case SCHEDULE:return (T)new scheduleBOImpl();
            case TRAIN:return (T)new trainBOImpl();
            default:return null;
        }
    }
}
