package com.appengers.marvelbase.ui.Comics;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appengers.marvelbase.Models.Comics;

import java.util.ArrayList;

public class ViewModelComics extends ViewModel {

    private MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<ArrayList<Comics>> comicsData = new MutableLiveData<>();

    public String getName() {
        return name.getValue();
    }

    public void setName(String n) {
        name.setValue(n);
    }

    public MutableLiveData<ArrayList<Comics>> getComicsData() {
        return comicsData;
    }

    public void setComicsData(ArrayList<Comics> comicsList) {
        comicsData.setValue(comicsList);
    }
    public void clearComicsData() {
        comicsData.setValue(new ArrayList<>());
    }
}
