package com.appengers.marvelbase.ui.Favorites;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.R;

public class FavoritesMenuFragment extends Fragment {

    Button creatorsFragmentBtn, charactersFragmentBtn, comicsFragmentBtn;
    public FavoritesMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites_menu, container, false);

        creatorsFragmentBtn = (Button) v.findViewById(R.id.creators_frgmt_btn);
        charactersFragmentBtn = (Button) v.findViewById(R.id.char_frgmt_btn);
        comicsFragmentBtn = (Button) v.findViewById(R.id.comics_frgmt_btn);

        creatorsFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity favact = getActivity();
                ((ChangeFragment)favact).changeFragment(1);
            }
        });
        charactersFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity favact = getActivity();
                ((ChangeFragment)favact).changeFragment(3);
            }
        });
        comicsFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity favact = getActivity();
                ((ChangeFragment)favact).changeFragment(2);
            }
        });
        return v;
    }
}