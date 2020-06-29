package com.example.stackoverflowapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.stackoverflowapp.model.StackApiResponse;
import com.example.stackoverflowapp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Call<StackApiResponse> call =
                RetrofitClient.getInstance().getApiInterface()
                        .getAnswers(1,50,"desc","activity","stackoverflow");

        call.enqueue(new Callback<StackApiResponse>() {
            @Override
            public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<StackApiResponse> call, Throwable t) {
                call.cancel();
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

            }
        });

    }
}