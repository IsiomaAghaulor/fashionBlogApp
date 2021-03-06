package com.week9task.fashionblogapp.models;

public class UserRegistration {
    private String userName;
    private String password;
    private String passwordConfirmation;

    public UserRegistration(String userName, String password, String passwordConfirmation) {
        this.userName = userName;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
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

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
