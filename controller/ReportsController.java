package controller;

import com.jfoenix.controls.JFXButton;
import db.dbconnection;
import javafx.event.ActionEvent;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;

public class ReportsController {

    public JFXButton btnCustRep;
    public JFXButton btnTrainsRep;

    public void CustRepOnAction(ActionEvent actionEvent){

            InputStream is = this.getClass().getResourceAsStream("/Reports/Customers.jrxml");
            try {
                JasperReport jr = JasperCompileManager.compileReport(is);
                JasperPrint jp = JasperFillManager.fillReport(jr, null, dbconnection.getInstance().getConnection());

                JasperViewer.viewReport(jp, false);
            } catch (JRException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    public void TrainsRepOnAction(ActionEvent actionEvent) {
        InputStream is = this.getClass().getResourceAsStream("/Reports/Trains.jasper");
        try {
            //JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jasperPrint = JasperFillManager.fillReport(is, null, dbconnection.getInstance().getConnection());
            //JasperPrint jp = JasperFillManager.fillReport(is, null, dbconnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
