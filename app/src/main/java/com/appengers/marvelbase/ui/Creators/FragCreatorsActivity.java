package com.appengers.marvelbase.ui.Creators;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Searchbar;

public class FragCreatorsActivity extends AppCompatActivity implements ChangeFragment {

    private Fragment[] fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new Fragment[2];
        fragments[0]=new Searchbar();
        fragments[1]=new CreatorsRecyclerFragment();
        setContentView(R.layout.activity_creator);
    }
    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng = getSupportFragmentManager();
        FragmentTransaction trans = fgmng.beginTransaction();

        switch (k) {
            case 1:
                trans.replace(R.id.fragmentContainerMenuCreator, fragments[0]);
                break;
            case 2:
                trans.replace(R.id.fragmentContainerRecyclerCreator, fragments[1]);
                break;
        }
        trans.commit();
    }

}