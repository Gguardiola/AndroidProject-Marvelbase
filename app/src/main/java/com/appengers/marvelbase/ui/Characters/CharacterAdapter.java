package com.appengers.marvelbase.ui.Characters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharactersViewHolder>{

    private List<Characters> charactersList = new ArrayList<>();
    @NonNull
    @Override
    public CharactersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_template_character, parent, false);
        return new CharactersViewHolder(view);
    }

    public void addCharacter(Characters character) {
        charactersList.add(character);
        notifyItemInserted(charactersList.size() - 1);
    }
    public void onBindViewHolder(@NonNull CharactersViewHolder holder, int position) {
        Characters character = charactersList.get(position);
        holder.charName.setText(character.getName());
        holder.charID.setText("ID: " + character.getId());
        /*holder.pokemonName.setText(pokemon.getName());
        holder.charID.setText("ID: " + pokemon.getId());
        holder.pokemonWeight.setText("Weight: " + pokemon.getWeight());
        boolean isSelected = (position == selected);

        if (pokemon.getSprites() != null && pokemon.getSprites().front_default != null) {
            Glide.with(holder.itemView.getContext()).load(pokemon.getSprites().front_default).into(holder.pokemonImage);
        }*/
    }
    public int getItemCount() {
        return charactersList.size();
    }
    static class CharactersViewHolder extends RecyclerView.ViewHolder {
        TextView charName;
        TextView charID;
        TextView pokemonWeight;
        ImageView pokemonImage;
        CardView chars;

        public CharactersViewHolder(@NonNull View itemView) {
            super(itemView);
            charName = itemView.findViewById(R.id.charname_txt);
            charID = itemView.findViewById(R.id.charId_txt);
            pokemonWeight = itemView.findViewById(R.id.charweight_txt);
            pokemonImage = itemView.findViewById(R.id.char_IMG);
            chars = itemView.findViewById(R.id.charCard);
        }

    }
}
