package com.appengers.marvelbase.API;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBController {

    public enum Category {
        CHARACTERS,
        COMICS,
        CREATORS,
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SharedPreferences prefs;
    Context context;
    String userId;
    //Constructor that stores the context
    public DBController(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
    }
    //Method that is called to set the userId from the phone storage (not first run)
    public void setUserId(){
        this.userId = prefs.getString("USER_ID",String.valueOf(0));
        Log.d("USER ID SETTED: ",userId);
    }

    //Initialize the Document inside the collection of the new user (first run)
    public void init(String userId){
        Map<String, Object> bodyContent = new HashMap<>();

        bodyContent.put("characters",  new ArrayList<Integer>());
        bodyContent.put("creators",  new ArrayList<Integer>());
        bodyContent.put("comics",  new ArrayList<Integer>());

        db.collection("UsersFavorites").document(userId)
        .set(bodyContent)
        .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("FIRESTORE - SET TEST", "CUSTOM added with ID: ");
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
        this.userId = userId;
    }
    //Adds a new Item inside the selected Category
    public void addFavorite(Category category, int itemId){
        String currentCategory = category.name().toLowerCase();
        DocumentReference docRef = db.collection("UsersFavorites").document(this.userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ArrayList<Integer> focusItemArray = (ArrayList<Integer>) document.get(currentCategory);
                        if (focusItemArray == null) {
                            focusItemArray = new ArrayList<Integer>();
                        }
                        focusItemArray.add(itemId);
                        docRef.update(currentCategory, focusItemArray);
                        Log.d("DB - ADDED FAVORITE TO "+userId+" on "+currentCategory+":",String.valueOf(itemId));
                    }
                }
            }
        });
    }
    public void getFavorites(Category category, APICallback<ArrayList<Long>> callback){
        String currentCategory = category.name().toLowerCase();
        DocumentReference docRef = db.collection("UsersFavorites").document(this.userId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Boolean isFound = false;
                    ArrayList<Long> creatorsArray = (ArrayList<Long>) document.get(currentCategory);
                    callback.onSuccess(creatorsArray);
                }
                else {
                    //TODO: check if mandatory handle
                }
            } else {
                Exception exception = task.getException();
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
        });
    }
    public void checkFavorite(int itemId, Category category, APICallback<Boolean> callback){
        String currentCategory = category.name().toLowerCase();
        DocumentReference docRef = db.collection("UsersFavorites").document(this.userId);
        docRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Boolean isFound = false;
                    ArrayList<Long> creatorsArray = (ArrayList<Long>) document.get(currentCategory);
                    for (Long item : creatorsArray) {
                        if(String.valueOf(item).equals(String.valueOf(itemId))){
                            Log.d("CREATOR FOUND!", "OLEE");
                            isFound = true;
                        }
                    }
                    callback.onSuccess(isFound);
                }
                else {
                    //TODO: check if mandatory handle
                }
            } else {
                Exception exception = task.getException();
                if (exception != null) {
                    exception.printStackTrace();
                }
            }
        });
    }
    //Deletes an Item from the selected Category
    public void deleteFavorite(Category category, int itemId){
        String currentCategory = category.name().toLowerCase();
        DocumentReference docRef = db.collection("UsersFavorites").document(this.userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        ArrayList<Long> focusItemArray = (ArrayList<Long>) document.get(currentCategory);
                        focusItemArray.remove(Long.valueOf(itemId));
                        docRef.update(currentCategory, focusItemArray);
                        Log.d("DB - DELETED FAVORITE TO "+userId+" on "+currentCategory+":",String.valueOf(itemId));
                    }
                }
            }
        });
    }
}
