package com.appengers.marvelbase;

import static android.content.ContentValues.TAG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.API.APIController;
import com.appengers.marvelbase.ui.Characters.CharacterActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    APIController apiController = new APIController();


    //test
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ImageButton btnCharacters, btnComics, btnFavorites, btnCreators;
    private ActivityResultLauncher<Intent> startCharacterAct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page_activity);

        btnCreators = (ImageButton) findViewById(R.id.creators_btn);
        btnCharacters = (ImageButton) findViewById(R.id.characters_btn);
        btnComics = (ImageButton) findViewById(R.id.comics_btn);
        btnFavorites = (ImageButton) findViewById(R.id.favorites_btn);

        btnCreators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Creator test", Toast.LENGTH_SHORT).show();
            }
        });
        btnCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CharacterActivity.class);
                startCharacterAct.launch(intent);
            }
        });
        startCharacterAct = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {

                    }
                }
        );

        //test read firestore
        db.collection("UsersFavorites")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FIRESTORE - READ TEST", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        //test post firestore
        Map<String, Object> bodyContent = new HashMap<>();
        List<Integer> characters = new ArrayList<Integer>();
        characters.add(234234);
        characters.add(345345435);

        List<Integer> creators = new ArrayList<Integer>();
        creators.add(34534543);
        creators.add(345435);

        List<Integer> comics = new ArrayList<Integer>();
        comics.add(345435345);
        comics.add(345435);

        // Add arrays to the document content
        bodyContent.put("characters", characters);
        bodyContent.put("creators", creators);
        bodyContent.put("comics", comics);


// Add a new document with a generated ID
        db.collection("UsersFavorites")
                .add(bodyContent)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FIRESTORE - ADD TEST", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }


}