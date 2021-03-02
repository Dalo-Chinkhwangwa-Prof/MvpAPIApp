package com.britishbroadcast.mvpapiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.britishbroadcast.mvpapiapp.model.data.RandomUserResult;
import com.britishbroadcast.mvpapiapp.model.data.Result;
import com.britishbroadcast.mvpapiapp.model.network.PersonAPIRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private PersonAPIRetrofit personAPIRetrofit;
    private RecyclerView personRecyclerView;
    private PersonAdapter personAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personRecyclerView = findViewById(R.id.main_recyclerview);

        personAPIRetrofit = new PersonAPIRetrofit();

        personAdapter = new PersonAdapter(new ArrayList<>());
        personRecyclerView.setAdapter(personAdapter);

        makeAPiCall();
    }

    private void makeAPiCall() {

        personAPIRetrofit.getRandomPeople(10)
                .enqueue(new Callback<RandomUserResult>() {
                    @Override
                    public void onResponse(Call<RandomUserResult> call, Response<RandomUserResult> response) {
                        if(response.body().getResults() != null)
                            displayPersons(response.body().getResults());
                    }

                    @Override
                    public void onFailure(Call<RandomUserResult> call, Throwable t) {
                        Log.d("TAG_X", "Failed: "+t.getLocalizedMessage()+" URL: "+call.request().url());
                    }
                });



    }

    private void displayPersons(List<Result> people) {
        for(int i = 0; i < people.size(); i++){
            Log.d("TAG_X", people.get(i).getName().getFirst() + " " +people.get(i).getName().getLast());
        }

        personAdapter.updatePeople(people);
    }
}







