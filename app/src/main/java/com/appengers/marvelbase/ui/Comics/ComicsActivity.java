package com.appengers.marvelbase.ui.Comics;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class ComicsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Comics> comicsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);

        Intent intent = getIntent();
        APIController api = new APIController(getResources());

        recyclerView = findViewById(R.id.recycComics);
        comicsList = new ArrayList<>();
        ComicsAdapter comicsAdapter = new ComicsAdapter(getApplicationContext(), comicsList);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(comicsAdapter);

        getComicsData(comicsAdapter);
    }

    private void getComicsData(ComicsAdapter adapter) {
        APIController appengers = new APIController(getResources());

        appengers.getComics(0, 20, new APICallback<ArrayList<Comics>>() {
            @Override
            public void onSuccess(ArrayList<Comics> comicsList) {
                adapter.setItems(comicsList);
                adapter.notifyDataSetChanged();
                Log.d("ComicsActivity", "API Call successful. Number of comics: " + comicsList.size());
            }

            @Override
            public void onError(Throwable t) {
                Log.e("ComicsActivity", "Error al cargar el personaje", t);
            }
        });
    }
}
