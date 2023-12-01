package com.appengers.marvelbase;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.ui.Characters.CharacterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    APIController apiController = new APIController();


    private ImageButton btnCharacters, btnComics, btnFavorites, btnCreators;
    private ActivityResultLauncher<Intent> startCharacterAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

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