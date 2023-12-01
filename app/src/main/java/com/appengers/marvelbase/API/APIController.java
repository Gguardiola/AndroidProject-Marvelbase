package com.appengers.marvelbase.API;


import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController extends AppCompatActivity {
    //APIController: Methods to handle the API calls
    Resources res = getResources();
    private String PRIVATEKEY = res.getString(R.string.PRIVATEKEY);
    private String PUBLICKEY = res.getString(R.string.PUBLICKEY);
    private String HASKEY = res.getString(R.string.HASHKEY);
    private String  ENDPOINT = res.getString(R.string.ENDPOINT);

//    public ArrayList<Characters> getCharacters(){
//
//    }
//    public ArrayList<Comics> getComics(){
//
//    }
//    public ArrayList<Creators> getCreators(){
//
//    }
//
//    //INDIVIDUAL CALLS
//    public Characters getCharacter(){
//
//    }
//    public Creators getCreator(){
//
//    }
//    public Comics getComic(){
//
//    }


}
