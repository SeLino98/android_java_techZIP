package com.example.testorangapp.post.create;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testorangapp.databinding.ActivityPostCreateBinding;

public class PostCreateActivity extends AppCompatActivity {
    private ActivityPostCreateBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Fragment 생성 메니져.

    }



}