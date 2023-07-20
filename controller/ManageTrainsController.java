package controller;

import bo.BOFactory;
import bo.custom.trainBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.trainDTO;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.TM.trainTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManageTrainsController {
    public AnchorPane paneOfficer;
    public JFXTextField txtCustomerName;
    public TableColumn <trainTM,String> colId;
    public TableColumn <trainTM,String> colTrainName;
    public TableColumn <trainTM,String> colDestination;
    public TableColumn <trainTM,String> colFirstClass;
    public TableColumn <trainTM,String> colSecondClass;
    public TableColumn <trainTM,String> colThirdClass;
    public TableColumn <trainTM,String> colUpdate;
    public TableColumn <trainTM,String> colDelete;
    public JFXButton btnNew;
    public TableView<trainTM> tblTrains;
    trainBO bo;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.TRAIN);

        colId.setCellValueFactory(new PropertyValueFactory<>("trainID"));
        colTrainName.setCellValueFactory(new PropertyValueFactory<>("trainName"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        colFirstClass.setCellValueFactory(new PropertyValueFactory<>("firstPrice"));
        colSecondClass.setCellValueFactory(new PropertyValueFactory<>("secondPrice"));
        colThirdClass.setCellValueFactory(new PropertyValueFactory<>("thirdPrice"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        editableCols();
        loadTrains();

        tblTrains.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    private void editableCols() {
        colId.setCellFactory(TextFieldTableCell.forTableColumn());
        colId.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTrainID(e.getNewValue());
        });
        colTrainName.setCellFactory(TextFieldTableCell.forTableColumn());
        colTrainName.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setTrainName(e.getNewValue());
        });
        colDestination.setCellFactory(TextFieldTableCell.forTableColumn());
        colDestination.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDestination(e.getNewValue());
        });
        colFirstClass.setCellFactory(TextFieldTableCell.forTableColumn());
        colFirstClass.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setFirstPrice(e.getNewValue());
        });
        colSecondClass.setCellFactory(TextFieldTableCell.forTableColumn());
        colSecondClass.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSecondPrice(e.getNewValue());
        });
        colThirdClass.setCellFactory(TextFieldTableCell.forTableColumn());
        colThirdClass.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setThirdPrice(e.getNewValue());
        });
        tblTrains.setEditable(true);
    }

    public void loadTrains() throws Exception {
        ObservableList<trainTM> tmList =
                FXCollections.observableArrayList();
        tmList.clear();
        List<trainDTO> allDrivers = bo.getAllTrains();
        for (trainDTO dto : allDrivers
        ) {
            Button btn = new Button("Update");
            btn.setStyle("-fx-background-color: MediumSeaGreen");
            Button btn2 = new Button("Delete");
            btn2.setStyle("-fx-background-color: #a90e0e");
            trainTM tm = new trainTM(dto.getTrainID(), dto.getTrainName(),
                    dto.getDestination(), dto.getFirstPrice()+"", dto.getSecondPrice()+"", dto.getThirdPrice()+"",btn,btn2);
            tmList.add(tm);
            btn.setOnAction((e) -> {
                try {
                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.updateTrain(tm.getTrainID(),tm.getTrainName(),tm.getDestination(),Double.parseDouble(tm.getFirstPrice()),Double.parseDouble(tm.getSecondPrice()),Double.parseDouble(tm.getThirdPrice()))) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadTrains();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            btn2.setOnAction((e) -> {
                try {
                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.deleteTrain(tm.getTrainID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadTrains();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }

        tblTrains.setItems(tmList);
    }


    public void custNameOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        getTrain(txtCustomerName.getText());
    }

    private void getTrain(String text) throws SQLException, ClassNotFoundException {
        ObservableList<trainTM> tmList =
                FXCollections.observableArrayList();
        tmList.clear();
        List<trainDTO> allDrivers = bo.getTrain(text);
        for (trainDTO dto : allDrivers
        ) {
            Button btn = new Button("Update");
            Button btn2 = new Button("Delete");
            trainTM tm = new trainTM(dto.getTrainID(), dto.getTrainName(),
                    dto.getDestination(), dto.getFirstPrice()+"", dto.getSecondPrice()+"", dto.getThirdPrice()+"",btn,btn2);
            tmList.add(tm);
            btn.setOnAction((e) -> {
                try {
                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.updateTrain(tm.getTrainID(),tm.getTrainName(),tm.getDestination(),Double.parseDouble(tm.getFirstPrice()),Double.parseDouble(tm.getSecondPrice()),Double.parseDouble(tm.getThirdPrice()))) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadTrains();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });

            btn2.setOnAction((e) -> {
                try {
                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.deleteTrain(tm.getTrainID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadTrains();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }

        tblTrains.setItems(tmList);
    }

    public void newOnAction(ActionEvent actionEvent) throws IOException {
        initUI("AddTrainForm.fxml");
    }

    private void initUI(String location) throws IOException {
        this.paneOfficer.getChildren().clear();
        this.paneOfficer.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }
}
