package com.example.testorangapp.post.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testorangapp.R;
import com.example.testorangapp.post.PostTable;
import com.example.testorangapp.post.view.PostViewActivity;

import java.util.List;

public class PostListAdapter1 extends RecyclerView.Adapter<PostListAdapter1.PostViewHolder> {

    private Context context;
    private List<PostTable> postTableList;

    public PostListAdapter1( List<PostTable> postTableList,Context context) {
        this.context = context;
        this.postTableList = postTableList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycle_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostTable post = postTableList.get(position);

        holder.titleTextView.setText(post.getTitle());
        Glide.with(context).load(post.getMainImageUrl()).into(holder.postImageView);
        holder.tvRowMan.setText(post.getTarget());
        holder.tvRowEndDay.setText(post.getDeadLine());
        holder.tvRowPlace.setText(post.getPlace());
        holder.tvRowSeasonDay.setText(post.getProgress());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PostViewActivity.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postTableList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView titleDeadLine;
        TextView tvRowMan;
        TextView tvRowEndDay;
        TextView tvRowPlace;
        TextView tvRowSeasonDay;
        ImageView postImageView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            postImageView = itemView.findViewById(R.id.image_row_post);
            titleTextView = itemView.findViewById(R.id.tv_row_post_name);
            titleDeadLine = itemView.findViewById(R.id.tv_row_day);
            tvRowMan = itemView.findViewById(R.id.tv_row_man1);
            tvRowEndDay = itemView.findViewById(R.id.tv_row_end_day1);
            tvRowPlace = itemView.findViewById(R.id.tv_row_place1);
            tvRowSeasonDay = itemView.findViewById(R.id.tv_row_season_day1);
        }
    }
}
