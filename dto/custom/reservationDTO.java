package dto.custom;

public class reservationDTO {
    private String RID;
    private String CID;
    private int SID;
    private int classNO;
    private int seats;
    private String paymentType;
    private double price;

    public reservationDTO() {
    }

    public reservationDTO(String RID, String CID, int SID, int classNO, int seats, String paymentType, double price) {
        this.RID = RID;
        this.CID = CID;
        this.SID = SID;
        this.classNO = classNO;
        this.seats = seats;
        this.paymentType = paymentType;
        this.price = price;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public int getClassNO() {
        return classNO;
    }

    public void setClassNO(int classNO) {
        this.classNO = classNO;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
