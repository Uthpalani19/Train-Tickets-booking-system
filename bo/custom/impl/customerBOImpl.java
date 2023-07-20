package bo.custom.impl;

import bo.custom.customerBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.customerDAO;
import dto.customerDTO;
import entity.customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerBOImpl implements customerBO {
    private customerDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);
    private QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public boolean saveCustomer(customerDTO dto) throws Exception {
        return dao.save(new customer(dto.getCustomerID(),dto.getCustomerName(),dto.getAddress(),dto.getDob(),dto.getContact(),dto.getNIC()));
    }

    @Override
    public String getID() throws Exception {
       return qdao.getId();
    }

    @Override
    public ArrayList<customerDTO> getAllCustomers() throws Exception {
        List<customer> cList=dao.getAll();
        ArrayList<customerDTO> dtoList=new ArrayList();
        for(customer c:cList){
            dtoList.add(new customerDTO(c.getCustomerID(),c.getCustomerName(),c.getAddress(),c.getDob(),c.getContact(),c.getNIC()));
        }
        return dtoList;
    }

    @Override
    public boolean deleteCustomer(String customerID) throws Exception {
        return dao.delete(customerID);
    }

    @Override
    public boolean updateCustomer(String customerID, String customerName, String address, String dob, int contact, String nic) throws Exception {
        return dao.update(
                new customer(
                        customerID,
                        customerName,
                        address,
                        dob,
                        contact,
                        nic
                )
        );
    }

    @Override
    public ArrayList<customerDTO> getCustomer(String text) throws SQLException, ClassNotFoundException {
        List<customer> cList=qdao.getCustomer(text);
        ArrayList<customerDTO> dtoList=new ArrayList();
        for(customer c:cList){
            dtoList.add(new customerDTO(c.getCustomerID(),c.getCustomerName(),c.getAddress(),c.getDob(),c.getContact(),c.getNIC()));
        }
        return dtoList;
    }
}
