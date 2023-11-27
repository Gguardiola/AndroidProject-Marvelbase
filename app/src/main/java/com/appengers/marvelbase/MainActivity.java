package com.appengers.marvelbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnCharacters, btnComics, btnFavorites, btnCreators;
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
    }
}