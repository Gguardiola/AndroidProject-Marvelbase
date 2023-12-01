package com.appengers.marvelbase.Models;

public class Comics {
    //Comics: Structure of the Comic objects received from API
    int id;
    String title;
    String pageCount;
    TextObjectsCom textObjects;
    SeriesCom series;
    PricesCom prices;
    ThumbnailCom thumbnail;
    CreatorsCom creators;
    public Comics(int id, String title, String pageCount, TextObjectsCom textObjects, SeriesCom series, PricesCom prices, ThumbnailCom thumbnail, CreatorsCom creators) {
        this.id = id;
        this.title = title;
        this.pageCount = pageCount;
        this.textObjects = textObjects;
        this.series = series;
        this.prices = prices;
        this.thumbnail = thumbnail;
        this.creators = creators;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public TextObjectsCom getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(TextObjectsCom textObjects) {
        this.textObjects = textObjects;
    }

    public SeriesCom getSeries() {
        return series;
    }

    public void setSeries(SeriesCom series) {
        this.series = series;
    }

    public PricesCom getPrices() {
        return prices;
    }

    public void setPrices(PricesCom prices) {
        this.prices = prices;
    }

    public ThumbnailCom getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ThumbnailCom thumbnail) {
        this.thumbnail = thumbnail;
    }

    public CreatorsCom getCreators() {
        return creators;
    }

    public void setCreators(CreatorsCom creators) {
        this.creators = creators;
    }

    public class TextObjectsCom {
        public String type;
        public String language;
        public String text;
    }
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
    public class CreatorsCom {
        ItemsCom items;
    }
    public class ItemsCom {
        String resourceURI;
        String role;
    }
}
