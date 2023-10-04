package com.example.retrofit2;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("userId")
    Integer UserId;
    @SerializedName("id")
    int id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;

    public PostModel(Integer userId, String title, String body) {
        UserId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return UserId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "UserId=" + UserId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
