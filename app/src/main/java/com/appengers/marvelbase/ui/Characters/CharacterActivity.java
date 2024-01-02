package com.appengers.marvelbase.ui.Characters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class CharacterActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Characters> charactersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Intent intent = getIntent();
        APIController api = new APIController(getResources());

        recyclerView = findViewById(R.id.recyChar);
        charactersList = new ArrayList<>();
        CharacterAdapter characterAdapter = new CharacterAdapter(getApplicationContext(), charactersList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterAdapter);

        getCharData(characterAdapter);
    }

    private void getCharData(CharacterAdapter adapter) {
        APIController appengers = new APIController(getResources());

        appengers.getChar(0, 20, new APICallback<ArrayList<Characters>>() {
            @Override
            public void onSuccess(ArrayList<Characters> charactersList) {
                adapter.setItems(charactersList);
                adapter.notifyDataSetChanged();
                Log.d("CharacterActivity", "API Call successful. Number of characters: " + charactersList.size());
            }

            @Override
            public void onError(Throwable t) {
                Log.e("CharacterActivity", "Error al cargar el personaje", t);
            }
        });
    }
}
