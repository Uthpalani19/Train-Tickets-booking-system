package entity;

public class reservation implements SuperEntity{
    private String RID;
    private String customerID;
    private int scheduleID;
    private int classNo;
    private int seats;

    public reservation() {
    }

    public reservation(String RID, String customerID, int scheduleID, int classNo, int seats) {
        this.RID = RID;
        this.customerID = customerID;
        this.scheduleID = scheduleID;
        this.classNo = classNo;
        this.seats = seats;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
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

