package com.example.nguyendinhtrung_pk02294_asm.models;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("userId") // replace with the actual field name
    private int userId;

    @SerializedName("name") // replace with the actual field name
    private String name;

    @SerializedName("email") // replace with the actual field name
    private String email;

    @SerializedName("avatarUrl") // replace with the actual field name
    private String avatarUrl;

    // Add other fields as needed

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    // Add getter methods for other fields

    // You can also add setter methods if needed
}
