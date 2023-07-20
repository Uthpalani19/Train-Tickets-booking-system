package dto;

public class reservationDTO {
    private int RID;
    private String customerID;
    private int scheduleID;
    private int classNo;
    private int seats;

    public reservationDTO() {
    }

    public reservationDTO(int RID, String customerID, int scheduleID, int classNo, int seats) {
        this.RID = RID;
        this.customerID = customerID;
        this.scheduleID = scheduleID;
        this.classNo = classNo;
        this.seats = seats;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}

