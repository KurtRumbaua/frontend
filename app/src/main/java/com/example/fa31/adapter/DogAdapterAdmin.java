package com.example.fa31.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fa31.R;
import com.example.fa31.model.Dog;

import java.util.List;

public class DogAdapterAdmin extends RecyclerView.Adapter<DogHolderAdmin>{
    private List<Dog> dogList;

    public DogAdapterAdmin(List<Dog> dogList){ this.dogList = dogList;}



    @NonNull
    @Override
    public DogHolderAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_admin_dogs, parent, false);
        return new DogHolderAdmin(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogHolderAdmin holder, int position) {
        Dog dog = dogList.get(position);

        holder.id.setText(String.valueOf(dog.getId()));
        holder.name.setText(dog.getName());
        holder.breed.setText(dog.getBreed());
        holder.age.setText(String.valueOf(dog.getAge()));
        holder.weight.setText(String.valueOf(dog.getWeight()));
        holder.vaccine.setText(dog.getVaccine());

    }



    @Override
    public int getItemCount() { return dogList.size(); }
}

