package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.driverDAO;
import entity.customer;
import entity.driver;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class driverDAOImpl implements driverDAO {
    @Override
    public boolean save(driver driver) throws Exception {
        return CrudUtil.execute("INSERT INTO DRIVER values(?,?,?,?,?,?)",driver.getDriverId(),driver.getDriverName(),driver.getAddress(),driver.getDob(),driver.getContact(),driver.getNIC());
    }

    @Override
    public boolean update(driver driver) throws Exception {
        return CrudUtil.execute("UPDATE driver set driverName=?,address=?,dob=?,contact=?,NIC=? where driverId=?",driver.getDriverName(),driver.getAddress(),driver.getDob(),driver.getContact(),driver.getNIC(),driver.getDriverId());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE from driver where driverId=?",s);
    }

    @Override
    public driver get(String s) throws Exception {
        return null;
    }

    @Override
    public List<driver> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM driver");
        ArrayList<driver> driverList = new ArrayList<>();
        while (resultSet.next()) {
            driverList.add(
                    new driver(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getString(6)
                    )
            );
        }
        return driverList;
    }
}
