package com.appengers.marvelbase.ui.Comics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Toast;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Searchbar;


public class FragComicsActivity extends AppCompatActivity implements ChangeFragment {

    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new Fragment[3];
        fragments[0]=new Searchbar();
        fragments[1]=new FragRecyclerComics();
        setContentView(R.layout.fragment_frag_comics_activity);
    }

    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng = getSupportFragmentManager();
        FragmentTransaction trans = fgmng.beginTransaction();

        switch (k) {
            case 1:
                trans.replace(R.id.fragmentContainerView4, fragments[0]);
                Toast.makeText(this, "FRAGMENT 1!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                trans.replace(R.id.fragmentContainerView5, fragments[1]);
                Toast.makeText(this, "FRAGMENT 2!", Toast.LENGTH_SHORT).show();
                break;
        }
        trans.commit();
    }


}