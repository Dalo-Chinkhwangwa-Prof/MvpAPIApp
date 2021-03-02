package com.britishbroadcast.mvpapiapp.model.network;

//[BASE_URL]/api/?results=2

//Path = api/
//Query = results

import com.britishbroadcast.mvpapiapp.model.data.RandomUserResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// GET - PUT - POST - DELETE
public interface PersonAPIService {

    @GET("api/")
    Call<RandomUserResult> getUserResult(@Query("results")int results);
}
