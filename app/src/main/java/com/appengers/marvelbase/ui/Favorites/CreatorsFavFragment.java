package com.appengers.marvelbase.ui.Favorites;

import static android.content.Intent.getIntent;

import android.content.Intent;
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
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Creators.CreatorsAdapter;

import java.util.ArrayList;

public class CreatorsFavFragment extends Fragment {

    RecyclerView creators_recycler;
    CreatorsAdapter adapter;
    ArrayList<Creators> creatorsList;
    ArrayList<Long> favoriteIdList;

    //Method that gets the creators (mandatory!)
    public void fetchCreatorsFavorites(CreatorsAdapter adapter, int offset, int limit) {
        DBController db = new DBController(this.getActivity().getApplicationContext());
        db.setUserId();
        db.getFavorites(DBController.Category.CREATORS, new  APICallback<ArrayList<Long>>() {

            @Override
            public void onSuccess(ArrayList<Long> fetchedFavorites) {
                favoriteIdList = fetchedFavorites;
                fetchCreators(favoriteIdList, adapter, offset, limit);
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }

    public void fetchCreators(ArrayList<Long> favoriteIdList, CreatorsAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        creatorsList = new ArrayList<>();
        for (Long item: favoriteIdList) {
            api.getCreator(item.intValue(), new APICallback<ArrayList<Creators>>() {
                @Override
                public void onSuccess(ArrayList<Creators> creatorsListItem) {
                    creatorsList.add(creatorsListItem.get(0));
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
    }
    public CreatorsFavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_creators_favorites, container, false);

        //Intent intent = getIntent();
        creators_recycler = (RecyclerView)v.findViewById(R.id.creator_recyclerView);
        //mandatory! initialize the ArrayList
        creatorsList = new ArrayList<>();
        adapter = new CreatorsAdapter(this.getActivity().getApplicationContext(), creatorsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(this.getActivity().getApplicationContext());
        creators_recycler.setLayoutManager(l);
        creators_recycler.setItemAnimator(new DefaultItemAnimator());
        creators_recycler.setAdapter(adapter);
        //mandatory! when the adapter is created, call the fetch
        //fetchCreatorsFavorites(adapter, 0, 20);

        return v;
    }

    public void onResume() {
        super.onResume();
        adapter.setItems(new ArrayList<>());
        adapter.notifyDataSetChanged();
        fetchCreatorsFavorites(adapter, 0, 20);
    }
}