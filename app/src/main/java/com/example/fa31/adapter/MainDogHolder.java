package com.example.fa31.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fa31.R;

public class MainDogHolder extends RecyclerView.ViewHolder {

    TextView id,name,breed,age,weight,vaccine,blood_type;

    public MainDogHolder(@NonNull View itemView){
        super(itemView);
        id = itemView.findViewById(R.id.list_id);
        name = itemView.findViewById(R.id.list_name);
        breed = itemView.findViewById(R.id.list_breed);
        age = itemView.findViewById(R.id.list_age);
        weight = itemView.findViewById(R.id.list_weight);
        vaccine = itemView.findViewById(R.id.list_vaccine);



    }
}
