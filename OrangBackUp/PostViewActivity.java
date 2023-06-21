package com.example.testorangapp.post.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.testorangapp.R;
import com.example.testorangapp.databinding.ActivityPostViewBinding;
import com.example.testorangapp.post.PostTable;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.List;

public class PostViewActivity extends AppCompatActivity {

    private ActivityPostViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        PostTable postTable = (PostTable) intent.getSerializableExtra("postTableData");
        Log.d("postTTTBALE",postTable.getTitle());

        if(postTable != null) {
            setupView(postTable);
            String deadlineText = intent.getStringExtra("deadlineText");
            binding.expiryDateTextView.setText(deadlineText);
        }

        binding.optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnApplyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void setupView(PostTable postTable) {
        binding.targetTextView.setText(postTable.getTarget());
        binding.deadlineTextView.setText(postTable.getDeadLine());
        binding.placeTextView.setText(postTable.getPlace());
        binding.periodTextView.setText(postTable.getProgress());
        binding.titleTextView.setText(postTable.getTitle());
        binding.contentTextView.setText(postTable.getContent());
        //ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(postTable.getImageUrl());
        //binding.postCardViewPager.setAdapter(adapter);
        ViewPager2 viewPager = findViewById(R.id.postCardViewPager);
        SpringDotsIndicator springDotsIndicator = findViewById(R.id.post_view_images_indicator);
        viewPager.setAdapter(new ImageViewPagerAdapter(postTable.getImageUrl()));
        springDotsIndicator.attachTo(viewPager);
    }

    public class ImageViewPagerAdapter extends RecyclerView.Adapter<ImageViewPagerAdapter.ImageViewHolder> {

        private List<String> imageUrls;

        public ImageViewPagerAdapter(List<String> imageUrls) {
            this.imageUrls = imageUrls;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Replace with your image view layout
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_image_row, parent, false);
            return new ImageViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            String imageUrl = imageUrls.get(position);

            // Load the image using Glide
            Glide.with(holder.imageView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return imageUrls.size();
        }

        public class ImageViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;

            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView2);
            }
        }
    }

}