package bo.custom.impl;

import bo.custom.loginBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.loginDAO;
import dto.loginDTO;
import entity.login;

import java.util.ArrayList;

public class loginBOImpl implements loginBO {
    private loginDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    private QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public loginDTO getAllDetails(String userName) throws Exception {
        login login=qdao.getAllDetails(userName);
        return new loginDTO(login.getUserName(),login.getPassword(),login.getRole());
    }
}
