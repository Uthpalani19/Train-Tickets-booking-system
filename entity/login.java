package entity;

public class login implements SuperEntity{

    private String userName;
    private String password;
    private String role;

    @Override
    public String toString() {
        return "login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public login(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public login() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
