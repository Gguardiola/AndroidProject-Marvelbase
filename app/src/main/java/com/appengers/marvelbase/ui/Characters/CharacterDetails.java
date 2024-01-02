package com.appengers.marvelbase.ui.Characters;



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
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Creators.CreatorsActivity;
import com.appengers.marvelbase.ui.Creators.CreatorsDetails;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CharacterDetails extends AppCompatActivity {

    Button backBtn, addFavoriteBtn, showComicsBtn;
    ImageButton characterImg;
    TextView characterFullnameTxt, infoTxt, priceTxt;
    DBController db;
    Characters currentCharacter;
    Boolean isFavorite = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        db = new DBController(this);
        db.setUserId();

        Intent intent = getIntent();
        int characterId = intent.getIntExtra("characterId",0);
        characterFullnameTxt = (TextView) findViewById(R.id.characterName_txt);
        infoTxt = (TextView) findViewById(R.id.info_txt);
        backBtn = (Button) findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterDetails.this, CharacterActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        addFavoriteBtn = (Button) findViewById(R.id.addFavorite_btn);
        addFavoriteBtn.setEnabled(false);
        checkFavorite(characterId);
        addFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFavorite) {
                    db.addFavorite(DBController.Category.CHARACTERS, characterId);
                    Toast.makeText(CharacterDetails.this, "Added to favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Delete favorite");
                    isFavorite = true;
                }else{
                    db.deleteFavorite(DBController.Category.CHARACTERS, characterId);
                    Toast.makeText(CharacterDetails.this, "Deleted from favorites!", Toast.LENGTH_SHORT).show();
                    addFavoriteBtn.setText("Favorite");
                    isFavorite = false;
                }
            }
        });
       // showComicsBtn = (Button) findViewById(R.id.detailComics_btn);
        characterImg = (ImageButton) findViewById(R.id.detailCharacter_img);

        fetchCharacter(characterId);
    }

    public void checkFavorite(int characterId){
        db.checkFavorite(characterId, DBController.Category.CHARACTERS ,new  APICallback<Boolean>() {
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
                Log.e("API Error", "Error fetching characters: " + t.getMessage());
            }
        });
    }
    public void fetchCharacter(int characterId) {
        APIController api = new APIController(getResources());
        api.getCharacter(characterId, new APICallback<ArrayList<Characters>>() {

            @Override
            public void onSuccess(ArrayList<Characters> fetchedCharacter) {
                currentCharacter = fetchedCharacter.get(0);
                characterFullnameTxt.setText(currentCharacter.getName());
                infoTxt.setText(String.valueOf(currentCharacter.getDescription()));
                //Glide.with(getApplicationContext()).load(currentCharacter.getThumbnail()).fitCenter().into(characterImg);
                if (currentCharacter.getThumbnail() != null && currentCharacter.getThumbnail().path != null) {
                    String imageUrl = currentCharacter.getThumbnail().path + "." + currentCharacter.getThumbnail().extension;
                    // Cambia 'http' a 'https'
                    imageUrl = imageUrl.replace("http://", "https://");
                    Picasso.get().load(imageUrl).fit().into(characterImg);
                }
            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error fetching characters: " + t.getMessage());
            }
        });
    }

}