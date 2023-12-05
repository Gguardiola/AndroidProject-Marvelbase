package com.appengers.marvelbase.ui.Comics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class ComicsActivity extends AppCompatActivity {
    RecyclerView listofComics;
    ArrayList<Comics> lComics;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_comic);

        listofComics = (RecyclerView) findViewById(R.id.recyChar);
    }
}
