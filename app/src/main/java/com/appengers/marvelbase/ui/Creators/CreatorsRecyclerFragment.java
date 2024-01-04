package com.appengers.marvelbase.ui.Creators;

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
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Characters.CharacterAdapter;

import java.util.ArrayList;

public class CreatorsRecyclerFragment extends Fragment {

    RecyclerView creators_recycler;
    ArrayList<Creators> creatorsList;
    ArrayList<Long> favoriteIdList;
    private CreatorsAdapter creatorsAdapter;
    private ViewModelCreators model;

    public void fetchCreators(CreatorsAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        api.getCreators(offset, limit, new APICallback<ArrayList<Creators>>() {

            @Override
            public void onSuccess(ArrayList<Creators> creatorsList) {
                adapter.setItems(creatorsList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }

    public CreatorsRecyclerFragment() {
        // Constructor público requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_creators_favorites, container, false);

        //Intent intent = getIntent();
        creators_recycler = (RecyclerView)v.findViewById(R.id.creator_recyclerView);
        //mandatory! initialize the ArrayList
        creatorsList = new ArrayList<>();
        CreatorsAdapter adapter;
        adapter = new CreatorsAdapter(this.getActivity().getApplicationContext(), creatorsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(this.getActivity().getApplicationContext());
        creators_recycler.setLayoutManager(l);
        creators_recycler.setItemAnimator(new DefaultItemAnimator());
        creators_recycler.setAdapter(adapter);
        //mandatory! when the adapter is created, call the fetch
        fetchCreators(adapter, 0, 20);
        //mandatory! when the adapter is created, call the fetch

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ViewModelCreators.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model.getCreatorsData().observe(getViewLifecycleOwner(), new Observer<ArrayList<Creators>>() {
            @Override
            public void onChanged(ArrayList<Creators> creatorsList) {

                if (creatorsList != null) {
                    creatorsAdapter.setItems(creatorsList);
                    creatorsAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}

