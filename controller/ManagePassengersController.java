package controller;

import bo.BOFactory;
import bo.custom.customerBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.customerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import view.TM.customerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ManagePassengersController {
    public JFXTextField txtCustomerName;
    public TableView<customerTM> tblCustomer;
    public TableColumn <customerTM,String> colId;
    public TableColumn <customerTM,String> colCustName;
    public TableColumn <customerTM,String> colAddress;
    public TableColumn <customerTM,String> colDOB;
    public TableColumn <customerTM,String> colContact;
    public TableColumn <customerTM,String> colNIC;
    public TableColumn <customerTM,String> colUpdate;
    public TableColumn <customerTM,String> colDelete;
    public JFXButton btnNew;
    public AnchorPane paneOfficer;
    customerBO bo;

    public void initialize() throws Exception {
        bo= BOFactory.getInstance().getBO(BOFactory.BOType.CUSTOMER);

        colId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));


        editableCols();
        loadCustomers();
    }

    private void editableCols() {
        colId.setCellFactory(TextFieldTableCell.forTableColumn());
        colId.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomerID(e.getNewValue());
        });
        colCustName.setCellFactory(TextFieldTableCell.forTableColumn());
        colCustName.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCustomerName(e.getNewValue());
        });
        colAddress.setCellFactory(TextFieldTableCell.forTableColumn());
        colAddress.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setAddress(e.getNewValue());
        });
        colDOB.setCellFactory(TextFieldTableCell.forTableColumn());
        colDOB.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setDob(e.getNewValue());
        });
        colContact.setCellFactory(TextFieldTableCell.forTableColumn());
        colContact.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setContact(e.getNewValue());
        });
        colNIC.setCellFactory(TextFieldTableCell.forTableColumn());
        colNIC.setOnEditCommit(e->{
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNIC(e.getNewValue());
        });
        tblCustomer.setEditable(true);
    }

    public void loadCustomers() throws Exception {
        ObservableList<customerTM> tmList =
                FXCollections.observableArrayList();
        tmList.clear();
        List<customerDTO> allCustomers = bo.getAllCustomers();
        for (customerDTO dto : allCustomers
        ) {
            Button btn = new Button("Update");
            btn.setStyle("-fx-background-color: MediumSeaGreen");
            Button btn2 = new Button("Delete");
            btn2.setStyle("-fx-background-color: #a90e0e");
            customerTM tm = new customerTM(dto.getCustomerID(), dto.getCustomerName(),
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
                        if (bo.updateCustomer(tm.getCustomerID(),tm.getCustomerName(),tm.getAddress(),tm.getDob(),Integer.parseInt(tm.getContact()),tm.getNIC())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadCustomers();
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
                        if (bo.deleteCustomer(tm.getCustomerID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadCustomers();
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

        tblCustomer.setItems(tmList);
    }

    private void initUI(String location) throws IOException {
        this.paneOfficer.getChildren().clear();
        this.paneOfficer.getChildren().add(FXMLLoader.load(this.getClass().getResource("../view/"+location)));
    }

    public void newOnAction(ActionEvent actionEvent) throws IOException {
        initUI("RegisterCustomerForm.fxml");
    }

    public void custNameOnAction(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        getCustomer(txtCustomerName.getText());
    }

    private void getCustomer(String text) throws SQLException, ClassNotFoundException {

        ObservableList<customerTM> tmList = FXCollections.observableArrayList();
        tmList.clear();
        List<customerDTO> allCustomers = bo.getCustomer(text);
        for (customerDTO dto : allCustomers
        ) {
            Button btn = new Button("Update");
            Button btn2 = new Button("Delete");
            customerTM tm = new customerTM(dto.getCustomerID(), dto.getCustomerName(),
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
                        if (bo.updateCustomer(tm.getCustomerID(),tm.getCustomerName(),tm.getAddress(),tm.getDob(),Integer.parseInt(tm.getContact()),tm.getNIC())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Updated", ButtonType.OK).show();
                            loadCustomers();
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
                        if (bo.deleteCustomer(tm.getCustomerID())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadCustomers();
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
        tblCustomer.setItems(tmList);
    }
}
