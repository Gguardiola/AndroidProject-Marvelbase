package com.appengers.marvelbase.ui.Creators;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.appengers.marvelbase.R;

public class CreatorsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creators_details);
        Intent intent = getIntent();
        int creatorId = intent.getIntExtra("creatorId",0);
        TextView test = (TextView) findViewById(R.id.test_txt);
        test.setText(String.valueOf(creatorId));
        //TODO: check if item is stored on Firestore
    }
}