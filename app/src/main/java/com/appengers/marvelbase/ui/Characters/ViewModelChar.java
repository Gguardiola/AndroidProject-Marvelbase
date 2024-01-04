package com.appengers.marvelbase.ui.Characters;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.appengers.marvelbase.Models.Characters;

import java.util.ArrayList;

public class ViewModelChar extends ViewModel {

    private MutableLiveData<String> name = new MutableLiveData<String>();
    private MutableLiveData<ArrayList<Characters>> charactersData = new MutableLiveData<>();

    public String getName() {
        return name.getValue();
    }

    public void setName(String n) {
        name.setValue(n);
    }

    public MutableLiveData<ArrayList<Characters>> getCharactersData() {
        return charactersData;
    }

    public void setCharactersData(ArrayList<Characters> charactersList) {
        charactersData.setValue(charactersList);
    }
}
