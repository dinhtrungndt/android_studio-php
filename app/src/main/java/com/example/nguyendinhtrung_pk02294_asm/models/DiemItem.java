package com.example.nguyendinhtrung_pk02294_asm.models;

public class DiemItem {
    private String monHoc;
    private String diem;

    public DiemItem(String monHoc, String diem) {
        this.monHoc = monHoc;
        this.diem = diem;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public String getDiem() {
        return diem;
    }
}
