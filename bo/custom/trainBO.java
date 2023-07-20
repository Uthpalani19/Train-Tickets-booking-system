package bo.custom;

import dto.custom.defaultDTO;
import dto.trainDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface trainBO {
    public String getID() throws Exception;
    public boolean saveTrain(trainDTO dto) throws Exception;
    public ArrayList<trainDTO> getAllTrains() throws Exception;

    public boolean deleteTrain(String trainID) throws Exception;

    public boolean updateTrain(String trainID, String trainName, String destination, double parseInt, double parseInt1, double parseInt2) throws Exception;

    public ArrayList<trainDTO> getTrain(String text) throws SQLException, ClassNotFoundException;

    public trainDTO getTrainDetails(String s) throws Exception;


    //public defaultDTO getTrains(String s);
}
