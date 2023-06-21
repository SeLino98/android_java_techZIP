package com.example.testorangapp.post.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PostViewPagerAdapter extends FragmentStateAdapter {

    public PostViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }
    private static final int NUM_PAGES = 5;
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                PostListFragment p1 = PostListFragment.newInstance("은평오랑");
                return p1;
            case 1:
                PostListFragment p2 = PostListFragment.newInstance("프로그램");
                return p2;
            case 2:
                PostListFragment p3 = PostListFragment.newInstance("청년정책");
                return p3;
            case 3:
                PostListFragment p4 = PostListFragment.newInstance("CJ나눔냉장고");
                return p4;
            default:
                PostListFragment p5 = PostListFragment.newInstance("청년도전사업");
                return p5;
        }
    }

    @Override
    public int getItemCount() {
        Log.d("NUM_PAGES",NUM_PAGES+"");
        return NUM_PAGES;
    }

}
