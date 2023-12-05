package com.appengers.marvelbase.ui.Comics;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Comics;

import java.util.ArrayList;

public class comicsAdapter extends RecyclerView.Adapter<comicsAdapter.MyViewHolder>{
    private ArrayList<Comics> lComics;
    private Context context = null;



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private String image;
        private String name;
        private Float price;

        public MyViewHolder (final View view) {
            super(view);
            
        }

    }
    @NonNull
    @Override
    public comicsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull comicsAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
