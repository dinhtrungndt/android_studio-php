package com.example.nguyendinhtrung_pk02294_asm.models;

import android.widget.ImageView;

public class NewsModelResponse {
    private int id, user_id, topic_id;
    private String title, content, created_at;
    private String image;

    public NewsModelResponse() {
    }

    public NewsModelResponse(int id, int user_id, int topic_id, String title, String content, String created_at, String image) {
        this.id = id;
        this.user_id = user_id;
        this.topic_id = topic_id;
        this.title = title;
        this.content = content;
        this.created_at = created_at;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(int topic_id) {
        this.topic_id = topic_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image != null ? image : "";
    }

    public void setImage(String image) {
        this.image = image;
    }
}
