package controller;

import bo.BOFactory;
import bo.custom.driverBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.driverDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Pattern;

public class RegisterDriverFormController {
    public AnchorPane RegisterStudentForm;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXDatePicker txtDOB;
    public JFXTextField txtContact;
    public JFXButton btnRegister;
    public JFXTextField txtNIC;
    public JFXButton btnClear;
    public ImageView imgBack;
    public Label lblID;

    driverBO bo;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);
        loadID();
    }

    public void loadID() throws Exception {
        String id = bo.getID();
        System.out.println(id);
        lblID.setText(id);
    }

    private void initUI(String location) throws IOException {
        this.RegisterStudentForm.getChildren().clear();
        this.RegisterStudentForm.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws Exception {
        boolean isSaved=bo.saveDriver(new driverDTO(
                lblID.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtDOB.getValue()+"",
                Integer.parseInt(txtContact.getText()),
                txtNIC.getText()
        ));

        if(isSaved)
        {
            new Alert(Alert.AlertType.INFORMATION,"Driver has been added sccessfully").show();
        }
        else
        {
            new Alert(Alert.AlertType.INFORMATION,"Try Again").show();
        }
        initUI("ManageDrivers.fxml");
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        initUI("ManageDrivers.fxml");
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        lblID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    public void NameOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[A-z ]{1,90}$").matcher(txtName.getText()).matches()) {
            txtName.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtName.setFocusColor(Paint.valueOf("Red"));
            txtName.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input your name");
            alert.show();
        }
    }

    public void AddressOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[A-z ]{1,90}$").matcher(txtAddress.getText()).matches()) {
            txtAddress.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtAddress.setFocusColor(Paint.valueOf("Red"));
            txtAddress.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input correct Address");
            alert.show();
        }
    }

    public void contactOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,10}$").matcher(txtContact.getText()).matches()) {
            txtContact.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtContact.setFocusColor(Paint.valueOf("Red"));
            txtContact.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input Valid Contact Number");
            alert.show();
        }
    }

    public void NIConAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,10}.(V|[0-9]{0,4})$").matcher(txtNIC.getText()).matches()) {
            txtNIC.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtNIC.setFocusColor(Paint.valueOf("Red"));
            txtNIC.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input valid NIC Number");
            alert.show();
        }
    }
}
