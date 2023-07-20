package bo.custom;

import dto.custom.defaultDTO;
import dto.scheduleDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface scheduleBO {
    public boolean saveSchedule(scheduleDTO dto) throws Exception;
    public ArrayList<String> getDriversNames() throws SQLException, ClassNotFoundException;

    public String getDriverId(String s) throws SQLException, ClassNotFoundException;

    public boolean checkAvailability(String date,String driverId) throws SQLException, ClassNotFoundException;

    public ArrayList<defaultDTO> selectTrains(String date) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getTrainsId()  throws SQLException, ClassNotFoundException;
}
