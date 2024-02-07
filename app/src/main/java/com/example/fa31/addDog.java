package com.example.fa31;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fa31.model.Dog;
import com.example.fa31.retrofit.DogApi;
import com.example.fa31.retrofit.RetrofitService;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addDog extends AppCompatActivity {

    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dog);

        Button dogaddbutton = findViewById(R.id.addDog);
        Button btn = findViewById(R.id.returnButton);

        btn.setOnClickListener(view -> {
            Intent i = new Intent(addDog.this, adminActivity.class);

            startActivity(i);
            finish();

        });

        initializeComponents();
    }

    private void initializeComponents() {

        EditText name = findViewById(R.id.edit_name);
        EditText breed = findViewById(R.id.edit_breed);
        EditText age = findViewById(R.id.edit_age);
        EditText weight = findViewById(R.id.edit_weight);
        EditText vaccine = findViewById(R.id.edit_vaccine);
        Button addInfo = findViewById(R.id.addDog);

        RetrofitService retrofitService=new RetrofitService();
        DogApi dogApi =retrofitService.getRetrofit().create(DogApi.class);

        addInfo.setOnClickListener(view -> {

            String dName, dBreed, dVac, dBt;
            int dAge, dWeight;

            dName = name.getText().toString();
            dBreed = breed.getText().toString();
            dAge = Integer.parseInt(age.getText().toString());
            dWeight = Integer.parseInt(weight.getText().toString());
            dVac = vaccine.getText().toString();

            Dog dog = new Dog();

            dog.setName(dName);
            dog.setBreed(dBreed);
            dog.setAge(dAge);
            dog.setWeight(dWeight);
            dog.setVaccine(dVac);

            dogApi.save(dog)
                    .enqueue(new Callback<Dog>() {
                                 @Override
                                 public void onResponse(Call<Dog> call, Response<Dog> response) {
                                     Toast.makeText(addDog.this,"Save Successful!", Toast.LENGTH_SHORT).show();

                                     Intent i = new Intent(addDog.this, adminActivity.class);

                                     startActivity(i);
                                     finish();
                                 }

                                 @Override
                                 public void onFailure(Call<Dog> call, Throwable t) {
                                     Toast.makeText(addDog.this,"Save Failed!", Toast.LENGTH_SHORT).show();
                                     Logger.getLogger(addDog.class.getName()).log(Level.SEVERE,"Error Occurred",t);
                                 }
                             }
                    );

        });
    }
}