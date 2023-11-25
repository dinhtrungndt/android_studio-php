package com.example.nguyendinhtrung_pk02294_asm.models;

public class LichHocModelResponse {
    private int id, monhoc_id,user_id;
    private String ngayhoc, diadiem, ca;

    public LichHocModelResponse() {
    }

    public LichHocModelResponse(int id, int monhoc_id, int user_id, String ngayhoc, String diadiem, String ca) {
        this.id = id;
        this.monhoc_id = monhoc_id;
        this.user_id = user_id;
        this.ngayhoc = ngayhoc;
        this.diadiem = diadiem;
        this.ca = ca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonhoc_id() {
        return monhoc_id;
    }

    public void setMonhoc_id(int monhoc_id) {
        this.monhoc_id = monhoc_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNgayhoc() {
        return ngayhoc;
    }

    public void setNgayhoc(String ngayhoc) {
        this.ngayhoc = ngayhoc;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }
}
