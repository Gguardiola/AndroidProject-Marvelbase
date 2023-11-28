package com.appengers.marvelbase;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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