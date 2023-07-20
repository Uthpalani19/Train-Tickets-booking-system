package bo.custom;

import dto.driverDTO;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface driverBO {
    public boolean saveDriver(driverDTO dto) throws Exception;
    public String getID() throws Exception;

    public ArrayList<driverDTO> getAllDrivers() throws Exception;

    public boolean updateDriver(String driverID, String driverName, String address, String dob, int parseInt, String nic) throws Exception;

    public boolean deleteDriver(String driverID) throws Exception;

   public ArrayList<driverDTO> getDriver(String text) throws SQLException, ClassNotFoundException;

    public int getAllCount() throws SQLException, ClassNotFoundException;

    public int getTrainsAllCount() throws SQLException, ClassNotFoundException;

    public int getCustomersAllCount() throws SQLException, ClassNotFoundException;
}
