package com.appengers.marvelbase.ui.Comics;

import static android.view.View.GONE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComicsDetails extends AppCompatActivity {
    Button backBtn, addFavoriteBtn;
    ImageButton comicsImg;
    TextView comicsNameTxt, infoTxt, comicsPrice;
    DBController db;
    Comics currentComics;
    Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics_details);
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
                if(!isFavorite) {
                    db.addFavorite(DBController.Category.COMICS, comicsId);
                    Toast.makeText(ComicsDetails.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Delete favorite");
                    isFavorite = true;
                }else{
                    db.deleteFavorite(DBController.Category.COMICS, comicsId);
                    Toast.makeText(ComicsDetails.this, "Deleted from favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Favorite");
                    isFavorite = false;
                }
            }
        });

        comicsImg = (ImageButton) findViewById(R.id.comicsDetail_img);
        comicsPrice = (TextView) findViewById(R.id.comicsPrice_txt);

        fetchComic(comicsId);
    }

    public void checkFavorite(int comicsId){
        db.checkFavorite(comicsId, DBController.Category.COMICS ,new  APICallback<Boolean>() {
            @Override
            public void onSuccess(Boolean exists) {
                addFavoriteBtn.setEnabled(true);
                if(exists) {
                    addFavoriteBtn.setText("Delete favorite");
                    isFavorite = true;
                }else{
                    addFavoriteBtn.setText("Favorite");
                    isFavorite = false;
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
                comicsPrice.setText(currentComics.getPrices());
                infoTxt.setText(String.valueOf(currentComics.getTitle()));
                String imageUrl = currentComics.getThumbnail().path + "." + currentComics.getThumbnail().extension;
                // Cambia 'http' a 'https'
                imageUrl = imageUrl.replace("http://", "https://");
                Picasso.get().load(imageUrl).fit().into(comicsImg);
            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }

}
