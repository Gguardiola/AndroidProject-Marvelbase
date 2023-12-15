package com.appengers.marvelbase.ui.Comics;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ComicsDetails extends AppCompatActivity {
    Button backBtn, addFavoriteBtn, showComicsBtn;
    ImageButton comicsImg;
    TextView comicsNameTxt, infoTxt;
    DBController db;
    Comics currentComics;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creators_details);
        db = new DBController(this);
        db.setUserId();

        Intent intent = getIntent();
        int comicsId = intent.getIntExtra("comicsId",0);
        comicsNameTxt = (TextView) findViewById(R.id.comicsName_txt);
        infoTxt = (TextView) findViewById(R.id.info_txt);
        backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComicsDetails.this, ComicsActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        addFavoriteBtn = (Button) findViewById(R.id.addFavorite_btn);
        addFavoriteBtn.setEnabled(false);
        checkFavorite(comicsId);
        addFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addFavorite(DBController.Category.COMICS, comicsId);
                addFavoriteBtn.setEnabled(false);
                Toast.makeText(ComicsDetails.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
            }
        });
        showComicsBtn = (Button) findViewById(R.id.detailComics_btn);
        comicsImg = (ImageButton) findViewById(R.id.detailCreator_img);

        fetchComic(comicsId);
    }

    public void checkFavorite(int comicsId){
        db.checkFavorite(comicsId, DBController.Category.COMICS ,new  APICallback<Boolean>() {
            @Override
            public void onSuccess(Boolean exists) {
                if(exists) {
                    addFavoriteBtn.setEnabled(false);
                }else{
                    addFavoriteBtn.setEnabled(true);
                }
            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
    public void fetchComic(int comicsId) {
        APIController api = new APIController(getResources());
        api.getComic(comicsId, new APICallback<ArrayList<Comics>>() {

            @Override
            public void onSuccess(ArrayList<Comics> fetchedComics) {
                currentComics = fetchedComics.get(0);
                comicsNameTxt.setText(currentComics.getTitle());
                infoTxt.setText(String.valueOf(currentComics.getTitle()));
                Glide.with(getApplicationContext()).load(currentComics.getThumbnail()).fitCenter().into(comicsImg);

            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
}
