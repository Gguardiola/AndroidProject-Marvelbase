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
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class ComicsActivity extends AppCompatActivity {
    RecyclerView comicsRecycler;
    ArrayList<Comics> comicsList;
    public void fetchComics(ComicsAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        api.getComics(offset, limit, new APICallback<ArrayList<Comics>>() {

            @Override
            public void onSuccess(ArrayList<Comics> comicsList) {
                adapter.setItems(comicsList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching comics: " + t.getMessage());
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic);
        DBController db = new DBController(this);
        db.setUserId();
        //Retrieve the APIController object
        Intent intent = getIntent();
        comicsRecycler = (RecyclerView)findViewById(R.id.comicsRecycler);
        //mandatory! initialize the ArrayList
        comicsList = new ArrayList<>();
        ComicsAdapter adapter;
        adapter = new ComicsAdapter(getApplicationContext(), comicsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(getApplicationContext());
        comicsRecycler.setLayoutManager(l);
        comicsRecycler.setItemAnimator(new DefaultItemAnimator());
        comicsRecycler.setAdapter(adapter);
        //mandatory! when the adapter is created, call the fetch
        fetchComics(adapter, 0, 20);
    }
}
