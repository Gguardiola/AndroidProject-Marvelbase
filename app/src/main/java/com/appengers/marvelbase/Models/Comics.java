package com.appengers.marvelbase.Models;

import java.util.ArrayList;
import java.util.List;

public class Comics {
    int id;
    String title;
    String pageCount;
    SeriesCom series;
    ThumbnailCom thumbnail;
    List<Prices> prices = new ArrayList<>();
    List<TextObject> textObjects = new ArrayList<>();
    List<Url> urls = new ArrayList<>();
    Creator creators;

    public static class Prices {
        public String type;
        public Float price;
    }

    public class SeriesCom {
        public String resourceURI;
        public String name;
    }

    public class ThumbnailCom {
        public String path;
        public String extension;
    }

    public static class TextObject {
        public String type;
        public String language;
        public String text;
    }

    public static class Url {
        public String type;
        public String url;
    }

    public class Creator {
        int available;
        String collectionURI;
        List<ItemsCom> items = new ArrayList<>();
        int returned;
    }

    public class ItemsCom {
        String resourceURI;
        String role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getPageCount() {
        return pageCount;
    }

    public SeriesCom getSeries() {
        return series;
    }

    public List<Prices> getPrices() {
        return prices;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public ThumbnailCom getThumbnail() {
        return thumbnail;
    }

    public Creator getCreators() {
        return creators;
    }

    public void setCreators(Creator creators) {
        this.creators = creators;
    }
}
