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
    Button backBtn, addFavoriteBtn, showComicsBtn;
    ImageButton comicsImg;
    TextView comicsNameTxt, infoTxt, comicsPrice, favorites;
    DBController db;
    Comics currentComics;

    SearchView searchView;

    @SuppressLint("MissingInflatedId")
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
        favorites = (TextView) findViewById(R.id.favorites);
        searchView = findViewById(R.id.busca);
        // Set up a listener for the SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform the search operation here
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Perform filtering as the user types
                return true;
            }
        });

        // Set up a listener for the SearchView expansion
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform actions when the SearchView is expanded
                // For example, you can update the UI or show additional options
                Log.d("MSG", "HOLA");
                favorites.setVisibility(GONE);
            }
        });

        // Set up a listener for the SearchView collapse
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                // Perform actions when the SearchView is collapsed
                // For example, you can reset the UI or hide additional options
                favorites.setVisibility(View.VISIBLE);
                return false;
            }
        });
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
        comicsImg = (ImageButton) findViewById(R.id.comicsDetail_img);
        comicsPrice = (TextView) findViewById(R.id.comicsPrice_txt);

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
