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
    Resources res;

    private String PRIVATEKEY, PUBLICKEY, HASHKEY, ENDPOINT;

    public APIController(Resources res) {
        this.res = res;
        this.ENDPOINT = this.res.getString(R.string.ENDPOINT);
        this.PRIVATEKEY = this.res.getString(R.string.PRIVATEKEY);
        this.PUBLICKEY = this.res.getString(R.string.PUBLICKEY);
        this.HASHKEY =this.res.getString(R.string.HASHKEY);
    }



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
