package bo.custom.impl;

import bo.custom.driverBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.driverDAO;
import dto.customerDTO;
import dto.driverDTO;
import entity.customer;
import entity.driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class driverBOImpl implements driverBO {
    driverDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DRIVER);
    private QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean saveDriver(driverDTO dto) throws Exception {
        return dao.save(
                new driver(
                        dto.getDriverId(),
                        dto.getDriverName(),
                        dto.getAddress(),
                        dto.getDob(),
                        dto.getContact(),
                        dto.getNIC()
                )
        );
    }
    public String getID() throws Exception {
        return qdao.getDriverId();
    }

    @Override
    public ArrayList<driverDTO> getAllDrivers() throws Exception {
        List<driver> cList=dao.getAll();
        ArrayList<driverDTO> dtoList=new ArrayList();
        for(driver c:cList){
            dtoList.add(new driverDTO(c.getDriverId(),c.getDriverName(),c.getAddress(),c.getDob(),c.getContact(),c.getNIC()));
        }
        return dtoList;
    }

    @Override
    public boolean updateDriver(String driverID, String driverName, String address, String dob, int parseInt, String nic) throws Exception {
        return dao.update(
                new driver(
                        driverID,
                        driverName,
                        address,
                        dob,
                        parseInt,
                        nic
                )
        );
    }

    @Override
    public boolean deleteDriver(String driverID) throws Exception {
        return dao.delete(driverID);
    }

    @Override
    public ArrayList<driverDTO> getDriver(String text) throws SQLException, ClassNotFoundException {
        List<driver> cList=qdao.getDriver(text);
        ArrayList<driverDTO> dtoList=new ArrayList();
        for(driver c:cList){
            dtoList.add(new driverDTO(c.getDriverId(),c.getDriverName(),c.getAddress(),c.getDob(),c.getContact(),c.getNIC()));
        }
        return dtoList;
    }

    @Override
    public int getAllCount() throws SQLException, ClassNotFoundException {
        return qdao.getAllDriversCount();
    }

    @Override
    public int getTrainsAllCount() throws SQLException, ClassNotFoundException {
        return qdao.getAllTrainsCount();
    }

    @Override
    public int getCustomersAllCount() throws SQLException, ClassNotFoundException {
        return qdao.getAllCustomersCount();
    }


}
