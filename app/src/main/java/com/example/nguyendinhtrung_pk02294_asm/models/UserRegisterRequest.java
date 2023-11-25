package com.example.nguyendinhtrung_pk02294_asm.models;

public class UserRegisterRequest {
    private String email;
    private String name;
    private String password;
    private String password_confirm;

    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String email, String name, String password, String password_confirm) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.password_confirm = password_confirm;
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

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
}
