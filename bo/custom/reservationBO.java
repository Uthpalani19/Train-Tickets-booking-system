package bo.custom;

import dto.custom.reservationDTO;
import dto.scheduleDTO;
import dto.trainDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface reservationBO {
    public ArrayList<String> getDestinations() throws SQLException, ClassNotFoundException;

    public trainDTO getTrainDetails(String s) throws SQLException, ClassNotFoundException;

    public ArrayList<scheduleDTO> getTableDetails(String s, String id) throws SQLException, ClassNotFoundException;

    public int getMaxValue(String cl, String s, String s1, String text) throws SQLException, ClassNotFoundException;

    public String getID() throws Exception;

    public ArrayList<String> getCustNames() throws SQLException, ClassNotFoundException;

    public String getCustId(String custName) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getCustNames(String id) throws SQLException, ClassNotFoundException;

    public int getSID(String trainID, String s, String s1) throws SQLException, ClassNotFoundException;

    public boolean saveAll(reservationDTO dto) throws SQLException, ClassNotFoundException;

    public int getCount(String date) throws SQLException, ClassNotFoundException;
}
