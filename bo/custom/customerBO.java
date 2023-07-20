package bo.custom;

import dto.customerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface customerBO {
    public boolean saveCustomer(customerDTO dto) throws Exception;
    public String getID() throws Exception;
    public ArrayList<customerDTO> getAllCustomers() throws Exception;

    public boolean deleteCustomer(String customerID) throws Exception;

    public boolean updateCustomer(String customerID, String customerName, String address, String dob, int contact, String nic) throws Exception;

    public ArrayList<customerDTO> getCustomer(String text) throws SQLException, ClassNotFoundException;
}
