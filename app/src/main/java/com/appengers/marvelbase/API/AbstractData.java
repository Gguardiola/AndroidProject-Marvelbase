package com.appengers.marvelbase.API;

import java.util.ArrayList;

public abstract class AbstractData<T> {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private ArrayList<T> results;

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getTotal() {
        return total;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<T> getResults() {
        return results;
    }
}
