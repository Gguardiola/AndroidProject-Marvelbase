package com.appengers.marvelbase.ui.Favorites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appengers.marvelbase.ChangeFragment;
import com.appengers.marvelbase.MainActivity;
import com.appengers.marvelbase.R;
import com.appengers.marvelbase.ui.Creators.CreatorsActivity;
import com.appengers.marvelbase.ui.Creators.CreatorsDetails;

public class FavoritesActivity extends AppCompatActivity implements ChangeFragment {

    private Fragment[] fragments;
    Button goBackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments=new Fragment[3];
        fragments[0]=new CreatorsFavFragment();
        fragments[1]=new ComicsFavFragment();
        fragments[2]=new CharactersFavFragment();

        setContentView(R.layout.activity_favorites);
        goBackBtn = (Button) findViewById(R.id.back_btn);

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

    }
    @Override
    public void changeFragment(int k) {
        FragmentManager fgmng=getSupportFragmentManager();
        FragmentTransaction trans=fgmng.beginTransaction();

        switch(k){
            case 1:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[0]);
                break;
            case 2:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[1]);
                break;
            case 3:
                trans.replace(R.id.fragmentRecyclerFavoritesData, fragments[2]);
                break;
        }
        trans.commit();

    }
}