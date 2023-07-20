package controller;

import bo.BOFactory;
import bo.custom.trainBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.trainDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.regex.Pattern;

public class AddTrainFormController {

    public ImageView imgBack;
    public AnchorPane pane;
    @FXML
    private JFXTextField txtTrainName;

    @FXML
    private JFXTextField txtDestination;

    @FXML
    private JFXButton btnContinue;

    @FXML
    private Label lblID;

    @FXML
    private JFXTextField txtFirstClass;

    @FXML
    private JFXTextField txtSecondClass;

    @FXML
    private JFXTextField txtThirdClass;

    @FXML
    private JFXButton btnSave;
    trainBO bo;

    public void initialize() throws Exception {
        bo = BOFactory.getInstance().getBO(BOFactory.BOType.TRAIN);
        loadId();
    }

    private void loadId(){

        try {
            String id = bo.getID();
            System.out.println(id);
            lblID.setText(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @FXML
    void ContinueOnAction(ActionEvent event) throws IOException {
        initUI("ManageScheduleForm.fxml");
    }

    @FXML
    void SaveOnAction(ActionEvent event) throws Exception {
        boolean isSaved=bo.saveTrain(
                new trainDTO(
                        lblID.getText(),
                        txtTrainName.getText(),
                        txtDestination.getText(),
                        Double.parseDouble(txtFirstClass.getText()),
                        Double.parseDouble(txtSecondClass.getText()),
                        Double.parseDouble(txtThirdClass.getText())
                )
        );
        if(isSaved)
        {
            new Alert(Alert.AlertType.INFORMATION,"Train has been added successfully").show();
        }
        else
        {
            new Alert(Alert.AlertType.INFORMATION,"Please try Again").show();
        }
        initUI("ManageTrains.fxml");
    }

    public void backOnAction(MouseEvent mouseEvent) throws IOException {
        initUI("ManageTrains.fxml");
    }

    private void initUI(String location) throws IOException {
        this.pane.getChildren().clear();
        this.pane.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void nameOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[A-z ]{1,90}$").matcher(txtTrainName.getText()).matches()) {
            txtTrainName.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtTrainName.setFocusColor(Paint.valueOf("Red"));
            txtTrainName.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input correct name");
            alert.show();
        }
    }

    public void destinationOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[A-z ]{1,90}$").matcher(txtDestination.getText()).matches()) {
            txtDestination.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtDestination.setFocusColor(Paint.valueOf("Red"));
            txtDestination.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input correct Destination");
            alert.show();
        }
    }

    public void firstOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,10}$").matcher(txtFirstClass.getText()).matches()) {
            txtFirstClass.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtFirstClass.setFocusColor(Paint.valueOf("Red"));
            txtFirstClass.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input Number");
            alert.show();
        }
    }

    public void SecondOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,10}$").matcher(txtSecondClass.getText()).matches()) {
            txtSecondClass.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtSecondClass.setFocusColor(Paint.valueOf("Red"));
            txtSecondClass.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input Number");
            alert.show();
        }
    }

    public void thirdOnAction(ActionEvent actionEvent) {
        if(Pattern.compile("^[0-9]{1,10}$").matcher(txtThirdClass.getText()).matches()) {
            txtThirdClass.setFocusColor(Paint.valueOf("Blue"));
        }
        else
        {
            txtThirdClass.setFocusColor(Paint.valueOf("Red"));
            txtThirdClass.requestFocus();
            Alert alert=new Alert(Alert.AlertType.ERROR,"Input Number");
            alert.show();
        }
    }
}
