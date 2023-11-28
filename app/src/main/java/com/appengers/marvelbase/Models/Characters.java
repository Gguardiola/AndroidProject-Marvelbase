package com.appengers.marvelbase.Models;

public class Characters {
    //Characters: Structure of the Character objects received from APIç

    int id;
    String name;
    String description;
    Thumbnail thumbnail;

    ComicsChar comics;
    SeriesChar series;

    public class Thumbnail {
        public String path;
        public String extension;
    }

    public class ComicsChar {
        public int available;
        public Items items;
    }

    public class SeriesChar {
        public int available;
        public Items items;
    }
    public class Items {
        public String resourceURI;
        public String name;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
