package com.appengers.marvelbase;

import static com.appengers.marvelbase.UserDataHandler.isFirstRun;
import static com.appengers.marvelbase.UserDataHandler.setFirstRun;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.ui.Characters.FragCharacterActivity;
import com.appengers.marvelbase.ui.Comics.FragComicsActivity;
import com.appengers.marvelbase.ui.Creators.CreatorsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.appengers.marvelbase.ui.Favorites.FavoritesActivity;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCharacters, btnComics, btnFavorites, btnCreators;
    private ActivityResultLauncher<Intent> startCharacterAct, startCreatorAct, startComicsAct, startFavoritesAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

        APIController api = new APIController(getResources());
        DBController db = new DBController(this);

        if (isFirstRun(this)) {
            String userId = setFirstRun(this, false);
            db.init(userId);
        }else{
            db.setUserId();
        }

        btnCreators = (ImageButton) findViewById(R.id.creators_btn);
        btnCharacters = (ImageButton) findViewById(R.id.characters_btn);
        btnComics = (ImageButton) findViewById(R.id.comics_btn);
        btnFavorites = (ImageButton) findViewById(R.id.favorites_btn);

        Picasso.get().load("https://i.imgur.com/BZjjUob.png").fit().into(btnCharacters);
        Picasso.get().load("https://i.imgur.com/u4qkDYA.png").fit().into(btnCreators);
        Picasso.get().load("https://i.imgur.com/HU8rPIc.png").fit().into(btnComics);
        Picasso.get().load("https://i.imgur.com/WqWiTg0.png").fit().into(btnFavorites);

        btnCreators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CreatorsActivity.class);
                startCreatorAct.launch(intent);
            }
        });
        btnCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FragCharacterActivity.class);
                startCharacterAct.launch(intent);
            }
        });
        btnComics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FragComicsActivity.class);
                startComicsAct.launch(intent);
            }
        });
        btnFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FavoritesActivity.class);
                startFavoritesAct.launch(intent);
            }
        });
        // ACTIVITY INTENT STARTERS //
        startCreatorAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );
        startCharacterAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );
        startComicsAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );
        startFavoritesAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );

    }
}