package com.appengers.marvelbase.ui.Characters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.API.DBController.Category;
import com.appengers.marvelbase.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private CharacterAdapter charAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        Intent intent = getIntent();
        APIController api = new APIController(getResources());
        DBController db = new DBController(this);

        recyclerView = findViewById(R.id.recyChar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        charAdapter = new CharacterAdapter();
        recyclerView.setAdapter(charAdapter);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       // MarvelApiService appengers = retrofit.create(MarvelApiService.class);

       // getCharData(appengers, 1);
    }
    //private void getPokemonData(final MarvelApiService appengers, final int charNumber) {
    //    appengers.getChar(String.valueOf(charNumber)).enqueue(new Callback<Characters>() {
    //        @Override
    //        public void onResponse(Call<Characters> call, Response<Characters> response) {
    //            if (response.isSuccessful()) {
    //                Characters p = response.body();
    //                charAdapter.addCharacter(p);
//
    //                if (charNumber < 20) {
    //                    getPokemonData(appengers, charNumber + 1);
    //                }
    //            }
    //        }
//
    //        @Override
    //        public void onFailure(Call<Characters> call, Throwable t) {
//
    //        }
    //    });
    //}
}