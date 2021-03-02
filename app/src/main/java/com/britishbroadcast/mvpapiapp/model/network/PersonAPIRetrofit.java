package com.britishbroadcast.mvpapiapp.model.network;

import com.britishbroadcast.mvpapiapp.model.data.RandomUserResult;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonAPIRetrofit {

    PersonAPIService personAPIService;

    public PersonAPIRetrofit() {
        personAPIService = createAPIService(createRetrofit());
    }

    private Retrofit createRetrofit(){
        return new Retrofit
                .Builder()
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create())//Since we are getting a json object - we need to convert it to our pojo classes
                .build();
    }

    private PersonAPIService createAPIService(Retrofit retrofit){
        return retrofit.create(PersonAPIService.class);
    }

    public Call<RandomUserResult> getRandomPeople(int results){
        return personAPIService.getUserResult(results);
    }

}
