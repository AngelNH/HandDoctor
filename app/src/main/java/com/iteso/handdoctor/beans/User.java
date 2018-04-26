package com.iteso.handdoctor.beans;

/**
 * Created by inqui on 25/04/2018.
 */

public class User {
    private String user;
    private String password;
    private int state;
    private String id;

    public User(){}

    /*public User(String user, String password) {
        this.user = user;
        this.password = password;
    }*/

    public User(String id, String user, String password, int state) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.state = state;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", id='" + id + '\'' +
                '}';
    }
}
