package com.techu.apitechu.models;

import org.springframework.http.HttpStatus;

// TODO: id should be generated automatically
public class UserModel extends GeneralModel {
    private String id;
    private String name;
    private Integer age;

    public UserModel() {
    }

    public UserModel(String errorMessage, HttpStatus httpStatus) {
        super(errorMessage, httpStatus);
    }

    public UserModel(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
