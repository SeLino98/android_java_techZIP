package com.example.testorangapp.post.create;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.testorangapp.R;

public class PostCreateSecondPageFragment extends Fragment {
    public PostCreateSecondPageFragment() {
        // Required empty public constructor
    }
    public static PostCreateSecondPageFragment newInstance(String param1, String param2) {
        PostCreateSecondPageFragment fragment = new PostCreateSecondPageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_create_second_page, container, false);
    }
}