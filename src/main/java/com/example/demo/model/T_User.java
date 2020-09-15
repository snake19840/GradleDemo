package com.example.demo.model;

import javax.xml.crypto.Data;

public class T_User {
    private  int id;
    private  String user_name;
    private  String password;
    private Data last_login_time;
    private boolean sex;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Data getLast_login_time() {
        return last_login_time;
    }

    public void setLast_login_time(Data last_login_time) {
        this.last_login_time = last_login_time;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "T_User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", last_login_time=" + last_login_time +
                ", sex=" + sex +
                '}';
    }

    public T_User(int id, String user_name, String password, Data last_login_time, boolean sex) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.last_login_time = last_login_time;
        this.sex = sex;
    }

    public T_User() {
        super();
    }
}
