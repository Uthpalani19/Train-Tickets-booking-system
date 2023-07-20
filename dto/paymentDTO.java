package dto;

public class paymentDTO {
    private int RID;
    private String price;
    private String paymentType;

    public paymentDTO() {
    }

    public paymentDTO(int RID, String price, String paymentType) {
        this.RID = RID;
        this.price = price;
        this.paymentType = paymentType;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "paymentDTO{" +
                "RID=" + RID +
                ", price='" + price + '\'' +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
