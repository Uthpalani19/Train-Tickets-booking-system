package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.customerDAO;
import entity.customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class customerDAOImpl implements customerDAO {
    @Override
    public boolean save(customer customer) throws Exception {
        return CrudUtil.execute("INSERT into customer values(?,?,?,?,?,?)",customer.getCustomerID(),customer.getCustomerName(),customer.getAddress(),customer.getDob(),customer.getContact(),customer.getNIC());
    }

    @Override
    public boolean update(customer customer) throws Exception {
        return CrudUtil.execute("UPDATE customer set customerName=?,address=?,dob=?,contact=?,NIC=? where customerID=?",customer.getCustomerName(),customer.getAddress(),customer.getDob(),customer.getContact(),customer.getNIC(),customer.getCustomerID());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE from customer where customerID=?",s);
    }

    @Override
    public customer get(String s) throws Exception {
        return null;
    }

    @Override
    public List<customer> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
        ArrayList<customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            customerList.add(
                    new customer(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getString(6)
                            )
            );
        }
        return customerList;
    }
}
