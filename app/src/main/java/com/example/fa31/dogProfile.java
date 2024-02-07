package com.example.fa31;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class dogProfile extends AppCompatActivity {

    int fId, fAge, fWeight;
    String fName, fBreed, fVaccine, fBType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dog_profile);

        TextView id = (TextView) findViewById(R.id.idDog);
        TextView name = (TextView) findViewById(R.id.ownerName);
        TextView breed = (TextView) findViewById(R.id.emailOwner);
        TextView age = (TextView) findViewById(R.id.phoneOwner);
        TextView weight = (TextView) findViewById(R.id.phoneOwner);
        TextView vaccine = (TextView) findViewById(R.id.vaccine_list);
        Button btn = (Button) findViewById(R.id.returnButton);
        Button adopt = (Button) findViewById(R.id.confirm);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);


        fId = sharedPref.getInt("key1",0);
        fName = sharedPref.getString("key2","");
        fBreed = sharedPref.getString("key3","");
        fAge = sharedPref.getInt("key4",0);
        fWeight = sharedPref.getInt("key5",0);
        fVaccine = sharedPref.getString("key6","");
        fBType = sharedPref.getString("key7","");

        id.setText("Dog ID: "+fId);
        name.setText("Dog Name: "+fName);
        breed.setText("Dog Breed: "+fBreed);
        age.setText("Dog Age: "+fAge);
        weight.setText("Dog Weight: "+fWeight);
        vaccine.setText("Vaccinated: "+fVaccine);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dogProfile.this, userActivity.class);

                startActivity(i);
                finish();

            }
        });

        adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(dogProfile.this, adoptionForm.class);


                startActivity(intent);
                finish();
            }
        });
    }
}