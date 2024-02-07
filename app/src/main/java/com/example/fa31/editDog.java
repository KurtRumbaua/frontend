package com.example.fa31;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fa31.model.Dog;
import com.example.fa31.retrofit.DogApi;
import com.example.fa31.retrofit.RetrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editDog extends AppCompatActivity {


    int fId, fAge, fWeight;
    String fName, fBreed, fVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_dog);

        EditText eId = (EditText) findViewById(R.id.edit_id);
        EditText eName = (EditText) findViewById(R.id.edit_name);
        EditText eBreed = (EditText) findViewById(R.id.edit_breed);
        EditText eAge = (EditText) findViewById(R.id.edit_age);
        EditText eWeight = (EditText) findViewById(R.id.edit_weight);
        EditText eVaccine = (EditText) findViewById(R.id.edit_vaccine);
        Button save = (Button) findViewById(R.id.saveBtn);
        Button del = (Button) findViewById(R.id.deleteBtn);
        Button btn = (Button) findViewById(R.id.returnButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(editDog.this, adminActivity.class);

                startActivity(i);
                finish();

            }
        });


        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        fId = sharedPref.getInt("key1",0);
        fName = sharedPref.getString("key2","");
        fBreed = sharedPref.getString("key3","");
        fAge = sharedPref.getInt("key4",0);
        fWeight = sharedPref.getInt("key5",0);
        fVaccine = sharedPref.getString("key6","");

        eId.setText(String.valueOf(fId));
        eName.setText(fName);
        eBreed.setText(fBreed);
        eAge.setText(String.valueOf(fWeight));
        eWeight.setText(String.valueOf(fAge));
        eVaccine.setText(fVaccine);

        RetrofitService retrofitService=new RetrofitService();
        DogApi dogApi =retrofitService.getRetrofit().create(DogApi.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                long id = Integer.parseInt(eId.getText().toString());
                String name = eName.getText().toString();
                String breed = eBreed.getText().toString();
                int age = Integer.parseInt(eWeight.getText().toString());
                int weight = Integer.parseInt(eAge.getText().toString());
                String vaccine = eVaccine.getText().toString();

                Dog dog = new Dog(id, name, breed, age, weight, vaccine);



                Call<Dog> call = dogApi.updateDog(id, dog);
                call.enqueue(new Callback<Dog>() {
                    @Override
                    public void onResponse(Call<Dog> call, Response<Dog> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(editDog.this, "Edited data successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(editDog.this, adminActivity.class);

                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(editDog.this, "Error: Failed to edit dog data", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<Dog> call, Throwable t) {
                        Toast.makeText(editDog.this, "Error: Failed to save dog data", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<Void> call = dogApi.deleteDog(fId);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(editDog.this, "Deleted successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(editDog.this, adminActivity.class);

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(editDog.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
