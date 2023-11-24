package com.example.nguyendinhtrung_pk02294_asm.models;

public class UserLoginResponse {
//    nhận về
    private Boolean status;
    private UserModel user;

    public UserLoginResponse() {
    }

    public UserLoginResponse(Boolean status, UserModel user) {
        this.status = status;
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
