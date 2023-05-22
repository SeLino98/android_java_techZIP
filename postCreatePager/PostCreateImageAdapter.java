package com.example.testorangapp.post.create;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testorangapp.R;

import java.util.List;

public class PostCreateImageAdapter extends RecyclerView.Adapter<PostCreateImageAdapter.ImageViewHolder> {
    private List<Uri> imageList;

    public PostCreateImageAdapter(List<Uri> imageList) {
        this.imageList = imageList;
    }
    public void setImageUriList(List<Uri> imageUriList) {
        this.imageList = imageUriList;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_upload_image_row, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Uri imageUri = imageList.get(position);
        holder.bind(imageUri);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewPhoto);
        }

        public void bind(Uri imageUri) {
            // Load the image into the ImageView using an image loading library like Picasso or Glide
            Glide.with(itemView.getContext())
                    .load(imageUri)
                    .into(imageView);
        }
    }
}

