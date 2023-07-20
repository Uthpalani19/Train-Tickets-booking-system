package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.reservationDAO;
import entity.reservation;

import java.util.List;

public class reservationDAOImpl implements reservationDAO {
    @Override
    public boolean save(reservation reservation) throws Exception {
        return CrudUtil.execute("INSERT into reservation values (?,?,?,?,?)",reservation.getRID(),reservation.getCustomerID(),reservation.getScheduleID(),reservation.getClassNo(),reservation.getSeats());
    }

    @Override
    public boolean update(reservation reservation) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public reservation get(String s) throws Exception {
        return null;
    }

    @Override
    public List<reservation> getAll() throws Exception {
        return null;
    }
}
