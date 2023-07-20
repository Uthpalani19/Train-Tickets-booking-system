package entity;

public class payment implements SuperEntity{
    private String RID;
    private String paymentType;
    private double price;

    public payment() {
    }

    public payment(String RID, String paymentType, double price) {
        this.RID = RID;
        this.paymentType = paymentType;
        this.price = price;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
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
