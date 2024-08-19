package com.example.workout_java.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.workout_java.Activity.DetailActivity;
import com.example.workout_java.Domain.Items;
import com.example.workout_java.databinding.ViewholderBestdealBinding;

import java.util.ArrayList;

public class BestdealAdapter extends RecyclerView.Adapter<BestdealAdapter.Viewholder> {
    ArrayList<Items> items;
    Context context;

    public BestdealAdapter(ArrayList<Items> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public BestdealAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderBestdealBinding binding = ViewholderBestdealBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BestdealAdapter.Viewholder holder, int position) {
        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.priceTxt.setText(items.get(position).getPrice()+" $/Kg");

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .into(holder.binding.bestdealImg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object",items.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ViewholderBestdealBinding binding;
        public Viewholder(@NonNull ViewholderBestdealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
