package com.appengers.marvelbase.Models;

import com.google.gson.Gson;

import java.io.Serializable;

public class Characters implements Serializable {
    // Characters: Structure of the Character objects received from API

    int id;
    String name;
    String description;
    Thumbnail thumbnail;

    public class Thumbnail {
        public String path;
        public String extension;
    }

    public String getName() {
        return name;
    }

    public int getId(){return id;}

    public String getDescription(){
        return description;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }
}
