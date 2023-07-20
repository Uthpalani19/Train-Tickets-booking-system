package entity;

public class customer implements SuperEntity{
    private String customerID;
    private String customerName;
    private String address;
    private String dob;
    private int contact;
    private String NIC;

    public customer() {
    }

    public customer(String customerID, String customerName, String address, String dob, int contact, String NIC) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.address = address;
        this.dob = dob;
        this.contact = contact;
        this.NIC = NIC;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        return "customerDTO{" +
                "customerID='" + customerID + '\'' +
                ", customerName='" + customerName + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", contact=" + contact +
                ", NIC='" + NIC + '\'' +
                '}';
    }
}
