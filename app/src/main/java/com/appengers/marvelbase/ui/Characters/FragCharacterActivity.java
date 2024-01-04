package com.appengers.marvelbase.ui.Characters;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Favorites.CharactersFavFragment;
import com.appengers.marvelbase.ui.Favorites.ComicsFavFragment;
import com.appengers.marvelbase.ui.Favorites.CreatorsFavFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragCharacterActivity#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragCharacterActivity extends AppCompatActivity implements ChangeFragment {

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new Fragment[3];
        fragments[0]=new CharSearch();
        fragments[1]=new FragRecyclerChar();
        setContentView(R.layout.fragment_frag_character_activity);
    }

    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng = getSupportFragmentManager();
        FragmentTransaction trans = fgmng.beginTransaction();

        switch (k) {
            case 1:
                trans.replace(R.id.fragmentContainerMenu, fragments[0]);
                Toast.makeText(this, "FRAGMENT 1!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                trans.replace(R.id.fragmentContainerRecycler, fragments[1]);
                Toast.makeText(this, "FRAGMENT 2!", Toast.LENGTH_SHORT).show();
                break;
        }
        trans.commit();
    }


}