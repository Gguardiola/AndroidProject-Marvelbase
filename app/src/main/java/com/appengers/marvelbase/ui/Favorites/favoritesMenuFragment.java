package com.appengers.marvelbase.ui.Favorites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appengers.marvelbase.R;

public class favoritesMenuFragment extends Fragment {

    public favoritesMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites_menu, container, false);
        // Inflate the layout for this fragment
        return v;
    }
}