package dao;

import db.dbconnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql,Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm= dbconnection.getInstance().getConnection().prepareStatement(sql);
        for (int i=0;i<args.length;i++){
            pstm.setObject((i+1),args[i]);
        }

        if(sql.startsWith("SELECT")){
            return (T)pstm.executeQuery();
        }
        return ((T)(Boolean)(pstm.executeUpdate()>0));
    }
}
