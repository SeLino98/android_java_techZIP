package com.example.testorangapp.post.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testorangapp.post.PostTable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostListViewModel extends ViewModel {

    private MutableLiveData<List<PostTable>> postTableListLiveData;
    private List<PostTable> postTableList = new ArrayList<>();
    private DatabaseReference mCategoryDatabaseReference;

    public PostListViewModel() {
        postTableListLiveData = new MutableLiveData<>();
        mCategoryDatabaseReference = FirebaseDatabase.getInstance().getReference().child("Post").child("your_category");

        mCategoryDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postTableList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    PostTable postTable = postSnapshot.getValue(PostTable.class);
                    postTableList.add(postTable);
                }
                postTableListLiveData.setValue(postTableList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public LiveData<List<PostTable>> getPostTableListLiveData() {
        return postTableListLiveData;
    }
}

