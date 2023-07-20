package controller;

import bo.BOFactory;
import bo.custom.driverBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.driverDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.TM.DriverTM;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageDriversController {
    public AnchorPane paneOfficer;
    public JFXTextField txtDriverName;
    public TableColumn <DriverTM, String> colId;
    public TableColumn <DriverTM, String> colDriverName;
    public TableColumn <DriverTM, String> colAddress;
    public TableColumn <DriverTM, String> colDOB;
    public TableColumn <DriverTM, String> colContact;
    public TableColumn <DriverTM, String> colNIC;
    public TableColumn colUpdate;
    public TableColumn colDelete;
    public JFXButton btnNew;
    public TableView<DriverTM> tblDriver;
    driverBO bo;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.DRIVER);

        colId.setCellValueFactory(new PropertyValueFactory<>("driverID"));
        colDriverName.setCellValueFactory(new PropertyValueFactory<>("driverName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));


        editableCols();
        loadDrivers();
        

        tblDriver.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    private void editableCols() {
        colId.setCellFactory(TextFieldTableCell.forTableColumn());
        colId.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverID(e.getNewValue());
        });

        colDriverName.setCellFactory(TextFieldTableCell.forTableColumn());
        colDriverName.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDriverName(e.getNewValue());
        });

        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
        });

        colDOB.setCellFactory(TextFieldTableCell.forTableColumn());
        colDOB.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDob(e.getNewValue());
        });

        colContact.setCellFactory(TextFieldTableCell.forTableColumn());
        colContact.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setContact(e.getNewValue());
        });

        colNIC.setCellFactory(TextFieldTableCell.forTableColumn());
        colNIC.setOnEditCommit(e ->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNIC(e.getNewValue());
        });
        tblDriver.setEditable(true);
    }

    public void loadDrivers() throws Exception {
        ObservableList<DriverTM> tmList =
                FXCollections.observableArrayList();
        tmList.clear();
        List<driverDTO> allDrivers = bo.getAllDrivers();
        for (driverDTO dto : allDrivers
        ) {
            Button btn = new Button("Update");
            btn.setStyle("-fx-background-color: MediumSeaGreen");
            Button btn2 = new Button("Delete");
            btn2.setStyle("-fx-background-color: #a90e0e");
            DriverTM tm = new DriverTM(dto.getDriverId(), dto.getDriverName(),
                    dto.getAddress(), dto.getDob(), dto.getContact()+"", dto.getNIC(),btn,btn2);
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
                        String id = tblDriver.getSelectionModel().selectedItemProperty().get().getDriverID();
                        String name = tblDriver.getSelectionModel().selectedItemProperty().get().getDriverName();
                        String address = tblDriver.getSelectionModel().selectedItemProperty().get().getAddress();
                        String dob = tblDriver.getSelectionModel().selectedItemProperty().get().getDob();
                        String contact = tblDriver.getSelectionModel().selectedItemProperty().get().getContact();
                        String NIC = tblDriver.getSelectionModel().selectedItemProperty().get().getNIC();

                        if (bo.updateDriver(
                                id,name,address,dob,Integer.parseInt(contact),NIC)) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadDrivers();
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
                        if (bo.deleteDriver(tm.getDriverID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadDrivers();
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

        tblDriver.setItems(tmList);
    }

    public void driverNameOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        getDriver(txtDriverName.getText());
    }

    private void getDriver(String text) throws SQLException, ClassNotFoundException {
        ObservableList<DriverTM> tmList =
                FXCollections.observableArrayList();
        tmList.clear();
        List<driverDTO> allDrivers = bo.getDriver(text);
        for (driverDTO dto : allDrivers
        ) {
            Button btn = new Button("Update");
            Button btn2 = new Button("Delete");
            DriverTM tm = new DriverTM(dto.getDriverId(), dto.getDriverName(),
                    dto.getAddress(), dto.getDob(), dto.getContact()+"", dto.getNIC(),btn,btn2);
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
                        if (bo.updateDriver(tm.getDriverID(),tm.getDriverName(),tm.getAddress(),tm.getDob(),Integer.parseInt(tm.getContact()),tm.getNIC())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadDrivers();
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
                        if (bo.deleteDriver(tm.getDriverID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadDrivers();
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

        tblDriver.setItems(tmList);

    }

    private void initUI(String location) throws IOException {
        this.paneOfficer.getChildren().clear();
        this.paneOfficer.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void newOnAction(ActionEvent actionEvent) throws IOException {
        initUI("RegisterDriverForm.fxml");
    }
}
