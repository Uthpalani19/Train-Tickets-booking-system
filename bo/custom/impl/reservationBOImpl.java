package bo.custom.impl;

import bo.custom.reservationBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.paymentDAO;
import dao.custom.reservationDAO;
import dao.custom.scheduleDAO;
import db.dbconnection;
import dto.custom.reservationDTO;
import dto.scheduleDTO;
import dto.trainDTO;
import entity.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class reservationBOImpl implements reservationBO {
    reservationDAO reservationdao=DAOFactory.getInstance().getDAO(DAOFactory.DAOType.RESERVATION);
    paymentDAO paymentdao=DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    scheduleDAO scheduledao=DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    public ArrayList<String> getDestinations() throws SQLException, ClassNotFoundException {
        return qdao.getDestination();
    }

    @Override
    public trainDTO getTrainDetails(String s) throws SQLException, ClassNotFoundException {
        train train=qdao.getTrainDetails(s);
        return new trainDTO(
               train.getTrainID(),
               train.getTrainName(),
               train.getDestination(),
               train.getFirstPrice(),
               train.getSecondPrice(),
               train.getThirdPrice()
        );
    }

    @Override
    public ArrayList<scheduleDTO> getTableDetails(String date, String id) throws SQLException, ClassNotFoundException {
        List<schedule> sList=qdao.getTableDetails(date,id);
        ArrayList<scheduleDTO> dtoList=new ArrayList();
        for(schedule s:sList){
            dtoList.add(new scheduleDTO(s.getSID(),s.getTrainID(),s.getDriverId(),s.getDATE(),s.getTIME(),s.getFirstseats(),s.getSeconddSeats(),s.getThirdSeats()));
        }
        return dtoList;
    }

    @Override
    public int getMaxValue(String cl, String s, String s1, String text) throws SQLException, ClassNotFoundException {
        return qdao.getMaxValue(cl,s,s1,text);
    }

    @Override
    public String getID() throws SQLException, ClassNotFoundException {
        return qdao.getRId();
    }

    @Override
    public ArrayList<String> getCustNames() throws SQLException, ClassNotFoundException {
        return qdao.getAllCustomerNames();
    }

    @Override
    public String getCustId(String custName) throws SQLException, ClassNotFoundException {
        return qdao.getCustId(custName);
    }

    @Override
    public ArrayList<String> getCustNames(String id) throws SQLException, ClassNotFoundException {
        return qdao.getAllCustomerNames(id);
    }

    @Override
    public int getSID(String trainID, String date, String time) throws SQLException, ClassNotFoundException {
        return qdao.getSID(trainID,date,time);
    }

    @Override
    public boolean saveAll(reservationDTO dto) throws SQLException, ClassNotFoundException {

       Connection connection = dbconnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            boolean result = reservationdao.save(
                    new reservation(
                            dto.getRID(),
                            dto.getCID(),
                            dto.getSID(),
                            dto.getClassNO(),
                            dto.getSeats()
                    ));
            if (!result) {
                System.out.println("NO 1");
                connection.rollback();
                return false;
            }

            result = paymentdao.save(
                    new payment(
                            dto.getRID(),
                            dto.getPaymentType(),
                            dto.getPrice()
                    ));

            if (!result) {
                System.out.println("NO 2");
                connection.rollback();
                return false;
            }
            schedule s = scheduledao.get(dto.getSID() + "");
            int type = dto.getClassNO();
            switch (type) {
                case 1:
                    s.setFirstseats(s.getFirstseats() - dto.getSeats());
                    break;
                case 2:
                    s.setSeconddSeats(s.getSeconddSeats() - dto.getSeats());
                    break;
                case 3:
                    s.setThirdSeats(s.getThirdSeats() - dto.getSeats());
                    break;
            }
            result = scheduledao.update(s);
            if (!result) {
                System.out.println("NO 3");
                connection.rollback();
                return false;
            }
            connection.commit();
            return true;
        } catch (Throwable throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public int getCount(String date) throws SQLException, ClassNotFoundException {
        return qdao.getCount(date);
    }
}
