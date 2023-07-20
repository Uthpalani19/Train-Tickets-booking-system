package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.regex.Pattern;

public class PaymentFormController {

    public JFXButton btnPayment;
    public JFXButton cancel;
    public AnchorPane pane;
    public TextField txtCardNumber;
    public TextField txtDate;
    public TextField txtCVV;

    public void MakeOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Payment has done !!").show();
    }

    public void cancelOnAction(ActionEvent actionEvent) {
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("CONFIRMATION OF CANCEL");
        alert.setContentText("Are you sure to cancel ?");

        Optional<ButtonType> result=alert.showAndWait();
        if(result.get()==ButtonType.OK){
            Stage stage = (Stage) pane.getScene().getWindow();
            stage.close();
        }
    }

    public void cardOnAction(ActionEvent actionEvent) {

    }

    public void expireOnAction(ActionEvent actionEvent) {
    }

    public void cvvOnAction(ActionEvent actionEvent) {
    }
}
