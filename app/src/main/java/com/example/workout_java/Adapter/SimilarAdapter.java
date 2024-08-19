package com.example.workout_java.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.workout_java.Domain.Items;
import com.example.workout_java.databinding.ViewholderSimilarBinding;

import java.util.ArrayList;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.ViewHolder> {
    ArrayList<Items> list;
    Context context;
    public SimilarAdapter(ArrayList<Items> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SimilarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderSimilarBinding binding = ViewholderSimilarBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAdapter.ViewHolder holder, int position) {
        Glide.with(context)
                .load(list.get(position).getImagePath())
                .into(holder.binding.similarImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewholderSimilarBinding binding;
        public ViewHolder(ViewholderSimilarBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
