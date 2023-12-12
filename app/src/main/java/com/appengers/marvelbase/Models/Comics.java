package com.appengers.marvelbase.Models;

import java.util.List;

public class Comics {
    //Comics: Structure of the Comic objects received from API
    int id;
    String title;
    String pageCount;
    SeriesCom series;

    ThumbnailCom thumbnail;
    //List<Creator> creators;
    public List<Prices> prices;

    public static class Prices {
        public String type;
        public Float price;
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
    public ThumbnailCom getThumbnail() {
        return thumbnail;
    }
//    public List<Creator> getCreators() {
//        return creators;
//    }
//    public void setCreators(List<Creator> creators) {
//        this.creators = creators;
//    }

    public class SeriesCom {
        public String resourceURI;
        public String name;
    }
    public class PricesCom {
        public String type;
        public Float price;
    }
    public class ThumbnailCom {
        public String path;
        public String extension;
    }
//    public static class Creator {
//        Object items;
//    }
    public class ItemsCom {
        String resourceURI;
        String role;
    }
}
