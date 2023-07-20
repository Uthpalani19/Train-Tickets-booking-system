package dao.custom.impl;

import dao.custom.loginDAO;
import entity.login;

import java.util.List;

public class loginDAOImpl implements loginDAO {
    @Override
    public boolean save(login login) throws Exception {
        return false;
    }

    @Override
    public boolean update(login login) throws Exception {
        return false;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public login get(String s) throws Exception {
        return null;
    }

    @Override
    public List<login> getAll() throws Exception {
        return null;
    }
}
