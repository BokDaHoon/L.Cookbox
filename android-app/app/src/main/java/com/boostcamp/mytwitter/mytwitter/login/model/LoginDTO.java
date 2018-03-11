package com.boostcamp.mytwitter.mytwitter.login.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DaHoon on 2017-07-24.
 */

public class LoginDTO implements Serializable {
    @SerializedName("MBR_ID")
    private String mbrId;
    @SerializedName("PASSWORD")
    private String password;
    @SerializedName("NAME")
    private String name;
    @SerializedName("SEX")
    private String sex;
    @SerializedName("AGE")
    private int age;
    @SerializedName("PHONENUMBER")
    private String phoneNumber;
    @SerializedName("ADDRESS")
    private String address;

    public String getMbrId() {
        return mbrId;
    }

    public void setMbrId(String mbrId) {
        this.mbrId = mbrId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "mbrId='" + mbrId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
