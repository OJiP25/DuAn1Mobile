package vn.edu.poly.duan1mobile.model;

public class User {
    String Username;
    String Password;

    public User(String username, String password) {
        Username = username;
        Password = password;
    }

    public User(){

    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String toSring(){
        return "User{"+"username = "+ Username +
                ",password = "+Password +")";
    }

}
