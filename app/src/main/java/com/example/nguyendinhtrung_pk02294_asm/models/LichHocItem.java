package com.example.nguyendinhtrung_pk02294_asm.models;

public class LichHocItem {
    private String monHoc;
    private String thoiGian;

    public LichHocItem(String monHoc, String thoiGian) {
        this.monHoc = monHoc;
        this.thoiGian = thoiGian;
    }

    public String getMonHoc() {
        return monHoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }
}
