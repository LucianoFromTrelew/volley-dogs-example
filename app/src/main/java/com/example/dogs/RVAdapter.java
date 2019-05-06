package com.example.dogs;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DogViewHolder> {
    public static class DogViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView dogBreed;
        ImageView dogImage;

        public DogViewHolder(@NonNull View itemView) {
            super(itemView);
            this.cv = (CardView) itemView.findViewById(R.id.cvDog);
            this.dogBreed = (TextView) itemView.findViewById(R.id.tvDog);
            this.dogImage = (ImageView) itemView.findViewById(R.id.ivDog);
        }
    }

    private List<Dog> dogs;

    public RVAdapter(List<Dog> dogs) {
        this.dogs = dogs;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.dog_item_layout, viewGroup, false);
        DogViewHolder pvh = new DogViewHolder(v);
        return pvh;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder dogViewHolder, int i) {
        Dog dog = this.dogs.get(i);
        dogViewHolder.dogBreed.setText(dog.toString());
        Glide
                .with(dogViewHolder.itemView.getContext())
                .load(dog.getImage())
                .placeholder(R.mipmap.ic_dog)
                .into(dogViewHolder.dogImage);
        dogViewHolder.dogImage.setZ(0);
    }

    @Override
    public int getItemCount() {
        return this.dogs.size();
    }
}
