package com.appengers.marvelbase.ui.Creators;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreatorsAdapter extends RecyclerView.Adapter<CreatorsAdapter.MyViewHolder> {

    private ArrayList<Creators> creatorsList;
    private Context context;
    private OnClickListener onClickListener;
    private int selectedItem;
    Creators selectedCreator;
    private MyViewHolder lastItem = null;


    public CreatorsAdapter(Context context, ArrayList<Creators> creatorsList) {
        this.creatorsList = new ArrayList<>(creatorsList);
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView creatorItem;
        private TextView fullName;
        private ImageView creatorImg;
        private CardView creatorCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            creatorImg = itemView.findViewById(R.id.creator_img);
            fullName = itemView.findViewById(R.id.creator_name);
            creatorCard = itemView.findViewById(R.id.creator_card);
            creatorCard.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }
    }
    @NonNull
    @Override
    public CreatorsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(context).inflate(R.layout.recycler_view_template_creator, parent, false);
        viewHolder.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new MyViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatorsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Creators creators = creatorsList.get(position);
        //Glide.with(holder.itemView.getContext()).load(creatorsList.get(position).getThumbnail()).into(holder.creatorImg);
        Picasso.get().load(creatorsList.get(position).getThumbnail()).fit().into(holder.creatorImg);
        holder.fullName.setText(String.valueOf(creators.getFirstName()));

        //current clicked item handler
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
                else{
                    Intent intent = new Intent(context, CreatorsDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("creatorId", creatorsList.get(position).getId());
                    context.startActivity(intent);
                    lastItem = holder;
                    setSelectedItem(position);
                }
            }
        });
    }
    private void setSelectedItem(int position){
        this.selectedItem = position;
        selectedCreator = creatorsList.get(selectedItem);
        Log.d("ITEM SELECTED: ", String.valueOf(creatorsList.get(selectedItem).getId()));
    }
    public Creators getSelectedCreator(){
        return this.selectedCreator;
    }
    public int getSelectedItem(){
        return this.selectedItem;
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    public void setItems(ArrayList<Creators> creatorsList) {
        this.creatorsList = creatorsList;
    }
    public interface OnClickListener {
        void onClick(int position);
    }
    @Override
    public int getItemCount() {
        return creatorsList.size();
    }
}

