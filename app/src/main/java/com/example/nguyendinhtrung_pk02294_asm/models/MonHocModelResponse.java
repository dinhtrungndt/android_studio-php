package com.example.nguyendinhtrung_pk02294_asm.models;

public class MonHocModelResponse {
    private int id;
    private String tenmonhoc;

    public MonHocModelResponse() {
    }

    public MonHocModelResponse(int id, String tenmonhoc) {
        this.id = id;
        this.tenmonhoc = tenmonhoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenmonhoc() {
        return tenmonhoc;
    }

    public void setTenmonhoc(String tenmonhoc) {
        this.tenmonhoc = tenmonhoc;
    }
}
