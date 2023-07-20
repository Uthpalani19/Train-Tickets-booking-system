package dao.custom;

import dao.SuperDAO;
import dto.custom.defaultDTO;
import dto.trainDTO;
import entity.*;
import entity.custom.defaultt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    public login getAllDetails(String userName) throws SQLException, ClassNotFoundException;
    public String getId() throws Exception ;
    public String getDriverId() throws Exception ;
    public String getTrainId() throws Exception ;

    public List<customer> getCustomer(String text) throws SQLException, ClassNotFoundException;
    public List<driver> getDriver(String text) throws SQLException, ClassNotFoundException;
    public List<train> getTrain(String text) throws SQLException, ClassNotFoundException;
    public ArrayList<String> getAllDrivers() throws SQLException, ClassNotFoundException;

    public String getDriversId(String s) throws SQLException, ClassNotFoundException;

    public boolean checkAvailability(String date,String driverId) throws SQLException, ClassNotFoundException;

    public List<defaultt> selectTrains(String date) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getDestination() throws SQLException, ClassNotFoundException;

    public train getTrainDetails(String s) throws SQLException, ClassNotFoundException;

    public List<schedule> getTableDetails(String date, String id) throws SQLException, ClassNotFoundException;

    public int getMaxValue(String cl, String s, String s1, String text) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllTrainId() throws SQLException, ClassNotFoundException;

    public String getRId() throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllCustomerNames() throws SQLException, ClassNotFoundException;

    public String getCustId(String custName) throws SQLException, ClassNotFoundException;

    public ArrayList<String> getAllCustomerNames(String id) throws SQLException, ClassNotFoundException;

    public int getSID(String trainID, String date, String time) throws SQLException, ClassNotFoundException;

    public int getAllDriversCount() throws SQLException, ClassNotFoundException;
    public int getAllTrainsCount() throws SQLException, ClassNotFoundException;
    public int getAllCustomersCount() throws SQLException, ClassNotFoundException;

    public int getCount(String date) throws SQLException, ClassNotFoundException;
}
