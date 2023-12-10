package com.appengers.marvelbase.API;

import android.content.res.Resources;

import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIHandlerInterface {
    //APIHandlerInterface: definition of all the API calls
    @GET("creators")
    Call<APIController.APIResponse> getCreators(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );

}
