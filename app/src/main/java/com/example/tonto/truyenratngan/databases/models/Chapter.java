package com.example.tonto.truyenratngan.databases.models;

/**
 * Created by tonto on 5/14/2017.
 */

public class Chapter {
    private int id;
    private String title;
    private String Content;

    public Chapter(int id, String title, String content) {
        this.id = id;
        this.title = title;
        Content = content;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return Content;
    }
}
