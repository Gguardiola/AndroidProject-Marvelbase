package com.appengers.marvelbase.API;


import android.content.res.Resources;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIController extends AppCompatActivity implements Serializable {
    //APIController: Methods to handle the API calls
    Resources res;

    private String PRIVATEKEY, PUBLICKEY, HASHKEY, ENDPOINT;

    public APIController(Resources res) {
        this.res = res;
        this.ENDPOINT = this.res.getString(R.string.ENDPOINT);
        this.PRIVATEKEY = this.res.getString(R.string.PRIVATEKEY);
        this.PUBLICKEY = this.res.getString(R.string.PUBLICKEY);
        this.HASHKEY =this.res.getString(R.string.HASHKEY);
    }

    public void getCreators(int offset, int limit, APICallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIHandlerInterface service = retrofit.create(APIHandlerInterface.class);
        Call<APIResponse<CreatorsData>> call = service.getCreators(PUBLICKEY, 1, HASHKEY, offset, limit);
        call.enqueue(new Callback<APIResponse<CreatorsData>>() {
            @Override
            public void onResponse(Call<APIResponse<CreatorsData>> call, Response<APIResponse<CreatorsData>> response) {
                if (response.isSuccessful()) {
                    APIResponse creatorsResponse = response.body();
                    if (creatorsResponse != null) {
                        CreatorsData data = (CreatorsData)creatorsResponse.getData();
                        if (data != null) {
                            ArrayList<Creators> creatorsList = data.getResults();
                            //mandatory! this callback retrieves to the onSuccess
                            callback.onSuccess(creatorsList);
                        }
                    }
                } else {
                    //TODO: check if this is mandatory
                }
            }
            @Override
            public void onFailure(Call<APIResponse<CreatorsData>> call, Throwable t) {
                //TODO: check if this is mandatory
            }
        });
    }
    public void getChar(int offset, int limit, APICallback callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIHandlerInterface service = retrofit.create(APIHandlerInterface.class);
        Call<APIResponse<CharactersData>> call = service.getChar(PUBLICKEY, 1, HASHKEY, offset, limit);
        call.enqueue(new Callback<APIResponse<CharactersData>>() {
            @Override
            public void onResponse(Call<APIResponse<CharactersData>> call, Response<APIResponse<CharactersData>> response) {
                if (response.isSuccessful()) {
                    APIResponse characterResponse = response.body();
                    if (characterResponse != null) {
                        CharactersData data = (CharactersData) characterResponse.getData();
                        if (data != null) {
                            ArrayList<Characters> charactersList = data.getResults();
                            //mandatory! this callback retrieves to the onSuccess
                            callback.onSuccess(charactersList);
                        }
                    }
                } else {
                    //TODO: check if this is mandatory
                }
            }
            @Override
            public void onFailure(Call<APIResponse<CharactersData>> call, Throwable t) {
                //TODO: check if this is mandatory
            }
        });
    }

//    public ArrayList<Characters> getCharacters(){
//
//    }
//    public ArrayList<Comics> getComics(){
//
//    }

    public class APIResponse<T> {
        private int code;
        private String status;
        @SerializedName("data")
        private T data;

        public int getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }

        public T getData() {
            return data;
        }
    }
    public class CreatorsData extends AbstractData<Creators> {
    }
    public class ComicsData extends AbstractData<Comics> {
    }
    public class CharactersData extends AbstractData<Characters> {
    }

}
