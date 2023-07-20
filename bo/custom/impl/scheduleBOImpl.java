package bo.custom.impl;

import bo.custom.scheduleBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.scheduleDAO;
import dto.custom.defaultDTO;
import dto.customerDTO;
import dto.scheduleDTO;
import entity.custom.defaultt;
import entity.customer;
import entity.schedule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class scheduleBOImpl implements scheduleBO {
    scheduleDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);
    @Override
    public boolean saveSchedule(scheduleDTO dto) throws Exception {
        return dao.save(
                new schedule(
                        dto.getTrainID(),
                        dto.getDriverId(),
                        dto.getDATE(),
                        dto.getTIME(),
                        dto.getFirstseats(),
                        dto.getSecondSeats(),
                        dto.getThirdSeats()
                )
        );
    }

    @Override
    public ArrayList<String> getDriversNames() throws SQLException, ClassNotFoundException {
        return qdao.getAllDrivers();
    }

    @Override
    public String getDriverId(String s) throws SQLException, ClassNotFoundException {
        return qdao.getDriversId(s);
    }

    @Override
    public boolean checkAvailability(String date,String driverId) throws SQLException, ClassNotFoundException {
        return qdao.checkAvailability(date,driverId);
    }

    @Override
    public ArrayList<defaultDTO> selectTrains(String date) throws SQLException, ClassNotFoundException {
        List<defaultt> list=qdao.selectTrains(date);
        ArrayList<defaultDTO> dtoList=new ArrayList();
        for(defaultt c:list){
            dtoList.add(new defaultDTO(c.getTrainID(),c.getTrainName(),c.getTime(),c.getDestination()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<String> getTrainsId() throws SQLException, ClassNotFoundException {
        return qdao.getAllTrainId();
    }
}
