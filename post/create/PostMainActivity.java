package com.example.testorangapp.post.main;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testorangapp.R;
import com.example.testorangapp.databinding.ActivityPostMainBinding;
import com.example.testorangapp.post.PostListAdapter;
import com.example.testorangapp.post.PostTable;
import com.example.testorangapp.post.create.PostCreateActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PostMainActivity extends AppCompatActivity {

    private ActivityPostMainBinding binding ;
    //private FirebaseRecyclerAdapter<PostTable, PostCardViewAdapter.ViewHolder> mPostCardAdapter;
    private FirebaseRecyclerAdapter<PostTable, PostListAdapter.ViewHolder> mPostListAdapter;//mFirebaseAdapter
    private RecyclerView mRecyclerView ;
    private PostViewPagerAdapter mAdapter;
    private TabLayout mTabLayout;
    private ViewPager2 cardPager;
    private ViewPager2 tabPager;
    private AppBarConfiguration mAppBarConfiguration;
    private boolean isFabOpen = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        DatabaseReference mRootDatabaseReference = FirebaseDatabase.getInstance().getReference(); //"Post/Category/1/"데이터베이스 위치한곳
//        DatabaseReference mSeoulDatabaseReference = mRootDatabaseReference.child("Post").child("Category").child("1"); //profile이란 이름의 하위 데이터베이스

        binding.fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFab();
            }
        });
        binding.fabTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.postScrollView.smoothScrollTo(0,0,500);
            }
        });
        binding.fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostMainActivity.this,PostCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        Query query = mSeoulDatabaseReference; //쿼리문의 수행위치 저장 (파이어베이스 리얼타임데이터베이스의 하위에있는 test 데이터를 가져오겠다는 뜻이다. ==> 메세지를 여기다 저장했으므로)
//        FirebaseRecyclerOptions<PostTable> options = new FirebaseRecyclerOptions.Builder<PostTable>() //어떤데이터를 어디서갖고올거며 어떠한 형태의 데이터클래스 결과를 반환할거냐 옵션을 정의한다.
//                .setQuery(query.limitToFirst(5), PostTable.class)
//                .build();
//        Log.e("QUREYEQUREWQ",""+query);
        // mPostListAdapter = new PostListAdapter(options, getContext());
        //mPostCardAdapter = new PostCardViewAdapter(options, this);
        //카드 뷰 이미지를 띄우는 창
        //SpringDotsIndicator springDotsIndicator = binding.springDotsIndicator;
        //cardPager = binding.postCardViewPager;
        //cardPager.setAdapter(mPostCardAdapter);
        //springDotsIndicator.attachTo(cardPager);


        // ViewPager2 어댑터를 생성
        mAdapter = new PostViewPagerAdapter(this);
        tabPager = binding.tabPager2;
        mTabLayout = binding.tabLayout;
        // ViewPager2에 어댑터를 연결
        tabPager.setAdapter(mAdapter);
        // TabLayout을 ViewPager2에 연결
        new TabLayoutMediator(mTabLayout, tabPager,
                (tab, position) -> {
                    String categoryResName = "category" + (position + 1);
                    int resId = getResources().getIdentifier(categoryResName, "string", getPackageName());
                    tab.setText(resId);
                }
        ).attach();

        //drawbleLayout
        DrawerLayout drawer = binding.postMainLayout;
        NavigationView navigationView = binding.postNavView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_testshow, R.id.nav_post_home)
//                .setOpenableLayout(drawer)
//                .build();

    }
    private void toggleFab() {
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabPost, "translationY", 0f).start();
            ObjectAnimator.ofFloat(binding.fabAlarm, "translationY", 0f).start();
            binding.fabPost.setVisibility(View.GONE);
            binding.fabAlarm.setVisibility(View.GONE);
            binding.fabMain.setImageResource(R.drawable.ic_fab_add);
        } else {
            binding.fabPost.setVisibility(View.VISIBLE);
            binding.fabAlarm.setVisibility(View.VISIBLE);
            ObjectAnimator.ofFloat(binding.fabPost, "translationY", -210f).start();
            ObjectAnimator.ofFloat(binding.fabAlarm, "translationY", -210f).start();
            binding.fabMain.setImageResource(R.drawable.ic_fab_close);
        }
        isFabOpen = !isFabOpen;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        //mPostCardAdapter.startListening();
        super.onStart();
    }
    @Override
    protected void onStop() {
        //mPostCardAdapter.stopListening();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}