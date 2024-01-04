package com.appengers.marvelbase.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.appengers.marvelbase.API.APICallback;
import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.API.DBController;
import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Characters.FragCharacterActivity;
import com.appengers.marvelbase.ui.Characters.ViewModelChar;
import com.appengers.marvelbase.ui.Comics.ComicsActivity;
import com.appengers.marvelbase.ui.Creators.CreatorsActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Searchbar extends Fragment {

    private SearchView searchView;
    private ViewModelChar model;
    TextView activityTitle;
    Context hostActivity = getContext();
    String currentActivity;
    DBController.Category currentCategory = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(requireActivity()).get(ViewModelChar.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity hostingActivity = getActivity();

        if (hostingActivity != null) {

            if (isAdded()) {
                String activityClassName = hostingActivity.getClass().getSimpleName();
                Log.d("YourFragment", "Fragment hosted in activity: " + activityClassName);
                if (activityClassName.equals(CreatorsActivity.class.getSimpleName())) {
                    Log.d("SEARCH ON", "Search in CreatorsActivity");
                    currentCategory = DBController.Category.CREATORS;
                    activityTitle.setText(capitalizeFirstLetter(String.valueOf(currentCategory)));
                } else if (activityClassName.equals(FragCharacterActivity.class.getSimpleName())) {
                    Log.d("SEARCH ON", "Search in FragCharacterActivity");
                    currentCategory = DBController.Category.CHARACTERS;
                    activityTitle.setText(capitalizeFirstLetter(String.valueOf(currentCategory)));
                } else if (activityClassName.equals(ComicsActivity.class.getSimpleName())) {
                    Log.d("SEARCH ON", "Search in ComicsActivity");
                    currentCategory = DBController.Category.COMICS;
                    activityTitle.setText(capitalizeFirstLetter(String.valueOf(currentCategory)));
                } else {
                    Log.d("SEARCH ON", "Unknown activity");
                }
            } else {
                Log.e("YourFragment", "Fragment not added or attached to any activity");
            }
        } else {
            Log.e("YourFragment", "Host activity is null");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_char_search, container, false);

        activityTitle = (TextView) v.findViewById(R.id.activityTitle);
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
                if (newText.isEmpty()) {
                    // Si el texto de búsqueda está vacío, restaura la lista original
                    restoreOriginalList();
                }
                return true;
            }
        });

        return v;
    }
    private void restoreOriginalList() {
        model.clearCharactersData();
        APIController appengers = new APIController(requireContext().getResources());
        appengers.getChar(0, 20, new APICallback<ArrayList<Characters>>() {
            @Override
            public void onSuccess(ArrayList<Characters> charactersList) {
                model.setCharactersData(charactersList); // Set the data in the ViewModel
                Log.d("CharSearch", "Original list restored. Number of characters: " + charactersList.size());
            }

            @Override
            public void onError(Throwable t) {
                Log.e("CharSearch", "Error restoring original list", t);
            }
        });
    }

    private void handleSearchQuery(String query) {
        APIController api = new APIController(getResources());

        api.searchCharacter(query, new APICallback<ArrayList<Characters>>() {
            @Override
            public void onSuccess(ArrayList<Characters> characterList) {
                if (characterList != null && !characterList.isEmpty()) {
                    Characters foundCharacter = characterList.get(0);
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

    private static String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        input = input.toLowerCase();
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}