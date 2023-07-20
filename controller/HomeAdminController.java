package controller;

import bo.BOFactory;
import bo.custom.customerBO;
import bo.custom.driverBO;
import bo.custom.reservationBO;
import bo.custom.trainBO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.time.LocalDate;

public class HomeAdminController {

    public Label lblDrivers;
    public Label lblPaassengers;
    public Label lblTrains;
    public LineChart chart;
    driverBO bo;
    customerBO bo2;
    trainBO bo3;
    reservationBO bo4;

    public void initialize() throws SQLException, ClassNotFoundException {
           bo= BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
           bo2= BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);
           bo3= BOFactory.getInstance().getBO(BOFactory.BOType.TRAIN);
            bo4= BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);

           int drivers=bo.getAllCount();
           int trains=bo.getTrainsAllCount();
           int customers=bo.getCustomersAllCount();

           setChart();

           lblDrivers.setText(drivers+"");
           lblPaassengers.setText(customers+"");
           lblTrains.setText(trains+"");
    }

    public void setChart() throws SQLException, ClassNotFoundException {
        XYChart.Series s=new XYChart.Series<>();
        s.setName("The Train reservations");

        int [] ar=new int[7];
        for (int i=0; i<ar.length-1 ; i++){
            ar[i]=bo4.getCount(LocalDate.now().plusDays ( -(7-i) )+"");
        }
        int seven=bo4.getCount(LocalDate.now().plusDays ( -7 )+"");
        int six=bo4.getCount(LocalDate.now().plusDays ( -6 )+"");
        int five=bo4.getCount(LocalDate.now().plusDays ( -5 )+"");
        int four=bo4.getCount(LocalDate.now().plusDays ( -4 )+"");
        int three=bo4.getCount(LocalDate.now().plusDays ( -3 )+"");
        int two=bo4.getCount(LocalDate.now().plusDays ( -2 )+"");
        int one=bo4.getCount(LocalDate.now().plusDays ( -1 )+"");

        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -7 )+"",seven));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -6 )+"",six));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -5 )+"",five));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -4 )+"",four));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -3 )+"",three));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -2 )+"",two));
        s.getData().add(new XYChart.Data<>(LocalDate.now().plusDays ( -1 )+"",one));

        chart.getData().addAll(s);
    }
}
