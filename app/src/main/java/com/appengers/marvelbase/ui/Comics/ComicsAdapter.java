package com.appengers.marvelbase.ui.Comics;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>{
    private ArrayList<Comics> comicsList;
    private Context context = null;
    private OnClickListener onClickListener;
    private int selectedItem;
    Comics selectedComic;
    private ComicsViewHolder lastItem = null;

    public ComicsAdapter(Context context, ArrayList<Comics> comicsList) {
        this.comicsList = new ArrayList<>(comicsList);
        this.context = context;
    }
    static class ComicsViewHolder extends RecyclerView.ViewHolder {
        private CardView comicsItem;
        private TextView comicsName;
        private ImageView comicsImg;
        private CardView comicsCard;
        private TextView comicsPrice;
        public ComicsViewHolder (@NonNull View itemView) {
            super(itemView);
            comicsImg = itemView.findViewById(R.id.comicsImg);
            comicsName = itemView.findViewById(R.id.comicsName);
            comicsCard = itemView.findViewById(R.id.comicsCard);
            comicsPrice = itemView.findViewById(R.id.comicsPrice);
        }

    }
    @NonNull
    @Override
    public ComicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_template_comics, parent, false);
        viewHolder.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return new ComicsViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicsViewHolder holder, int position) {
        Comics comics = comicsList.get(position);
        //Glide.with(holder.itemView.getContext()).load(comicsList.get(position).getThumbnail()).fitCenter().into(holder.comicsImg);
        holder.comicsName.setText(String.valueOf(comics.getTitle()));
        holder.comicsPrice.setText(comics.getPrices());
        if (comics.getThumbnail() != null && comics.getThumbnail().path != null) {
            String imageUrl = comics.getThumbnail().path + "." + comics.getThumbnail().extension;
            // Cambia 'http' a 'https'
            imageUrl = imageUrl.replace("http://", "https://");
            Picasso.get().load(imageUrl).fit().into(holder.comicsImg);
        }
        //current clicked item handler
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position);
                }
                else{
                    if(lastItem != null) {
                        //lastItem.creatorCard.setBackgroundColor(Color.parseColor("#EEEEEE"));
                    }
                    Intent intent = new Intent(context, ComicsDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("comicsId", comicsList.get(position).getId());
                    context.startActivity(intent);
                    lastItem = holder;
                    //holder.creatorCard.setBackgroundColor(Color.parseColor("#D4EFDF"));
                    setSelectedItem(position);
                }
            }
        });
    }

    private void setSelectedItem(int position){
        this.selectedItem = position;
        selectedComic = comicsList.get(selectedItem);
        Log.d("ITEM SELECTED: ", String.valueOf(comicsList.get(selectedItem).getId()));
    }

     public void setItems(ArrayList<Comics> comicsList) {
        this.comicsList = comicsList;
    }
    public interface OnClickListener {
        void onClick(int position);
    }
    @Override
    public int getItemCount() {
        return comicsList.size();
    }

}
