package controller;

import bo.BOFactory;
import bo.custom.loginBO;
import com.jfoenix.controls.JFXButton;
import dto.loginDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainFormController {
    public TextField txtUserName;
    public JFXButton btnLogin;
    public JFXButton btnCancel;
    public PasswordField txtPassword;
    public AnchorPane mainPane;

    private loginBO bo;

    public void initialize() throws Exception {
        bo = BOFactory.getInstance().getBO(BOFactory.BOType.LOGIN);
    }

    public void LogOnAction(ActionEvent actionEvent) {
        try {
            loginDTO loginDTO = null;
            loginDTO = bo.getAllDetails(txtUserName.getText());
            String role = "";
            if (loginDTO.getPassword().equals(txtPassword.getText())) {
                role = loginDTO.getRole();
                switch (role) {
                    case "Admin":
                        setUI("DashBoardAdminForm.fxml");
                        break;

                    case "officer":
                        setUI("DashBoardOfficerForm.fxml");
                        break;
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Try Again with correct password").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void setUI(String location) throws IOException {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/"+location))));
    }

    public void CancelOnAction(ActionEvent actionEvent) throws IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("CONFIRMATION OF EXIT");
        alert.setContentText("Are you sure to exit ?");

        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.close();
        }
    }
}
