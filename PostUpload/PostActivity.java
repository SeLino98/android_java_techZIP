package com.example.testorangapp.post;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testorangapp.R;
import com.example.testorangapp.databinding.ActivityPostBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class PostActivity extends AppCompatActivity {


    //Post를 작성하는 페이지.


    //TESTTTT
    private Spinner spinner2;
    ArrayList<String> arrayList;
    ArrayAdapter<CharSequence> arrayAdapter;

    //이미지 저장 파이어스토어
    private FirebaseStorage mStorage;
    private StorageReference storageRef ;

    //리사이클뷰에 띄울 대표이미지 값 선정.
    private int mainImageNum = 1;
    private DatabaseReference mDatabaseRef;
    private FirebaseAuth mFirebaseAuth;
    ActivityPostBinding activityPostBinding;
    ArrayList<Uri> uriList = new ArrayList<>();     // 이미지의 uri를 담을 ArrayList 객체

    //임의로 카테고리 지정 for Test.
    private String category = "1" ;

    PostTable postTable ;
    PostViewModel postViewModel;
    //Content
    private Context mContext;



    //임의로 카테고리 지정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityPostBinding = ActivityPostBinding.inflate(getLayoutInflater());
        postViewModel =
                new ViewModelProvider(() -> new ViewModelStore()).get(PostViewModel.class);
        setContentView(activityPostBinding.getRoot());
        //파이어베이스에 이미지 업로드 인스턴스와 참조생성
        mStorage = FirebaseStorage.getInstance();
        storageRef = mStorage.getReference();
        //파이어베이스 인증과 게시글 내용 등록 인스턴스 생성 .
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        arrayAdapter = ArrayAdapter.createFromResource(this, R.array.question, android.R.layout.simple_spinner_dropdown_item);
        getApplicationContext();
        spinner2 = (Spinner)findViewById(R.id.txt_question_type);
        spinner2.setAdapter(arrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),spinner2.getSelectedItem() +"가 선택되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        //클릭 이벤트
        PostUploadButtonClickListener1 regClick = new PostUploadButtonClickListener1();
        activityPostBinding.btnRegPost.setOnClickListener(regClick);
        UploadImageButtonClickListener imageClick = new UploadImageButtonClickListener();
        activityPostBinding.btnRegImage.setOnClickListener(imageClick);
    }
    class PostUploadButtonClickListener1 implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            postTable.setPostUser("");
            postTable.setCategory("");
            postTable.setTitle("");
            postTable.setContent("");
            postTable.setApplyLink("");
            postViewModel.PostUpload(postTable);
        }
    }
    class PostUploadButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
            String content = activityPostBinding.etContent.getText().toString();
            String title = activityPostBinding.etTitle.getText().toString();

            //파이어베이스에 게시글 내용 업로드
            //DB.CreatePostTable(title,content,firebaseUser.getUid());
            Log.e("ASDF","DASFD");
            PostTable postTable = new PostTable();
            postTable.setPostUser(firebaseUser.getUid());
            postTable.setContent(content);
            postTable.setTitle(title);
//            mDatabaseRef.child("Post").child("Category").child(category).child(title).setValue(postTable)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(PostActivity.this, "등록 성공", Toast.LENGTH_SHORT).show();
//                                // 성공 메세지 출력 후 이미지 저장DB문을 스레드로 넘기기 .
//                                //이미지 저장.
//                                //이미지 저장은 백그라운드로 넘겨야 됨.
//                                for(int i = 0; i<uriList.size();i++) {
//                                    Log.d("uriListSize:",""+uriList.size());
//                                    StorageReference riversRef = storageRef.child("Orang/"+"Post/"+"Images/"+title+"/"+title+i);
//                                    UploadTask uploadTask = riversRef.putFile(uriList.get(i));
//                                    uploadTask.addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(PostActivity.this, "업로드 실패", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                        @Override
//                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                            //이미지 저장 성공
//                                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                                @Override
//                                                public void onSuccess(Uri uri) {
//                                                    postTable.setImageUrl(uri.toString());
//                                                    Log.d("ImageUrlgetUploadSessionUri3 : ",""+uri.toString());
//                                                    if(mainImageNum == 1 ){
//                                                        mDatabaseRef.child("Post").child("Category").child(category).child(title).child("imageUrl").setValue(postTable.getImageUrl());
//                                                    }
//                                                    mDatabaseRef.child("Post").child("Category").child(category).child(title).child("image").push().setValue(postTable.getImageUrl());
//                                                    Toast.makeText(PostActivity.this, "업로드 성공", Toast.LENGTH_SHORT).show();
//                                                    mainImageNum ++;
//                                                }
//                                            });
//                                        }
//                                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//                                                @Override
//                                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
//                                                }
//                                            });
//                                }
//                            }
//                            else{
//                                Toast.makeText(PostActivity.this, "등록 실패", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
            finish();
        }
    }
    class UploadImageButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            //ACTION_OPEN_DOCUMENT
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 2222);
        }
    }
    //이미지 등록 버튼 클릭 후 이미지 선택 후 돌아오는 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("resultCode : ",resultCode+"");  //성공했을 때 -1 리턴
        Log.d("requestCode : ",requestCode+"" ); //2222 리턴

        //data : 이미지
        if(data == null){
            //이미지 선택안한 경우
            Toast.makeText(this, "이미지 선택을 하지 않았습니다.", Toast.LENGTH_SHORT).show();
        }
        else{ // data 값이 null 이 아닌 경우
            if(data.getClipData() == null){
                //이미지 한 장 선택한 경우
                //ClipData clipData = data.getClipData();
                //Uri imageUri = clipData.getItemAt(0).getUri();
                Uri imageUri = data.getData();
                uriList.add(imageUri);
            }else{//이미지 여러장 선택한 경우
                ClipData clipData = data.getClipData();
                if(clipData.getItemCount()>10){
                    //선택 이미지가 10장 이상인 경우
                }else{ //1~10장인 경우
                    for(int i = 0; i < clipData.getItemCount(); i++){
                        //선택된 이미지 uri들을 가져온다.
                        Uri imageUri = clipData.getItemAt(i).getUri();
                        try {
                            uriList.add(imageUri);
                            Log.d("imageUri",":"+imageUri);
                        }catch (Exception e){
                            Log.e("ExceptionError","e : "+ e);
                        }
                    }
                    //xml 페이지에 이미지 업로드_어뎁터
                    PostImageAdapter adapter1 = new PostImageAdapter(uriList, getApplicationContext());
                    activityPostBinding.imageRecyclerView.setAdapter(adapter1);
                    RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
                    activityPostBinding.imageRecyclerView.setLayoutManager(layoutManager1);
//                    postImageAdapter = new PostImageAdapter(uriList, getApplicationContext());
//                    recyclerView.setAdapter(postImageAdapter);
//                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
                }
            }
        }
    }
}