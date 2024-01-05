package com.appengers.marvelbase.ui.Favorites;

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
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Comics.ComicsAdapter;
import com.appengers.marvelbase.ui.Creators.CreatorsAdapter;

import java.util.ArrayList;

public class ComicsFavFragment extends Fragment {

    RecyclerView comicsRecycler;
    ArrayList<Comics> comicsList;
    ComicsAdapter adapter;
    ArrayList<Long> favoriteIdList;

    public void fetchComicsFavorites(ComicsAdapter adapter, int offset, int limit) {
        DBController db = new DBController(this.getActivity().getApplicationContext());
        db.setUserId();
        db.getFavorites(DBController.Category.COMICS, new  APICallback<ArrayList<Long>>() {

            @Override
            public void onSuccess(ArrayList<Long> fetchedFavorites) {
                favoriteIdList = fetchedFavorites;
                fetchComics(favoriteIdList, adapter, offset, limit);
            }
            @Override
            public void onError(Throwable t) {
                //Handle the error
                Log.e("API Error", "Error fetching creators: " + t.getMessage());
            }
        });
    }
    public void fetchComics(ArrayList<Long> favoriteIdList, ComicsAdapter adapter, int offset, int limit) {
        APIController api = new APIController(getResources());
        comicsList = new ArrayList<>();
        for (Long item : favoriteIdList) {
            api.getComic(item.intValue(), new APICallback<ArrayList<Comics>>() {

                @Override
                public void onSuccess(ArrayList<Comics> fetchedComicsList) {
                    comicsList.add(fetchedComicsList.get(0));
                    adapter.setItems(comicsList);
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onError(Throwable t) {
                    //Handle the error
                    Log.e("API Error", "Error fetching comics: " + t.getMessage());
                }
            });
        }
    }

    public ComicsFavFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comics_favorites, container, false);
        DBController db = new DBController(this.getActivity().getApplicationContext());
        db.setUserId();
        //Retrieve the APIController object
        //Intent intent = getIntent();
        comicsRecycler = (RecyclerView) v.findViewById(R.id.comicsRecycler);
        //mandatory! initialize the ArrayList
        comicsList = new ArrayList<>();
        adapter = new ComicsAdapter(this.getActivity().getApplicationContext(), comicsList);
        RecyclerView.LayoutManager l = new LinearLayoutManager(this.getActivity().getApplicationContext());
        comicsRecycler.setLayoutManager(l);
        comicsRecycler.setItemAnimator(new DefaultItemAnimator());
        comicsRecycler.setAdapter(adapter);
        //mandatory! when the adapter is created, call the fetch
        //fetchComicsFavorites(adapter, 0, 20);
        
        return v;
    }

    public void onResume() {
        super.onResume();
        adapter.setItems(new ArrayList<>());
        adapter.notifyDataSetChanged();
        fetchComicsFavorites(adapter, 0, 20);
    }
}