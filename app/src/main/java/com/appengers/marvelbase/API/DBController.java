package com.appengers.marvelbase.API;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBController {

    enum Category {
        CHARACTER,
        COMIC,
        CREATOR,
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void addFavorite(int userId, Category category, int itemId){


    }

    //    db.collection("UsersFavorites")
    //            .get()
    //            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
    //    @Override
    //    public void onComplete(@NonNull Task<QuerySnapshot> task) {
    //        if (task.isSuccessful()) {
    //            for (QueryDocumentSnapshot document : task.getResult()) {
    //                Log.d("FIRESTORE - READ TEST", document.getId() + " => " + document.getData());
    //            }
    //        } else {
    //            Log.w(TAG, "Error getting documents.", task.getException());
    //        }
    //    }
    //});
//
    ////test post firestore
    //Map<String, Object> bodyContent = new HashMap<>();
    //List<Integer> characters = new ArrayList<Integer>();
    //    characters.add(234234);
    //    characters.add(345345435);
//
    //List<Integer> creators = new ArrayList<Integer>();
    //    creators.add(34534543);
    //    creators.add(345435);
//
    //List<Integer> comics = new ArrayList<Integer>();
    //    comics.add(345435345);
    //    comics.add(345435);
//
    //// Add arrays to the document content
    //    bodyContent.put("characters", characters);
    //    bodyContent.put("creators", creators);
    //    bodyContent.put("comics", comics);
//
//

    //    db.collection("UsersFavorites")
    //            .add(bodyContent)
    //            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
    //    @Override
    //    public void onSuccess(DocumentReference documentReference) {
    //        Log.d("FIRESTORE - ADD TEST", "DocumentSnapshot added with ID: " + documentReference.getId());
    //    }
    //})
    //        .addOnFailureListener(new OnFailureListener() {
    //    @Override
    //    public void onFailure(@NonNull Exception e) {
    //        Log.w(TAG, "Error adding document", e);
    //    }
    //});
//
}
