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

public class DashBoardOfficerFormController {
    public AnchorPane paneOfficer;
    public JFXButton btnManageStudents;
    public JFXButton btnReserveTickets;
    public ImageView imgLogout;
    public ImageView paneDashBoardOfiicer;
    public JFXButton btnSchedule;
    public ImageView imgHome;

    public void initialize() throws IOException {
        initUI("DefaultForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.paneOfficer.getChildren().clear();
        this.paneOfficer.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    private void setUI(String location) throws IOException {
        Stage stage = (Stage) paneDashBoardOfiicer.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("../view/"+location))));
    }

    public void manageStudentOnAction(ActionEvent actionEvent) throws IOException {
        initUI("ManagePassengers.fxml");
    }

    public void reserveTicketsOnAction(ActionEvent actionEvent) throws IOException {
        initUI("ReserveTicketsForm.fxml");
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

    public void manageScheduleOnAction(ActionEvent actionEvent) throws IOException {
        initUI("ManageScheduleForm.fxml");
    }

    public void homeOnAction(MouseEvent mouseEvent) throws IOException {
        initUI("DefaultForm.fxml");
    }
}
