package com.example.testorangapp.post;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testorangapp.databinding.FragmentPostHomeBinding;
//import com.example.testorangapp.post.main.PostCardViewAdapter;
import com.example.testorangapp.post.main.PostViewPagerAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class PostHomeFragment extends Fragment {
    ViewPager2 viewPager;
    Context mContext;
    private FragmentPostHomeBinding binding;
    //private FirebaseRecyclerAdapter<PostTable, PostCardViewAdapter.ViewHolder> mPostCardAdapter;
    private FirebaseRecyclerAdapter<PostTable, PostListAdapter.ViewHolder> mPostListAdapter;//mFirebaseAdapter
    private RecyclerView mRecyclerView ;
    private PostViewPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // ViewPager2 어댑터를 생성합니다.
        //mAdapter = new PostViewPagerAdapter(getSupportFragmentManager(), getLifecycle());

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PostViewModel postViewModel =
                new ViewModelProvider(this).get(PostViewModel.class);
        binding = FragmentPostHomeBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();

        DatabaseReference mRootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //"Post/Category/1/"데이터베이스 위치한곳
        DatabaseReference mSeoulDatabaseReference = mRootDatabaseReference.child("Post").child("Category").child("1"); //profile이란 이름의 하위 데이터베이스
        //mRecyclerView = binding.recycle;
        Query query = mSeoulDatabaseReference; //쿼리문의 수행위치 저장 (파이어베이스 리얼타임데이터베이스의 하위에있는 test 데이터를 가져오겠다는 뜻이다. ==> 메세지를 여기다 저장했으므로)
        FirebaseRecyclerOptions<PostTable> options = new FirebaseRecyclerOptions.Builder<PostTable>() //어떤데이터를 어디서갖고올거며 어떠한 형태의 데이터클래스 결과를 반환할거냐 옵션을 정의한다.
                .setQuery(query.limitToFirst(5), PostTable.class)
                .build();
        Log.e("QUREYEQUREWQ",""+query);

       // mPostListAdapter = new PostListAdapter(options, getContext());
       // mPostCardAdapter = new PostCardViewAdapter(options, getContext());
//
//        //카드 뷰 이미지를 띄우는 창
//        SpringDotsIndicator springDotsIndicator = binding.springDotsIndicator;
//        viewPager = binding.cardViewPager;
//        viewPager.setAdapter(mPostCardAdapter);
//        springDotsIndicator.attachTo(viewPager);

        return root;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onStart() {
        super.onStart();
        //mPostCardAdapter.startListening();
        //mPostListAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        //mPostCardAdapter.stopListening();
        //mPostListAdapter.stopListening();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}