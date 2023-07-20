package controller;

import bo.BOFactory;
import bo.custom.driverBO;
import bo.custom.scheduleBO;
import bo.custom.trainBO;
import com.jfoenix.controls.*;
import dto.driverDTO;
import dto.scheduleDTO;
import dto.trainDTO;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class ManageScheduleFormController {
    public JFXTextField txtTrainID;
    public JFXComboBox txtTime;
    public JFXDatePicker txtDate;
    public JFXSlider sld1st;
    public JFXSlider sld2nd;
    public JFXSlider sld3rd;
    public JFXButton btnSave;
    public JFXTimePicker timePicker;
    public JFXComboBox cmbDriver;
    public ImageView imgSearch;
    public AnchorPane pane;
    public JFXComboBox cmbTrainId;
    public Label txtTrainName;
    public Label txtDestination;
    public Label lblTrainname;
    public Label lblDestination;
    public ImageView imgSearchTrain;
    public Label txtDriverID;
    public Label lblDriverID;
    scheduleBO bo;
    trainBO bo3;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);
        bo3= BOFactory.getInstance().getBO(BOFactory.BOType.TRAIN);
        getAllTrains();
        getAllDrivers();
    }

    public void getAllTrains(){
        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();
            ArrayList<String> arrayList = bo.getTrainsId();
            for (String s : arrayList) {
                observableList.add(s);
            }
            cmbTrainId.setItems(observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getAllDrivers(){

        try {
            ObservableList<String> observableList= FXCollections.observableArrayList();
            ArrayList<String> arrayList = null;
            arrayList = bo.getDriversNames();
            for (String s : arrayList) {
                observableList.add(s);
            }
            cmbDriver.setItems(observableList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void SaveOnAction(ActionEvent actionEvent){

        try {
            String driverId=  bo.getDriverId(cmbDriver.getValue()+"");
            boolean isSaved=bo.saveSchedule(
                    new scheduleDTO(
                            cmbTrainId.getValue()+"",
                            driverId,
                            txtDate.getValue()+"",
                            timePicker.getValue()+"",
                            (int)(Double.parseDouble(sld1st.getValue()+"")),
                            (int)(Double.parseDouble(sld2nd.getValue()+"")),
                            (int)(Double.parseDouble(sld3rd.getValue()+""))
                    )
            );
            if(isSaved)
            {
                new Alert(Alert.AlertType.INFORMATION,"Added Successfully").show();
                initUI("ManageScheduleForm.fxml");

            }
            else
            {
                new Alert(Alert.AlertType.WARNING,"Try Again").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initUI(String location){
        try {
            this.pane.getChildren().clear();
            this.pane.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cmbBoxOnAction(ActionEvent actionEvent) {
        try {
            String driverId= null;
            driverId = bo.getDriverId(cmbDriver.getValue()+"");
            txtDriverID.setText(driverId);
            txtDriverID.setVisible(true);
            lblDriverID.setVisible(true);

            boolean isAvailable=bo.checkAvailability(txtDate.getValue()+"",driverId);
            if(!isAvailable){
                new Alert(Alert.AlertType.ERROR,"Sorry. This driver is not available at the moment").show();
            }
            else{
                new Alert(Alert.AlertType.INFORMATION,"This driver is available").show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbTrainOnAction(ActionEvent actionEvent) {
        try {
            trainDTO dto=bo3.getTrainDetails(cmbTrainId.getValue()+"");
            lblTrainname.setVisible(true);
            lblDestination.setVisible(true);
            txtTrainName.setVisible(true);
            txtDestination.setVisible(true);
            txtTrainName.setText(dto.getTrainName());
            txtDestination.setText(dto.getDestination());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
