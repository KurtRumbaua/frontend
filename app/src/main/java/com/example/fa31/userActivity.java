package com.example.fa31;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fa31.adapter.DogAdapter;
import com.example.fa31.model.Dog;
import com.example.fa31.retrofit.DogApi;
import com.example.fa31.retrofit.RetrofitService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class userActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_page);

        recyclerView = findViewById(R.id.test);
        recyclerView.setLayoutManager(new LinearLayoutManager(userActivity.this));

        Button logOutBtn = (Button) findViewById(R.id.returnButton);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(userActivity.this, loginActivity.class);

                startActivity(i);
                finish();
            }

        });

        loadUsers();

    }


    private void loadUsers () {
        RetrofitService retrofitService = new RetrofitService();
        DogApi dogApi = retrofitService.getRetrofit().create(DogApi.class);
        dogApi.getAllDogs()
                .enqueue(new Callback<List<Dog>>() {

                    @Override
                    public void onResponse(Call<List<Dog>> call, Response<List<Dog>> response) {
                        populateListView(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Dog>> call, Throwable t) {
                        Toast.makeText(userActivity.this, "Failed to load users", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(userActivity.class.getName()).log(Level.SEVERE, "Error occured", t);

                    }
                });
    }

    private void populateListView (List < Dog > dogList) {
        DogAdapter dogAdapter = new DogAdapter(dogList);
        recyclerView.setAdapter(dogAdapter);
    }

}
