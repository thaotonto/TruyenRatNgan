package com.example.tonto.truyenratngan.databases.models;

import java.io.Serializable;

/**
 * Created by tonto on 4/18/2017.
 */

public class Story implements Serializable{
    private int id;
    private String title;
    private String description;
    private String author;
    private String genre;
    private String image;
    private boolean isFavorite;
    private int lastChapterNo;

    public Story(int id, String title, String description, String author, String genre, String image, boolean isFavorite, int lastChapterNo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.genre = genre;
        this.image = image;
        this.isFavorite = isFavorite;
        this.lastChapterNo = lastChapterNo;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getImage() {
        return image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getLastChapterNo() {
        return lastChapterNo;
    }

    @Override
    public String toString() {
        return title;
    }

    public void setLastChapterNo(int lastChapterNo) {
        this.lastChapterNo = lastChapterNo;
    }
}
