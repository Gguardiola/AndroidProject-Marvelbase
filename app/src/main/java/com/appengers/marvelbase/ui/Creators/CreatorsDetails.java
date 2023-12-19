package com.appengers.marvelbase.ui.Creators;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreatorsDetails extends AppCompatActivity {
    
    Button backBtn, addFavoriteBtn, showComicsBtn;
    ImageButton creatorImg;
    TextView creatorFullnameTxt, infoTxt;
    DBController db;
    Creators currentCreator;
    Boolean isFavorite = false;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creators_details);
        db = new DBController(this);
        db.setUserId();

        Intent intent = getIntent();
        int creatorId = intent.getIntExtra("creatorId",0);
        creatorFullnameTxt = (TextView) findViewById(R.id.creatorsName_txt);
        infoTxt = (TextView) findViewById(R.id.info_txt);
        backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatorsDetails.this, CreatorsActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        addFavoriteBtn = (Button) findViewById(R.id.addFavorite_btn);
        addFavoriteBtn.setEnabled(false);
        checkFavorite(creatorId);
        addFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFavorite) {
                    db.addFavorite(DBController.Category.CREATORS, creatorId);
                    Toast.makeText(CreatorsDetails.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Delete favorite");
                    isFavorite = true;
                }else{
                    db.deleteFavorite(DBController.Category.CREATORS, creatorId);
                    Toast.makeText(CreatorsDetails.this, "Deleted from favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Favorite");
                    isFavorite = false;
                }
            }
        });
        showComicsBtn = (Button) findViewById(R.id.detailComics_btn);
        creatorImg = (ImageButton) findViewById(R.id.detailCreator_img);

        fetchCreator(creatorId);
    }

    public void checkFavorite(int creatorId){
        db.checkFavorite(creatorId, DBController.Category.CREATORS ,new  APICallback<Boolean>() {
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
    public void fetchCreator(int creatorId) {
        APIController api = new APIController(getResources());
        api.getCreator(creatorId, new APICallback<ArrayList<Creators>>() {

            @Override
            public void onSuccess(ArrayList<Creators> fetchedCreator) {
                currentCreator = fetchedCreator.get(0);
                creatorFullnameTxt.setText(currentCreator.getFirstName());
                infoTxt.setText(String.valueOf(currentCreator.getFirstName()+" "+currentCreator.getLastName()));
                Glide.with(getApplicationContext()).load(currentCreator.getThumbnail()).fitCenter().into(creatorImg);

            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
}