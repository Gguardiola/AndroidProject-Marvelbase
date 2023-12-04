package com.appengers.marvelbase.ui.Creators;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class CreatorsActivity extends AppCompatActivity {

    RecyclerView creators_recycler;
    ArrayList<Creators> creatorsList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creator);

        //Retrieve the APIController object
        Intent intent = getIntent();
        APIController api = new APIController(getResources());

        creators_recycler = (RecyclerView)findViewById(R.id.creator_recyclerView);
        //TODO: make de apicall in APIController to get the arrayList of Creators
        //creatorsList = api.getCreators();
        CreatorsAdapter adapter;
        adapter = new CreatorsAdapter(getApplicationContext(), creatorsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(getApplicationContext());
        creators_recycler.setLayoutManager(l);
        creators_recycler.setItemAnimator(new DefaultItemAnimator());
        creators_recycler.setAdapter(adapter);

    }
}
