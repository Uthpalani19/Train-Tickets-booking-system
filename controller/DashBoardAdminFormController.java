package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class DashBoardAdminFormController {

    public AnchorPane paneAdmin;
    public AnchorPane paneAdmin1;
    public JFXButton btnManageTrains;
    public JFXButton btnManageSchedule;
    public JFXButton btnManageDrivers;
    public JFXButton btnReports;
    public ImageView imgLogout;
    public ImageView imgHome;

    public void initialize() throws IOException {
        initUI("HomeAdmin.fxml");
    }

    private void initUI(String location) throws IOException {
        this.paneAdmin.getChildren().clear();
        this.paneAdmin.getChildren().add(FXMLLoader.load(this.getClass().getResource("/view/"+location)));
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) paneAdmin1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/"+location))));
    }

    public void reportsOnAction(ActionEvent actionEvent) throws IOException {
        initUI("Reports.fxml");
    }

    public void logoutOnAction(MouseEvent mouseEvent) throws IOException {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LOGOUT");
        alert.setHeaderText("CONFIRMATION OF LOGOUT");
        alert.setContentText("Are you sure to logout ?");

        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK) {
            setUI("MainForm.fxml");
        }
    }

    public void manageTrainOnAction(ActionEvent actionEvent) throws IOException {
        initUI("ManageTrains.fxml");
    }

    public void manageDriversOnAction(ActionEvent actionEvent) throws IOException {
        initUI("ManageDrivers.fxml");
    }

    public void homeOnAction(MouseEvent mouseEvent) throws IOException {
        initUI("HomeAdmin.fxml");
    }
}
