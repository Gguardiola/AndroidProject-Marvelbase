package com.appengers.marvelbase.Models;

import java.io.Serializable;
import java.util.List;

public class Creators implements Serializable {
    //Creators: Structure of the Creator objects received from API

    int id;

    String firstName;
    String lastName;

    Thumbnail thumbnail;
    Comics comics;
    public class Thumbnail {
        public String path;
        public String extension;
    }


    public class ComicsCreator {
        public int available;
        public List<Comic> items;
    }
    public class Comic {
        public String resourceURI;
        public String name;
    }
    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

    public String getThumbnail() {
        return thumbnail.path+"."+thumbnail.extension;
    }

    public Comics getComics(){return comics;}
}
