package entity.custom;

public class defaultt {
    private String trainID;
    private String trainName;
    private String time;
    private String destination;

    public defaultt() {
    }

    public defaultt(String trainID, String trainName, String time, String destination) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.time = time;
        this.destination = destination;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
