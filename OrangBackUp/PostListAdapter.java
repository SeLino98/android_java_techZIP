package com.example.testorangapp.post.main;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PostListAdapter extends FirebaseRecyclerAdapter<PostTable, PostListAdapter.ViewHolder> {
    private Context context;

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
    public PostListAdapter(@NonNull FirebaseRecyclerOptions<PostTable> options, Context context) {
        super(options);
        Log.e("OPEROPEWQOR",""+options);
        this.context = context;
    }
    @Override //홀더가 갖고있는 뷰에 데이터들을 세팅해줍니다.
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PostTable model) {
        Log.d("ssd",holder.getPosition()+"");
        holder.titleTextView.setText(model.getTitle());
        Glide.with(context).load(model.getMainImageUrl()).into(holder.postImageView);
        holder.tvRowMan.setText(model.getTarget());
        holder.tvRowEndDay.setText(model.getDeadLine());
        holder.tvRowPlace.setText(model.getPlace());
        holder.tvRowSeasonDay.setText(model.getProgress());

        Log.d("titleTextView",""+holder.titleTextView);
        Log.d("titleTextView",""+holder.itemView);
        String str = model.getExpireDate();
        String deadlineText = "";  // declare the variable here

        if (str.equals("NullDate")){
            Log.d("STR1",str);
            holder.titleDeadLine.setText("상시모집");
            deadlineText = "상시모집";
        }else{
            Log.d("STR",str);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate tempDate = LocalDate.parse(str, formatter);
            LocalDate currentDate = LocalDate.now();
            long diff = ChronoUnit.DAYS.between(currentDate, tempDate);
            if (diff<0){
                holder.titleDeadLine.setText("모집마감");
                deadlineText = "모집마감";
            }else {
                String dDayText = "D-"+diff;
                holder.titleDeadLine.setText("D-"+diff);
                deadlineText = dDayText;
            }
        }

        // in ViewHolder
        String finalDeadlineText = deadlineText;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PostViewActivity.class);
                intent.putExtra("postTableData", model);
                intent.putExtra("deadlineText", finalDeadlineText);
                context.startActivity(intent);
            }
        });
        /*
        ArrayList<PostTable> postTableList= new ArrayList<>();
        PostTable postTable = postTableList.get(position);
        holder.titleTextView.setText(postTable.getTitle());
        //이미지 뷰는 글라이드를 사용할 것.
        */
    }
    @NonNull
    @Override //뷰를 담을 수 있는 뷰홀더를 생성해줍니다.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_recycle_post, viewGroup, false);
        //우리가쓸려는 chatMessage아이템의 뷰객체 생성
        return new ViewHolder(view);
        //각각의 chatMessage아이템을 위한 뷰를 담고있는 뷰홀더객체를 반환한다.
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView titleDeadLine;
        TextView tvRowMan;
        TextView tvRowEndDay;
        TextView tvRowPlace;
        TextView tvRowSeasonDay;
        ImageView postImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView); //android View로 임포트함
            postImageView = itemView.findViewById(R.id.image_row_post); //업로드한사진
            titleTextView = itemView.findViewById(R.id.tv_row_post_name);//제목
            titleDeadLine = itemView.findViewById(R.id.tv_row_day);
            tvRowMan = itemView.findViewById(R.id.tv_row_man1);
            tvRowEndDay = itemView.findViewById(R.id.tv_row_end_day1);
            tvRowPlace = itemView.findViewById(R.id.tv_row_place1);
            tvRowSeasonDay = itemView.findViewById(R.id.tv_row_season_day1);
        }
    }
}
