package com.dal.database.Login;

import java.io.Serializable;

public class UserDetails implements Serializable {

    private String userName = "";
    private String password = "";

    public String getUserName() {
        return userName;
    }

    public UserDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public String toString(){
        String data = "User Name = " + getUserName() + "\n" + "Password = " + getPassword() + "\n";
        return data;
    }

}
