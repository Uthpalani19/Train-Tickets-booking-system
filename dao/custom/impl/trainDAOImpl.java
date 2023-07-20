package dao.custom.impl;


import dao.CrudUtil;
import dao.custom.trainDAO;
import entity.customer;
import entity.train;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class trainDAOImpl implements trainDAO {
    @Override
    public boolean save(train train) throws Exception {
        return CrudUtil.execute("INSERT INTO train values(?,?,?,?,?,?)",train.getTrainID(),train.getTrainName(),train.getDestination(),train.getFirstPrice(),train.getSecondPrice(),train.getThirdPrice());
    }

    @Override
    public boolean update(train train) throws Exception {
        return CrudUtil.execute("UPDATE train set trainName=?,destination=?,firstPrice=?,secondPrice=?,thirdPrice=? where trainID=?",train.getTrainName(),train.getDestination(),train.getFirstPrice(),train.getSecondPrice(),train.getThirdPrice(), train.getTrainID());
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE from train where trainID=?",s);
    }

    @Override
    public train get(String s) throws Exception {
        ResultSet rst=CrudUtil.execute("SELECT * FROM train WHERE trainId=?",s);
        if(rst.next()){
            return new train(
              rst.getString(1),
              rst.getString(2),
              rst.getString(3),
              rst.getDouble(4),
              rst.getDouble(5),
              rst.getDouble(6)
            );
        }
        return null;
    }

    @Override
    public List<train> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM train");
        ArrayList<train> trainList = new ArrayList<>();
        while (resultSet.next()) {
            trainList.add(
                    new train(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getDouble(4),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return trainList;
    }
}
