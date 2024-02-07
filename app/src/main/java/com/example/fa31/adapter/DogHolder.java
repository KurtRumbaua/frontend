package com.example.fa31.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fa31.dogProfile;
import com.example.fa31.R;


public class DogHolder extends RecyclerView.ViewHolder{
    TextView id,name,breed,age,weight,vaccine,blood_type;
    Button btn;

    String dName,dBreed,dVaccine,dBType;
    int dId,dAge,dWeight;

    public DogHolder(@NonNull View itemView){
        super(itemView);
        id = itemView.findViewById(R.id.list_id);
        name = itemView.findViewById(R.id.list_name);
        breed = itemView.findViewById(R.id.list_breed);
        age = itemView.findViewById(R.id.list_age);
        weight = itemView.findViewById(R.id.list_weight);
        vaccine = itemView.findViewById(R.id.list_vaccine);


        btn = itemView.findViewById(R.id.adoptBtn);
        final SharedPreferences sharedPref= PreferenceManager.getDefaultSharedPreferences(itemView.getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(itemView.getContext(), dogProfile.class);

                dId = Integer.parseInt(id.getText().toString());
                dName = String.valueOf(name.getText());
                dBreed = String.valueOf(breed.getText());
                dAge = Integer.parseInt(age.getText().toString());
                dWeight = Integer.parseInt(weight.getText().toString());
                dVaccine = String.valueOf(vaccine.getText());


                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putInt("key1", dId);
                editor.putString("key2", dName);
                editor.putString("key3", dBreed);
                editor.putInt("key4", dAge);
                editor.putInt("key5",dWeight);
                editor.putString("key6", dVaccine);


                editor.apply();

                itemView.getContext().startActivity(i);
            }
        });

    }
}


