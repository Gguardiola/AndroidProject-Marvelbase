package com.appengers.marvelbase.ui.Favorites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Characters.CharacterAdapter;
import com.appengers.marvelbase.ui.Comics.ComicsAdapter;

import org.checkerframework.checker.units.qual.A;

import java.sql.Array;
import java.util.ArrayList;

public class CharactersFavFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Characters> charactersList;
    CharacterAdapter characterAdapter;
    ArrayList<Long> favoriteIdList;

    public void fetchCharactersFavorites(CharacterAdapter adapter, int offset, int limit) {
        DBController db = new DBController(this.getActivity().getApplicationContext());
        db.setUserId();
        db.getFavorites(DBController.Category.CHARACTERS, new  APICallback<ArrayList<Long>>() {

            @Override
            public void onSuccess(ArrayList<Long> fetchedFavorites) {
                favoriteIdList = fetchedFavorites;
                fetchCharacters(favoriteIdList, adapter, offset, limit);
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
    private void fetchCharacters(ArrayList<Long> favoriteIdList, CharacterAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        charactersList = new ArrayList<>();
        for (Long item : favoriteIdList) {
            api.getCharacter(item.intValue(), new APICallback<ArrayList<Characters>>() {
                @Override
                public void onSuccess(ArrayList<Characters> fetchedCharactersList) {
                    charactersList.add(fetchedCharactersList.get(0));
                    adapter.setItems(charactersList);
                    adapter.notifyDataSetChanged();
                    Log.d("CharacterActivity", "API Call successful. Number of characters: " + charactersList.size());
                }

                @Override
                public void onError(Throwable t) {
                    Log.e("CharacterActivity", "Error al cargar el personaje", t);
                }
            });

        }
    }

    public CharactersFavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characters_favorites, container, false);

        //Intent intent = getIntent();
        recyclerView = v.findViewById(R.id.recyChar);
        charactersList = new ArrayList<>();
        characterAdapter = new CharacterAdapter(this.getActivity().getApplicationContext(), charactersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterAdapter);

        //fetchCharactersFavorites(characterAdapter, 0, 20);

        return v;
    }

    public void onResume() {
        super.onResume();
        characterAdapter.setItems(new ArrayList<>());
        characterAdapter.notifyDataSetChanged();
        fetchCharactersFavorites(characterAdapter, 0, 20);
    }
}