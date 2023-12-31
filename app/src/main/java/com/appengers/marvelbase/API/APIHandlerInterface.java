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
    @GET("creators")
    Call<APIController.APIResponse<APIController.CreatorsData>> searchCreator(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("nameStartsWith") String query
    );

    @GET("characters")
    Call<APIController.APIResponse<APIController.CharactersData>> getChar(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
    @GET("characters")
    Call<APIController.APIResponse<APIController.CharactersData>> searchCharacter(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("nameStartsWith") String query
    );
    @GET("characters/{id}")
    Call<APIController.APIResponse<APIController.CharactersData>> getCharById(
            @Path("id") int characterId,
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash
    );
    @GET("comics")
    Call<APIController.APIResponse<APIController.ComicsData>> getComics(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("offset") int offset,
            @Query("limit") int limit
    );
    @GET("comics/{id}")
    Call<APIController.APIResponse<APIController.ComicsData>> getComicsById(
            @Path("id") int comicsId,
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash
    );
    @GET("comics")
    Call<APIController.APIResponse<APIController.ComicsData>> searchComic(
            @Query("apikey") String apiKey,
            @Query("ts") long timestamp,
            @Query("hash") String hash,
            @Query("titleStartsWith") String query
    );
}

