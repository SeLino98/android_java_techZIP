package com.example.testorangapp.post;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testorangapp.databinding.FragmentPostListBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PostListFragment extends Fragment {
    private FragmentPostListBinding binding;
    private FirebaseRecyclerAdapter<PostTable, PostListAdapter.ViewHolder> mFirebaseAdapter;
    private RecyclerView mRecyclerView ;
    Context mContext ;
    private static String category = "";
    public static PostListFragment newInstance(String category) {
        PostListFragment fragment = new PostListFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        fragment.setArguments(args);
        return fragment;
    }
    public PostListFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.category = getArguments().getString("category");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostListBinding.inflate(getLayoutInflater());
        Log.d("SCATEGORY",category);
//        DatabaseReference mRootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //"Post/Category/1/"데이터베이스 위치한곳
//        DatabaseReference mSeoulDatabaseReference = mRootDatabaseReference.child("Post").child("Category").child("1"); //profile이란 이름의 하위 데이터베이스
        DatabaseReference mRootDatabaseReference = FirebaseDatabase.getInstance().getReference();
        //DatabaseReference mCategoryDatabaseReference = mRootDatabaseReference.child("Post").child(category);
        DatabaseReference mCategoryDatabaseReference = mRootDatabaseReference.child("Post").child(category);
        mRecyclerView = binding.postListRecycler;
        Query query = mCategoryDatabaseReference; //쿼리문의 수행위치 저장 (파이어베이스 리얼타임데이터베이스의 하위에있는 test 데이터를 가져오겠다는 뜻이다. ==> 메세지를 여기다 저장했으므로)
        FirebaseRecyclerOptions<PostTable> options = new FirebaseRecyclerOptions.Builder<PostTable>() //어떤데이터를 어디서갖고올거며 어떠한 형태의 데이터클래스 결과를 반환할거냐 옵션을 정의한다.
                .setQuery(query, PostTable.class)
                .build();
        Log.e("QUREYEQUREWQ",""+query);

        mFirebaseAdapter = new PostListAdapter(options, getContext());
        // 리사이클러뷰에 레이아웃 매니저와 어댑터를 설정한다.
        GridLayoutManager layoutManager = new GridLayoutManager(mContext,1);
        Log.e("GETCPOCOLNCONC", ""+getContext());
        layoutManager.setReverseLayout(true);
        mRecyclerView.setLayoutManager(layoutManager); ////만든 레이아웃매니저 객체를(설정을) 리사이클러 뷰에 설정해줌
        mRecyclerView.setAdapter(mFirebaseAdapter);
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseAdapter.stopListening();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}