package com.example.workout_java.Activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.workout_java.Adapter.BestdealAdapter;
import com.example.workout_java.Adapter.SimilarAdapter;
import com.example.workout_java.Domain.Items;
import com.example.workout_java.MainActivity;
import com.example.workout_java.R;
import com.example.workout_java.databinding.ActivityDetailBinding;
import com.example.workout_java.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {
    ActivityDetailBinding binding;
    private Items object;
    private int weight = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBundles();
        initSimilarList();
        setVariables();

    }

    private void initSimilarList() {
        DatabaseReference myRef = database.getReference("Items");
        ArrayList<Items> list = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot issue : snapshot.getChildren()){
                        list.add(issue.getValue(Items.class));
                    }
                    if (!list.isEmpty()){
                        binding.detailView.setLayoutManager(new LinearLayoutManager(DetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        binding.detailView.setAdapter(new SimilarAdapter(list));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setVariables() {
        binding.backImg.setOnClickListener(view -> finish());
        Glide.with(DetailActivity.this).load(object.getImagePath()).into(binding.detailImg);

        binding.detailTxt.setText(object.getTitle());
        binding.desTxt.setText(object.getDescription());
        binding.detailStar.setRating((float) object.getStar());
        binding.kgTxt.setText(object.getPrice()+ " $/Kg");
        binding.totalpriceTxt.setText((weight*object.getPrice())+"$");

        binding.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = weight +1;
                binding.numTxt.setText(weight+" kg");
                binding.totalpriceTxt.setText((weight*object.getPrice())+"$");

            }
        });

        binding.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight > 1){
                    weight = weight -1;
                    binding.numTxt.setText(weight+" kg");
                    binding.totalpriceTxt.setText((weight*object.getPrice())+"$");
                }
            }
        });
    }

    private void getBundles() {
        object = (Items) getIntent().getSerializableExtra("object");
    }
}