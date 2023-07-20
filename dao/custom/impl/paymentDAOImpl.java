package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.paymentDAO;
import entity.payment;

import java.util.List;

public class paymentDAOImpl implements paymentDAO {
    @Override
    public boolean save(payment payment) throws Exception {
        return CrudUtil.execute("INSERT into payment values (?,?,?)",payment.getRID(),payment.getPaymentType(),payment.getPrice());
    }

    @Override
    public boolean update(payment payment) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public payment get(String s) throws Exception {
        return null;
    }

    @Override
    public List<payment> getAll() throws Exception {
        return null;
    }
}
