package com.appengers.marvelbase.API;


import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.R;

public class APIController extends AppCompatActivity {
    //APIController: Methods to handle the API calls
    Resources res = getResources();
    private String PRIVATEKEY = res.getString(R.string.PRIVATEKEY);
    private String PUBLICKEY = res.getString(R.string.PUBLICKEY);
    private String HASKEY = res.getString(R.string.HASHKEY);
    private String  ENDPOINT = res.getString(R.string.ENDPOINT);


}
