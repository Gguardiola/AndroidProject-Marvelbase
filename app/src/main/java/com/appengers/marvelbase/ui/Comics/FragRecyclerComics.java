package com.appengers.marvelbase.ui.Comics;

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
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.R;

import java.util.ArrayList;

public class FragRecyclerComics extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Comics> comicsList;
    private ComicsAdapter comicsAdapter;
    public ComicsAdapter firstList;
    private ViewModelComics model;

    public FragRecyclerComics() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_recycler_comics, container, false);

        recyclerView = v.findViewById(R.id.recycComics);
        comicsList = new ArrayList<>();
        comicsAdapter = new ComicsAdapter(requireContext(), comicsList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(comicsAdapter);

        getComicsData(comicsAdapter);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ViewModelComics.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getComicsData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Comics>>() {
            @Override
            public void onChanged(ArrayList<Comics> comicsList) {

                if (comicsList != null) {
                    comicsAdapter.setItems(comicsList);
                    comicsAdapter.notifyDataSetChanged();

                    Log.d("FragRecyclerComics", "Recycler actualizada. Número de personajes: " + comicsList.size());
                }
            }
        });

    }

    public void getComicsData(ComicsAdapter adapter) {
        APIController appengers = new APIController(requireContext().getResources());
        appengers.getComics(0, 20, new APICallback<ArrayList<Comics>>() {
            @Override
            public void onSuccess(ArrayList<Comics> comicsList) {
                adapter.setItems(comicsList);
                adapter.notifyDataSetChanged();
                model.setComicsData(comicsList);
                Log.d("FragRecyclerComics", "Llamada API exitosa. Número de personajes: " + comicsList.size());
            }

            @Override
            public void onError(Throwable t) {
                Log.e("FragRecyclerComics", "Error al cargar el personaje", t);
            }
        });
    }
}

