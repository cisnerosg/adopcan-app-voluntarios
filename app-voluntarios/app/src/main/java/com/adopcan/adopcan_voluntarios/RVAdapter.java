package com.adopcan.adopcan_voluntarios;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DogViewHolder> {

    public static class DogViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView dogName;
        TextView dogAge;
        ImageView dogPhoto;

        DogViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            dogName = (TextView)itemView.findViewById(R.id.dog_name);
            dogAge = (TextView)itemView.findViewById(R.id.dog_age);
            dogPhoto = (ImageView)itemView.findViewById(R.id.dog_photo);
        }
    }

    List<Dog> dogs;

    RVAdapter(List<Dog> dogs){
        this.dogs = dogs;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public DogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dogs, viewGroup, false);
        DogViewHolder pvh = new DogViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DogViewHolder dogViewHolder, int i) {
        dogViewHolder.dogName.setText(dogs.get(i).name);
        dogViewHolder.dogAge.setText(dogs.get(i).age);
        dogViewHolder.dogPhoto.setImageResource(dogs.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return dogs.size();
    }
}
