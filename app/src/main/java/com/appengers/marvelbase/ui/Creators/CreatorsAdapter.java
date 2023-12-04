package com.appengers.marvelbase.ui.Creators;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class CreatorsAdapter extends RecyclerView.Adapter<CreatorsAdapter.CreatorsViewHolder>{

    private ArrayList<Creators> creatorsList;
    private Context context = null;
    public CreatorsAdapter(Context context, ArrayList<Creators> creatorsList){
        this.context = context;
        this.creatorsList = creatorsList;
    }
    @NonNull
    @Override
    public CreatorsAdapter.CreatorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO: change R.layout

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_template_creator, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        //TODO: onClickListener of the current item selected
        return new CreatorsAdapter.CreatorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatorsAdapter.CreatorsViewHolder holder, int position) {
        int creatorId = creatorsList.get(position).getId();
        String fullName = creatorsList.get(position).getfirstName();

        holder.creatorId.setText(String.valueOf(creatorId));
        holder.fullName.setText(String.valueOf(fullName));

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class CreatorsViewHolder extends RecyclerView.ViewHolder {
        TextView fullName;
        TextView creatorId;
        //TODO: implement the comics and series

        public CreatorsViewHolder(@NonNull View itemView) {
            super(itemView);
            fullName = itemView.findViewById(R.id.charname_txt);
            creatorId = itemView.findViewById(R.id.charId_txt);
        }

    }

}
