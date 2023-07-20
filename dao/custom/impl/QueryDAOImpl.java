package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.QueryDAO;
import entity.*;
import entity.custom.defaultt;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public login getAllDetails(String userName) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from login where userName=?",userName);
        if(rst.next()){
            return new login(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }

    @Override
    public String getId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT customerID FROM customer ORDER BY customerID DESC LIMIT 1");
        String id="C001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("C");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="C00"+(selectedId+1);
            }
            else if(selectedId<100){
                id="C0"+(selectedId+1);
            }
        }
        return id;
    }

    @Override
    public String getDriverId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT driverId FROM driver ORDER BY driverId DESC LIMIT 1");
        String id="D001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("D");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="D00"+(selectedId+1);
            }
            else if(selectedId<100){
                id="D0"+(selectedId+1);
            }
        }
        return id;
    }

    @Override
    public String getTrainId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT trainID FROM train ORDER BY trainID DESC LIMIT 1");
        String id="T001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("T");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="T00"+(selectedId+1);
            }
            else if(selectedId<100){
                id="T0"+(selectedId+1);
            }
        }
        return id;
    }

    @Override
    public List<customer> getCustomer(String text) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer where customerName like ? ","%"+text+"%");
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

    @Override
    public List<driver> getDriver(String text) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM driver where driverName like ? ","%"+text+"%");
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

    @Override
    public List<train> getTrain(String text) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM train where trainName like ? ","%"+text+"%");
        ArrayList<train> trainList = new ArrayList<>();
        while (resultSet.next()) {
            trainList.add(
                    new train(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return trainList;
    }

    @Override
    public ArrayList<String> getAllDrivers() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT driverName FROM driver");
        ArrayList<String> driverList = new ArrayList<>();
        while (resultSet.next()) {
            driverList.add(
                            resultSet.getString(1)
            );
        }
        return driverList;
    }

    @Override
    public String getDriversId(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT driverId from driver where driverName=?",s);
        if(rst.next()){
        return rst.getString(1);}
        return null;
    }

    @Override
    public boolean checkAvailability(String date,String driverId) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT *  FROM schedule WHERE driverID=? && date=?",driverId,date);
        if(rst.next()){
            return false;
        }
        return true;
    }

    @Override
    public List<defaultt> selectTrains(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT trainId,time from schedule where date=?",date);
        List<defaultt> list=new ArrayList<>();
        while(rst.next()){
            ResultSet rst2=CrudUtil.execute("SELECT trainName,destination from train where trainId=?",rst.getString(1));
            while(rst2.next()) {
                list.add(
                        new defaultt(
                                rst.getString(1),
                                rst2.getString(1),
                                rst.getString(2),
                                rst2.getString(2)
                        )
                );
            }
        }
        return list;
    }

    @Override
    public ArrayList<String> getDestination() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT destination FROM train");
        ArrayList<String> destination = new ArrayList<>();
        while (resultSet.next()) {
            destination.add(
                    resultSet.getString(1)
            );
        }
        return destination;
    }

    @Override
    public train getTrainDetails(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from train where destination=?",s);
        if(rst.next()){
            return new train(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public List<schedule> getTableDetails(String date, String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from schedule where date=? && trainID=?",date,id);
        ArrayList<schedule> scheduleList = new ArrayList<>();
        while (rst.next()) {
            scheduleList.add(
                    new schedule(
                            rst.getInt(1),
                            rst.getString(2),
                            rst.getString(3),
                            rst.getString(4),
                            rst.getString(5),
                            rst.getInt(6),
                            rst.getInt(7),
                            rst.getInt(8)
                    )
            );
        }
        return scheduleList;
    }

    @Override
    public int getMaxValue(String clz, String time, String date, String trainId) throws SQLException, ClassNotFoundException {
        if(clz.equals("1")){
            ResultSet rst=CrudUtil.execute("SELECT firstSeats FROM schedule where time=? && date=? && trainID=?",time,date,trainId);
            if(rst.next()) {
                return rst.getInt(1);
            }
        }
        else if(clz.equals("2")){
            ResultSet rst=CrudUtil.execute("SELECT secondSeats from schedule where time =? && date=? && trainID=?",time,date,trainId);
            if(rst.next())
            {
                return rst.getInt(1);
            }
        }
        else if(clz.equals("3")){
            ResultSet rst=CrudUtil.execute("SELECT thirdSeats from schedule where time =? && date=? && trainID=?",time,date,trainId);
            if(rst.next())
            {
                return rst.getInt(1);
            }
        }
        return 0;

    }

    @Override
    public ArrayList<String> getAllTrainId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT trainId FROM train");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public String getRId() throws SQLException, ClassNotFoundException {
        ResultSet set = CrudUtil.execute("SELECT RID FROM reservation ORDER BY RID DESC LIMIT 1");
        String id="R001";
        if (set.next()){
            String temp=set.getString(1);
            String[] cs = temp.split("R");
            int selectedId=Integer.parseInt(cs[1]);
            if (selectedId<9){
                id="R00"+(selectedId+1);
            }
            else if(selectedId<100){
                id="R0"+(selectedId+1);
            }
        }
        return id;
    }

    @Override
    public ArrayList<String> getAllCustomerNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT customerName FROM customer");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public String getCustId(String custName) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT customerID from customer where customerName=?",custName);
        if(rst.next()){
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllCustomerNames(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT customerName FROM customer where customerId like ?","%"+id+"%");
        ArrayList<String> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public int getSID(String trainID, String date, String time) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT SID from schedule where trainID=? && date=? && time=?",trainID,date,time);
        if(rst.next())
        {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public int getAllDriversCount() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from driver");
        int count=0;
        while(rst.next())
        {
            count++;
        }
        return count;
    }

    @Override
    public int getAllTrainsCount() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from train");
        int count=0;
        while(rst.next())
        {
            count++;
        }
        return count;
    }

    @Override
    public int getAllCustomersCount() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * from customer");
        int count=0;
        while(rst.next())
        {
            count++;
        }
        return count;
    }

    @Override
    public int getCount(String date) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT SID from schedule where date=?",date);
        int count=0;
        while(rst.next()){
            ResultSet rst2=CrudUtil.execute("SELECT RID from reservation where SID=?",rst.getString(1));
            while(rst2.next())
            {
                count++;
            }
        }
        return count;
    }
}
