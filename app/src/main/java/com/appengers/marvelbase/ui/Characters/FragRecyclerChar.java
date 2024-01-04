package com.appengers.marvelbase.ui.Characters;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class FragRecyclerChar extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Characters> charactersList;
    private CharacterAdapter characterAdapter;
    public CharacterAdapter firstList;
    private ViewModelChar model;

    public FragRecyclerChar() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_recycler_char, container, false);

        recyclerView = v.findViewById(R.id.recyChar);
        charactersList = new ArrayList<>();
        characterAdapter = new CharacterAdapter(requireContext(), charactersList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(characterAdapter);

        getCharData(characterAdapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ViewModelChar.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getCharactersData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Characters>>() {
            @Override
            public void onChanged(ArrayList<Characters> charactersList) {

                if (charactersList != null) {
                    characterAdapter.setItems(charactersList);
                    characterAdapter.notifyDataSetChanged();

                    Log.d("FragRecyclerChar", "Recycler actualizada. Número de personajes: " + charactersList.size());
                }
            }
        });

    }

    public void getCharData(CharacterAdapter adapter) {
        APIController appengers = new APIController(requireContext().getResources());
        appengers.getChar(0, 20, new APICallback<ArrayList<Characters>>() {
            @Override
            public void onSuccess(ArrayList<Characters> charactersList) {
                adapter.setItems(charactersList);
                adapter.notifyDataSetChanged();
                model.setCharactersData(charactersList);
                Log.d("FragRecyclerChar", "Llamada API exitosa. Número de personajes: " + charactersList.size());
            }

            @Override
            public void onError(Throwable t) {
                Log.e("FragRecyclerChar", "Error al cargar el personaje", t);
            }
        });
    }
}

