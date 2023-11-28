package com.appengers.marvelbase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.appengers.marvelbase.Models.Characters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private charAdapter charAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        recyclerView = findViewById(R.id.recyChar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        charAdapter = new charAdapter();
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