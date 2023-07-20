package bo.custom.impl;

import bo.custom.trainBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dao.custom.trainDAO;
import dto.trainDTO;
import entity.train;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class trainBOImpl implements trainBO {
    trainDAO dao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.TRAIN);
    private QueryDAO qdao= DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUERY);

    @Override
    public String getID() throws Exception {
        return qdao.getTrainId();
    }

    @Override
    public boolean saveTrain(trainDTO dto) throws Exception {
        return dao.save(
                new train(
                        dto.getTrainID(),
                        dto.getTrainName(),
                        dto.getDestination(),
                        dto.getFirstPrice(),
                        dto.getSecondPrice(),
                        dto.getThirdPrice()
                )
        );
    }

    @Override
    public ArrayList<trainDTO> getAllTrains() throws Exception {
        List<train> cList=dao.getAll();
        ArrayList<trainDTO> dtoList=new ArrayList();
        for(train c:cList){
            dtoList.add(new trainDTO(c.getTrainID(),c.getTrainName(),c.getDestination(),c.getFirstPrice(),c.getSecondPrice(),c.getThirdPrice()));
        }
        return dtoList;
    }

    @Override
    public boolean deleteTrain(String trainID) throws Exception {
        return dao.delete(trainID);
    }

    @Override
    public boolean updateTrain(String trainID, String trainName, String destination, double parseInt, double parseInt1, double parseInt2) throws Exception {
        return dao.update(
                new train(
                        trainID,
                        trainName,
                        destination,
                        parseInt,
                        parseInt1,
                        parseInt2
                )
        );
    }

    @Override
    public ArrayList<trainDTO> getTrain(String text) throws SQLException, ClassNotFoundException {
        List<train> cList=qdao.getTrain(text);
        ArrayList<trainDTO> dtoList=new ArrayList();
        for(train c:cList){
            dtoList.add(new trainDTO(c.getTrainID(),c.getTrainName(),c.getDestination(),c.getFirstPrice(),c.getSecondPrice(),c.getThirdPrice()));
        }
        return dtoList;
    }

    @Override
    public trainDTO getTrainDetails(String s) throws Exception {
        train train=dao.get(s);
        return new trainDTO(
          train.getTrainID(),
          train.getTrainName(),
          train.getDestination(),
          train.getFirstPrice(),
          train.getSecondPrice(),
          train.getThirdPrice()
        );
    }

}
