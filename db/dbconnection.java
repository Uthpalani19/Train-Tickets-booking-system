package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static db.dbconnection dbconnection;
    private Connection connection;

    private dbconnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/traintickets","root","mysql");
    }

    public static db.dbconnection getInstance() throws SQLException, ClassNotFoundException {
        if(dbconnection==null){
            dbconnection=new dbconnection();
        }
        return dbconnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
