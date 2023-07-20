package dto;

public class scheduleDTO {
    private int SID;
    private String trainID;
    private String driverId;
    private String DATE;
    private String TIME;
    private int firstseats;
    private int secondSeats;
    private int thirdSeats;

    public scheduleDTO() {
    }

    public scheduleDTO(String trainID, String driverId, String DATE, String TIME, int firstseats, int secondSeats, int thirdSeats) {
        this.trainID = trainID;
        this.driverId = driverId;
        this.DATE = DATE;
        this.TIME = TIME;
        this.firstseats = firstseats;
        this.secondSeats = secondSeats;
        this.thirdSeats = thirdSeats;
    }

    public scheduleDTO(int SID, String trainID, String driverId, String DATE, String TIME, int firstseats, int secondSeats, int thirdSeats) {
        this.SID = SID;
        this.trainID = trainID;
        this.driverId = driverId;
        this.DATE = DATE;
        this.TIME = TIME;
        this.firstseats = firstseats;
        this.secondSeats = secondSeats;
        this.thirdSeats = thirdSeats;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getTrainID() {
        return trainID;
    }

    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
    }

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public int getFirstseats() {
        return firstseats;
    }

    public void setFirstseats(int firstseats) {
        this.firstseats = firstseats;
    }

    public int getSecondSeats() {
        return secondSeats;
    }

    public void setSecondSeats(int secondSeats) {
        this.secondSeats = secondSeats;
    }

    public int getThirdSeats() {
        return thirdSeats;
    }

    public void setThirdSeats(int thirdSeats) {
        this.thirdSeats = thirdSeats;
    }
}
