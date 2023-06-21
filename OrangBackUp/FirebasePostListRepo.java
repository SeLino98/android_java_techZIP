package com.example.testorangapp.post.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testorangapp.post.PostTable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebasePostListRepo {

    private final DatabaseReference mRootDatabaseReference;
    private MutableLiveData<List<PostTable>> postsMutableLiveData;

    public FirebasePostListRepo() {
        mRootDatabaseReference = FirebaseDatabase.getInstance().getReference();
        postsMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<List<PostTable>> getPosts(String category) {
        DatabaseReference mCategoryDatabaseReference = mRootDatabaseReference.child("Post").child(category);
        mCategoryDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<PostTable> posts = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    PostTable postTable = postSnapshot.getValue(PostTable.class);
                    posts.add(postTable);
                }
                postsMutableLiveData.setValue(posts);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle potential errors.
            }
        });
        return postsMutableLiveData;
    }
}
