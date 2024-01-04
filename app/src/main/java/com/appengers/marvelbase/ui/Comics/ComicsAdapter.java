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
    private int selected = -1;
    private Context context;
    private OnItemClickListener onClickListener;
    private int selectedItem;
    Comics selectedComics;

    private ComicsViewHolder lastItem = null;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;
    public void setItems(ArrayList<Comics> comicsList) {
        this.comicsList = comicsList;
    }
    public ComicsAdapter(Context context, ArrayList<Comics> comicsList) {
        this.comicsList = new ArrayList<>(comicsList);
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ComicsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_template_comics, parent, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ComicsViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ComicsViewHolder holder, int position) {
        Comics comic = comicsList.get(position);
        holder.comicsName.setText(comic.getTitle());
        holder.comicsPrice.setText(String.valueOf(comic.getPrices()));

        if (comic.getThumbnail() != null && comic.getThumbnail().path != null) {
            String imageUrl = comic.getThumbnail().path + "." + comic.getThumbnail().extension;
            // Cambia 'http' a 'https'
            imageUrl = imageUrl.replace("http://", "https://");
            Picasso.get().load(imageUrl).fit().into(holder.comicsIMG);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cambiar la posición seleccionada y notificar cambios
                if (onClickListener != null) {
                    onClickListener.onItemClick(position);
                } else {
                    Intent intent = new Intent(context, ComicsDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("comicsId", comicsList.get(position).getId());
                    Log.d("IDIDIDID12", "Comics ID: " + comicsList.get(position).getTitle());
                    context.startActivity(intent);
                    lastItem = holder;
                    //holder.creatorCard.setBackgroundColor(Color.parseColor("#D4EFDF"));
                    setSelectedItem(position);

                }
            }
        });
    }

    private void setSelectedItem(int position) {
        this.selectedItem = position;
        selectedComics = comicsList.get(selectedItem);
        Log.d("ITEM SELECTED: ", String.valueOf(comicsList.get(selectedItem).getTitle()));
    }
    public Comics getSelectedComics(){
        return this.selectedComics;
    }
    public int getItemCount() {
        return comicsList.size();
    }
    static class ComicsViewHolder extends RecyclerView.ViewHolder {
        TextView comicsName;
        ImageView comicsIMG;
        TextView comicsPrice;
        CardView comics;

        public ComicsViewHolder(@NonNull View itemView) {
            super(itemView);
            comicsName = itemView.findViewById(R.id.comicsName);
            comicsIMG = itemView.findViewById(R.id.comicsImg);
            comicsPrice = itemView.findViewById(R.id.comicsPrice);
            comics = itemView.findViewById(R.id.comicsCard);
        }

    }
}
