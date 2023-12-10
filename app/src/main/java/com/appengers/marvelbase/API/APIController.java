package com.appengers.marvelbase.API;


import android.content.res.Resources;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.appengers.marvelbase.Models.Characters;
import com.appengers.marvelbase.Models.Comics;
import com.appengers.marvelbase.Models.Creators;
import com.appengers.marvelbase.R;

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
        Call<APIResponse> call = service.getCreators(PUBLICKEY, 1, HASHKEY, offset, limit);
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    APIResponse creatorsResponse = response.body();
                    if (creatorsResponse != null) {
                        CreatorsData data = creatorsResponse.getData();
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
            public void onFailure(Call<APIResponse> call, Throwable t) {
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

    public class APIResponse {
        private int code;
        private String status;
        private CreatorsData data;

        public int getCode() {
            return code;
        }

        public String getStatus() {
            return status;
        }
        public CreatorsData getData() {
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
