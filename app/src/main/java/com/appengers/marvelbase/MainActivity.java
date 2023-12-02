package com.appengers.marvelbase;

import static android.content.ContentValues.TAG;

import static com.appengers.marvelbase.UserDataHandler.isFirstRun;
import static com.appengers.marvelbase.UserDataHandler.setFirstRun;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.API.DBController.Category;
import com.appengers.marvelbase.ui.Characters.CharacterActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCharacters, btnComics, btnFavorites, btnCreators;
    private ActivityResultLauncher<Intent> startCharacterAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

        Resources res = getResources();
        APIController apiController = new APIController(res);
        DBController db = new DBController(this);

        if (isFirstRun(this)) {
            String userId = setFirstRun(this, false);
            db.init(userId);
        }else{
            db.setUserId();
        }

        //TEST FIRESTORE
        //db.addFavorite(Category.CREATORS, 12345);
        //db.addFavorite(Category.CREATORS, 12345678);

        //db.addFavorite(Category.COMICS, 123123);
        //db.addFavorite(Category.COMICS, 231231231);

        //db.addFavorite(Category.CHARACTERS, 66666);
        //db.addFavorite(Category.CHARACTERS, 4545555);

        //db.deleteFavorite(Category.CHARACTERS, 4545555);
        //db.addFavorite(Category.CHARACTERS, 66666);

        btnCreators = (ImageButton) findViewById(R.id.creators_btn);
        btnCharacters = (ImageButton) findViewById(R.id.characters_btn);
        btnComics = (ImageButton) findViewById(R.id.comics_btn);
        btnFavorites = (ImageButton) findViewById(R.id.favorites_btn);

        btnCreators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Creator test", Toast.LENGTH_SHORT).show();
            }
        });
        btnCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CharacterActivity.class);
                startCharacterAct.launch(intent);
            }
        });
        startCharacterAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );

    }
}