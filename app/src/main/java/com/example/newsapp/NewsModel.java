package com.example.newsapp;

//variables represent all data for items
public class NewsModel {
    // variables
    String newsTitle;
    String newsDescription;
    int image;

    // constructor
    public NewsModel(String newsTitle, String newsDescription, int image) {
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
        this.image = image;
    }

    //getters

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public int getImage() {
        return image;
    }


}
