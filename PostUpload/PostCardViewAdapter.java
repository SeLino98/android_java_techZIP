package com.example.testorangapp.post.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testorangapp.R;
import com.example.testorangapp.post.PostActivity;
import com.example.testorangapp.post.PostTable;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class PostCardViewAdapter extends FirebaseRecyclerAdapter<PostTable, PostCardViewAdapter.ViewHolder> {
    Context context;
    static int POST_CARD_SIZE = 5;

    public PostCardViewAdapter(@NonNull FirebaseRecyclerOptions<PostTable> options, Context context) {
        super(options);
        Log.e("OPEROPEWQOR",""+options);
        // options는 파이어베이스 리사이클러뷰에 DB의 쿼리문옵션을 넣어 해당
        // 쿼리문에 맞는 데이터들을 자동 세팅해주기 위해서 사용합니다.
        this.context = context; //Glide 서울액티비티쪽에 사용하기위해 필요
    }

    @Override //홀더가 갖고있는 뷰에 데이터들을 세팅해줍니다.
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PostTable model) {
        Glide.with(context).load(model.getImageUrl()).into(holder.postImageView);
        //Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
        Log.d("titleTextView",""+holder.itemView);
        /*
        ArrayList<PostTable> postTableList= new ArrayList<>();
        PostTable postTable = postTableList.get(position);
        holder.titleTextView.setText(postTable.getTitle());
        //이미지 뷰는 글라이드를 사용할 것.
        */
    }
//
//    @Override
//    public int getItemCount() {
//        return POST_CARD_SIZE;
//    }

    @NonNull
    @Override //뷰를 담을 수 있는 뷰홀더를 생성해줍니다.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_recycle_post_card_view, viewGroup, false);
        //우리가쓸려는 chatMessage아이템의 뷰객체 생성
        return new ViewHolder(view);
        //각각의 chatMessage아이템을 위한 뷰를 담고있는 뷰홀더객체를 반환한다.
    }

    //뷰들을 바인딩 해줍니다.
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView postImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //android View로 임포트함
            postImageView = itemView.findViewById(R.id.postCardImage); //업로드한사진
            itemView.findViewById(R.id.postCardImage).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(view.getContext(), PostActivity.class);
                    intent.putExtra("ItemId", getItemId());
                    Log.d("CARDVIEWCLICKED","::"+getItemId()+"::"+getBindingAdapterPosition());
                }
            });
        }
    }
}

