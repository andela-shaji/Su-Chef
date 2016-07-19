package com.andela.suada.suchef.model;

public class Category {
    private String name;
    private int numOfPhotos;
    private int thumbnail;

    public Category() {

    }

    public Category(String name, int numOfPhotos, int thumbnail) {
        this.name = name;
        this.numOfPhotos = numOfPhotos;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfPhotos() {
        return numOfPhotos;
    }

    public void setNumOfPhotos(int numOfPhotos) {
        this.numOfPhotos = numOfPhotos;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
