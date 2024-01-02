package com.appengers.marvelbase.ui.Favorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.R;

public class FavoritesActivity extends AppCompatActivity implements ChangeFragment {

    private Fragment[] fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new Fragment[3];
        fragments[0]=new CreatorsFavFragment();
        fragments[1]=new ComicsFavFragment();
        fragments[2]=new CharactersFavFragment();

        setContentView(R.layout.activity_favorites);
    }
    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng=getSupportFragmentManager();
        FragmentTransaction trans=fgmng.beginTransaction();

        switch(k){
            case 1:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[0]);
                Toast.makeText(this, "FRAGMENT 1!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[1]);
                Toast.makeText(this, "FRAGMENT 2!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[2]);
                Toast.makeText(this, "FRAGMENT 3!", Toast.LENGTH_SHORT).show();
                break;
        }
        trans.commit();

    }


}