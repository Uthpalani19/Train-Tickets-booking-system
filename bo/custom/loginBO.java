package bo.custom;

import dto.loginDTO;

import java.util.ArrayList;

public interface loginBO {
    public loginDTO getAllDetails(String userName) throws Exception;
}
