package com.example.nguyendinhtrung_pk02294_asm.models;

public class TranscriptsModelResponse {
    private int id, point;
    private String name;

    public TranscriptsModelResponse() {
    }

    public TranscriptsModelResponse(int id, int point, String name) {
        this.id = id;
        this.point = point;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
