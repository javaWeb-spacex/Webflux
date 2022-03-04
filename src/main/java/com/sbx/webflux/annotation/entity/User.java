package com.sbx.webflux.annotation.entity;


/**
 * @author :sbx
 * @date :2022/3/4 11:26
 * @description :
 * @version: :1.0.0
 */
public class User {
    private String userName;
    private String gender;
    private Integer age;

    public User() {
    }

    public User(String userName, String gender, Integer age) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
