package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.scheduleDAO;
import entity.schedule;

import java.sql.ResultSet;
import java.util.List;

public class scheduleDAOImpl implements scheduleDAO {
    @Override
    public boolean save(schedule schedule) throws Exception {
        return CrudUtil.execute("INSERT INTO schedule(trainID,driverId,date,time,firstSeats,secondSeats,thirdSeats) values(?,?,?,?,?,?,?)",schedule.getTrainID(),schedule.getDriverId(),schedule.getDATE(),schedule.getTIME(),schedule.getFirstseats(),schedule.getSeconddSeats(),schedule.getThirdSeats());
    }

    @Override
    public boolean update(schedule schedule) throws Exception {
        return CrudUtil.execute("UPDATE schedule set trainId=?,driverId=?,date=?,time=?,firstSeats=?,secondSeats=?,thirdSeats=? where SID=?",schedule.getTrainID(),schedule.getDriverId(),schedule.getDATE(),schedule.getTIME(),schedule.getFirstseats(),schedule.getSeconddSeats(),schedule.getThirdSeats(),schedule.getSID());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public schedule get(String s) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM schedule WHERE SID = ?", s);
        if(set.next()){
            return new schedule(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getInt(6),
                    set.getInt(7),
                    set.getInt(8)
                    );
        }else{
            return null;
        }
    }

    @Override
    public List<schedule> getAll() throws Exception {
        return null;
    }
}
