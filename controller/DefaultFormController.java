package controller;

import bo.BOFactory;
import bo.custom.scheduleBO;
import bo.custom.trainBO;
import dto.custom.defaultDTO;
import dto.customerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import view.TM.custom.defaultTM;
import view.TM.customerTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultFormController {

    @FXML
    private ImageView paneDashBoardOfiicer;

    @FXML
    private ImageView imgLogout;

    @FXML
    private TableView<defaultTM> tblTrain;

    @FXML
    private TableColumn<?, ?> colTrainID;

    @FXML
    private TableColumn<?, ?> colTrainName;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colDriverName;

    @FXML
    private TableColumn<?, ?> colDestination;
    scheduleBO bo2;


    public void initialize() throws SQLException, ClassNotFoundException {
        bo2= BOFactory.getInstance().getBO(BOFactory.BOType.SCHEDULE);

        colTrainID.setCellValueFactory(new PropertyValueFactory<>("trainID"));
        colTrainName.setCellValueFactory(new PropertyValueFactory<>("trainName"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));

        String date=LocalDate.now().toString();
        ArrayList<defaultDTO> list=bo2.selectTrains(date);
        loadTable(list);
    }

    private void loadTable(ArrayList<defaultDTO> list) {
        ObservableList<defaultTM> tmList = FXCollections.observableArrayList();
        tmList.clear();
        for (defaultDTO dto : list
        ) {
            defaultTM tm = new defaultTM(dto.getTrainID(), dto.getTrainName(), dto.getTime(), dto.getDestination());
            tmList.add(tm);
        }
        tblTrain.setItems(tmList);
    }
}
