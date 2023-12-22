package com.appengers.marvelbase.ui.Characters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Creators.CreatorsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>{

    private ArrayList<Characters> charactersList;
    private int selected = -1;
    private Context context;
    private OnItemClickListener onClickListener;
    private int selectedItem;
    Characters selectedCharacter;

    private CharactersViewHolder lastItem = null;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private OnItemClickListener listener;
    public void setItems(ArrayList<Characters> charactersList) {
        this.charactersList = charactersList;
    }
    public CharacterAdapter(Context context, ArrayList<Characters> charactersList) {
        this.charactersList = new ArrayList<>(charactersList);
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_template_character, parent, false);
        return new CharactersViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        Characters character = charactersList.get(position);
        holder.charName.setText(character.getName());
        holder.charID.setText(String.valueOf(character.getId()));

        if (character.getThumbnail() != null && character.getThumbnail().path != null) {
            String imageUrl = character.getThumbnail().path + "." + character.getThumbnail().extension;
            // Cambia 'http' a 'https'
            imageUrl = imageUrl.replace("http://", "https://");
            Picasso.get().load(imageUrl).fit().into(holder.charIMG);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Cambiar la posición seleccionada y notificar cambios
                if (onClickListener != null) {
                    onClickListener.onItemClick(position);
                } else {
                    Intent intent = new Intent(context, CharacterDetails.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("characterId", charactersList.get(position).getId());
                    Log.d("IDIDIDID12", "Character ID: " + charactersList.get(position).getName());
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
        selectedCharacter = charactersList.get(selectedItem);
        Log.d("ITEM SELECTED: ", String.valueOf(charactersList.get(selectedItem).getName()));
    }
    public Characters getSelectedCreator(){
        return this.selectedCharacter;
    }
    public int getItemCount() {
        return charactersList.size();
    }
    static class CharactersViewHolder extends RecyclerView.ViewHolder {
        TextView charName;
        TextView charID;
        ImageView charIMG;
        CardView chars;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            charName = itemView.findViewById(R.id.charname_txt);
            charID = itemView.findViewById(R.id.charId_txt);
            charIMG = itemView.findViewById(R.id.char_IMG);
            chars = itemView.findViewById(R.id.charCard);
        }

    }
}
