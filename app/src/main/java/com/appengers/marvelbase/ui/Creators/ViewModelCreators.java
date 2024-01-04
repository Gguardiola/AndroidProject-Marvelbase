package com.appengers.marvelbase.ui.Creators;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Creators;

import java.util.ArrayList;

public class ViewModelCreators extends ViewModel {

    private MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<ArrayList<Creators>> creatorsData = new MutableLiveData<>();

    public String getName() {
        return name.getValue();
    }

    public void setName(String n) {
        name.setValue(n);
    }

    public MutableLiveData<ArrayList<Creators>> getCreatorsData() {
        return creatorsData;
    }

    public void setCreatorsData(ArrayList<Creators> creatorsList) {
        creatorsData.setValue(creatorsList);
    }
}
