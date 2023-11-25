package com.example.nguyendinhtrung_pk02294_asm.models;

public class UserModelResponse {
    private int id;
    private String name;

    public UserModelResponse() {
    }

    public UserModelResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
