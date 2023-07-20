package controller;

import bo.BOFactory;
import bo.custom.reservationBO;
import com.jfoenix.controls.*;
import db.dbconnection;
import dto.custom.reservationDTO;
import dto.scheduleDTO;
import dto.trainDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import view.TM.custom.reservationTM;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ReserveTicketsFormController {
    public JFXComboBox cmbDestination;
    public TableView tblSchedule;
    public TableColumn colTime;
    public TableColumn colTrainID;
    public TableColumn colTrainName;
    public TableColumn col1st;
    public TableColumn col2nd;
    public TableColumn col3rd;
    public JFXComboBox cmbClass;
    public JFXButton btnConfirm;
    public ToggleGroup payment;
    public AnchorPane paneReservation;
    public ImageView imgSearch;
    public JFXButton btnCard;
    public JFXButton btnGetYourTicket;
    public JFXSlider sldSeats;
    public JFXButton btnCalculate;
    public Label lblPrice;
    public JFXDatePicker datePicker;
    public JFXComboBox cmbTime;
    public JFXTextField txtCustId;
    public Label lblRID;
    public JFXComboBox cmbCustomers;
    public Label lblCust;
    public Label lblCustId;
    public JFXRadioButton rdCash;
    public JFXRadioButton rdCard;
    reservationBO bo;
    trainDTO dto;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.RESERVATION);
        try {
            loadId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbClass.getItems().addAll("1","2","3");

        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colTrainID.setCellValueFactory(new PropertyValueFactory<>("trainId"));
        colTrainName.setCellValueFactory(new PropertyValueFactory<>("trainName"));
        col1st.setCellValueFactory(new PropertyValueFactory<>("firstClass"));
        col2nd.setCellValueFactory(new PropertyValueFactory<>("secondClass"));
        col3rd.setCellValueFactory(new PropertyValueFactory<>("thirdClass"));
        getAllCustomerNames();
        getDestination();
    }

    private void getAllCustomerNames() throws Exception{
        ObservableList<String> observableList= FXCollections.observableArrayList();
        ArrayList<String> arrayList = bo.getCustNames();
        for (String s : arrayList) {
            observableList.add(s);
        }
        cmbCustomers.setItems(observableList);
    }

    private void loadId() throws Exception {
        String id = bo.getID();
        System.out.println(id);
        lblRID.setText(id);
    }

    public void OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String cl=cmbClass.getValue()+"";
        double max=(double) bo.getMaxValue(cl,cmbTime.getValue()+"",datePicker.getValue()+"",dto.getTrainID());
        sldSeats.setMax(max);
    }

    private void getDestination() throws SQLException, ClassNotFoundException {
        ObservableList<String> observableList= FXCollections.observableArrayList();
        ArrayList<String> arrayList = bo.getDestinations();
        for (String s : arrayList) {
            observableList.add(s);
        }
        cmbDestination.setItems(observableList);

    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) paneReservation.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/"+location))));
    }

    public void CardOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/PaymentForm.fxml"))));
        stage.show();
    }

    public void calculateOnAction(ActionEvent actionEvent) {
        try {
            btnGetYourTicket.setDisable(false);
            trainDTO dto=bo.getTrainDetails(cmbDestination.getValue()+"");
            String c=cmbClass.getValue()+"";
            int seatsCount=(int)Double.parseDouble(sldSeats.getValue()+"");
            double pricePer=0;
            switch (c){
                case "1":pricePer=dto.getFirstPrice();break;
                case "2":pricePer=dto.getSecondPrice();break;
                case "3":pricePer=dto.getThirdPrice();break;
            }
            double total=seatsCount*pricePer;
            String finalTotal=total+"";
            lblPrice.setText(finalTotal);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cmbDestOnAction(ActionEvent actionEvent) {
        try {
            dto=bo.getTrainDetails(cmbDestination.getValue()+"");
            ArrayList<scheduleDTO> list=bo.getTableDetails(datePicker.getValue()+"",dto.getTrainID());
            ObservableList<String> cmbList= FXCollections.observableArrayList();
            ObservableList<reservationTM> tmList = FXCollections.observableArrayList();
            tmList.clear();
            for (scheduleDTO dto1 : list
            ) {
                reservationTM tm = new reservationTM(dto1.getTIME(),dto.getTrainID(),dto.getTrainName(),dto1.getFirstseats(),dto1.getSecondSeats(),dto1.getThirdSeats());
                tmList.add(tm);
                cmbList.add(tm.getTime());
            }
            cmbTime.setItems(cmbList);
            tblSchedule.setItems(tmList);

        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR,"Sorry !! Enter suitable details").show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR,"Sorry !! Enter suitable details").show();
        }
    }

    public void cmbCustOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String custName=cmbCustomers.getValue()+"";
        String id=bo.getCustId(custName);
        lblCust.setVisible(true);
        lblCustId.setVisible(true);
        lblCustId.setText(id);
    }

    public void searchCustNameOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String id=cmbCustomers.getValue()+"";
        ObservableList<String> observableList= FXCollections.observableArrayList();
        ArrayList<String> arrayList = bo.getCustNames(id);
        for (String s : arrayList) {
            observableList.add(s);
        }
        cmbCustomers.setItems(observableList);
    }

    public void GetOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("RESERVATION");
        alert.setHeaderText("CONFIRMATION OF RESSERVATION");
        alert.setContentText("Are you sure to reserve seats ?");

        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            int sid=bo.getSID(dto.getTrainID(),datePicker.getValue()+"",cmbTime.getValue()+"");
            String type="CARD";
            if(rdCash.isSelected()){
                type="CASH";
            }
            reservationDTO dto=new reservationDTO(
                    lblRID.getText(),
                    lblCustId.getText(),
                    sid,
                    Integer.parseInt(cmbClass.getValue()+""),
                    (int)Double.parseDouble(sldSeats.getValue()+""),
                    type,
                    Double.parseDouble(lblPrice.getText())
            );
            boolean isSavedAll=bo.saveAll(dto);
            if(isSavedAll){
                new Alert(Alert.AlertType.INFORMATION,"Reservation has done . !!").show();
                getBill();
            }
            else{
                new Alert(Alert.AlertType.WARNING,"Please try Again!!").show();
            }
            initUI("ReserveTicketsForm.fxml");
        }

    }

    private void getBill() {

        try {
            InputStream is=this.getClass().getResourceAsStream("/Reports/Bill.jrxml");
            JasperReport jr= JasperCompileManager.compileReport(is);
            HashMap hm=new HashMap();
            hm.put("ReservationID",lblRID.getText());
            hm.put("Customer ID",lblCustId.getText());
            hm.put("Destination",cmbDestination.getValue()+"");
            hm.put("Date",datePicker.getValue()+"");
            hm.put("Time",cmbTime.getValue()+"");
            hm.put("Class",cmbClass.getValue()+"");
            hm.put("No of Seats",(int)sldSeats.getValue()+"");
            hm.put("Price",lblPrice.getText());
            JasperPrint jp= JasperFillManager.fillReport(jr,hm, dbconnection.getInstance().getConnection());
            JasperViewer.viewReport(jp,false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initUI(String s) throws IOException {
        this.paneReservation.getChildren().clear();
        this.paneReservation.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+s)));
    }
}
