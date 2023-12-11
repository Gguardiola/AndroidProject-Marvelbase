package com.appengers.marvelbase.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIHandlerInterface {
    @GET("creators")
    Call<APIController.APIResponse<APIController.CreatorsData>> getCreators(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
    @GET("creators/{id}")
    Call<APIController.APIResponse<APIController.CreatorsData>> getCreatorsById(
            @Path("id") int creatorId,
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash
    );

    @GET("characters")
    Call<APIController.APIResponse<APIController.CharactersData>> getChar(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
}

