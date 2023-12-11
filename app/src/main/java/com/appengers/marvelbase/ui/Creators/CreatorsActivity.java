package com.appengers.marvelbase.ui.Creators;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class CreatorsActivity extends AppCompatActivity {

    RecyclerView creators_recycler;
    ArrayList<Creators> creatorsList;

    //Method that gets the creators (mandatory!)
    public void fetchCreators(CreatorsAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        api.getCreators(offset, limit, new APICallback<ArrayList<Creators>>() {

            @Override
            public void onSuccess(ArrayList<Creators> creatorsList) {
                adapter.setItems(creatorsList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);
        DBController db = new DBController(this);
        db.setUserId();
        //Retrieve the APIController object
        Intent intent = getIntent();
        creators_recycler = (RecyclerView)findViewById(R.id.creator_recyclerView);
        //mandatory! initialize the ArrayList
        creatorsList = new ArrayList<>();
        CreatorsAdapter adapter;
        adapter = new CreatorsAdapter(getApplicationContext(), creatorsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(getApplicationContext());
        creators_recycler.setLayoutManager(l);
        creators_recycler.setItemAnimator(new DefaultItemAnimator());
        creators_recycler.setAdapter(adapter);
        //mandatory! when the adapter is created, call the fetch
        fetchCreators(adapter, 0, 20);
    }
}
