package com.example.nguyendinhtrung_pk02294_asm.models;

public class ModelPutUser {
    private Integer id;
    private String email;
    private String name;
    private String password;
    private String avatar;

    public ModelPutUser() {
    }

    public ModelPutUser(Integer id, String email, String name, String password, String avatar) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
