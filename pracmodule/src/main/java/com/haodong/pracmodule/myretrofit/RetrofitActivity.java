package com.haodong.pracmodule.myretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haodong.pracmodule.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        GankCreator.getGank().getGanhuo().enqueue(new Callback<Ganhuo>() {
            @Override
            public void onResponse(Call<Ganhuo> call, Response<Ganhuo> response) {
                Ganhuo ganhuo = response.body();

            }

            @Override
            public void onFailure(Call<Ganhuo> call, Throwable t) {

            }
        });
    }
}
