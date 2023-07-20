package dto;

public class trainDTO {
    private String trainID;
    private String trainName;
    private String destination;
    private Double firstPrice;
    private Double secondPrice;
    private Double thirdPrice;

    public trainDTO() {
    }

    public trainDTO(String trainID, String trainName, String destination, Double firstPrice, Double secondPrice, Double thirdPrice) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.destination = destination;
        this.firstPrice = firstPrice;
        this.secondPrice = secondPrice;
        this.thirdPrice = thirdPrice;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getFirstPrice() {
        return firstPrice;
    }

    public void setFirstPrice(Double firstPrice) {
        this.firstPrice = firstPrice;
    }

    public Double getSecondPrice() {
        return secondPrice;
    }

    public void setSecondPrice(Double secondPrice) {
        this.secondPrice = secondPrice;
    }

    public Double getThirdPrice() {
        return thirdPrice;
    }

    public void setThirdPrice(Double thirdPrice) {
        this.thirdPrice = thirdPrice;
    }

    @Override
    public String toString() {
        return "trainDTO{" +
                "trainID='" + trainID + '\'' +
                ", trainName='" + trainName + '\'' +
                ", destination='" + destination + '\'' +
                ", firstPrice=" + firstPrice +
                ", secondPrice=" + secondPrice +
                ", thirdPrice=" + thirdPrice +
                '}';
    }
}
