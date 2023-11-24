package com.example.nguyendinhtrung_pk02294_asm.models;

public class NewsItem implements NewsData {
    private String title;
    private String content;
    private String time;
    private String author;
    private String topic;
    private int imageResourceId;

    public NewsItem(String title, String content, String time, String author, String topic, int imageResourceId) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.author = author;
        this.topic = topic;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getAuthor() {
        return author;
    }

    public String getTopic() {
        return topic;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

}
