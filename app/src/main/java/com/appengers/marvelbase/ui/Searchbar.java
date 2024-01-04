package com.appengers.marvelbase.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Characters.ViewModelChar;

import java.util.ArrayList;

public class Searchbar extends Fragment {

    private SearchView searchView;
    private ViewModelChar model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ViewModelChar.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_char_search, container, false);

        searchView = v.findViewById(R.id.busca);
        Button back = v.findViewById(R.id.button);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                } else {

                    requireActivity().finish();
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               handleSearchQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return true;
            }
        });

        return v;
    }

    private void handleSearchQuery(String query) {
        APIController api = new APIController(getResources());

        api.searchCharacter(query, new APICallback<ArrayList<Characters>>() {
            @Override
            public void onSuccess(ArrayList<Characters> characterList) {
                if (characterList != null && !characterList.isEmpty()) {
                    Characters foundCharacter = characterList.get(0);
                    //showCharacterDetails(foundCharacter);
                    Toast.makeText(requireContext(), "El personaje existe: " + foundCharacter.getName(), Toast.LENGTH_SHORT).show();
                    model.setCharactersData(characterList);

                } else {
                    Toast.makeText(requireContext(), "No existe el personaje con el término de búsqueda: " + query, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onError(Throwable t) {
                Log.e("API Error", "Error searching for character: " + t.getMessage());
            }
        });
    }
}