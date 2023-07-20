package entity;

public class driver implements SuperEntity{
    private String driverId;
    private String driverName;
    private String address;
    private String dob;
    private int contact;
    private String NIC;

    public driver() {
    }

    public driver(String driverId, String driverName, String address, String dob, int contact, String NIC) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.NIC = NIC;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    @Override
    public String toString() {
        return "driver{" +
                "driverId='" + driverId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", contact=" + contact +
                ", NIC='" + NIC + '\'' +
                '}';
    }
}
