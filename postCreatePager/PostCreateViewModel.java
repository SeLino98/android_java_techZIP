package com.example.testorangapp.post.create;

import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PostCreateViewModel extends ViewModel {
    private MutableLiveData<List<Uri>> imageUriListLiveData;
    private List<Uri> imageUriList ;
    public PostCreateViewModel() {
        imageUriListLiveData = new MutableLiveData<>(new ArrayList<Uri>()); //저장한 List를 담는 객체
        imageUriList = new ArrayList<Uri>(); //엘범에서 값 저장한 List
    }
    public MutableLiveData<List<Uri>> getImageUriListLiveData() {
        return imageUriListLiveData;
    }
    public void setImageUriListLiveData(List<Uri> imageUriList) {
        this.imageUriListLiveData.setValue(imageUriList);
    }
    public boolean isImageCountExceeded() {
        return imageUriListLiveData.getValue().size() <= 10;
    }
    public boolean isSelectedImageCount(Uri selectedImageUri){
        if (imageUriList.size() <= 10){
            imageUriList.add(selectedImageUri);
            return true;
        }else {
            return false;
        }
    }
    public boolean processSelectedImages(List<Uri> result) {
        if (result != null && !result.isEmpty()) {
            int selectedImageCount = result.size();
            if (selectedImageCount == 1) {
                // Only one image selected
                Uri selectedImageUri = result.get(0);
                isSelectedImageCount(selectedImageUri);
                Log.d("Selected Image", selectedImageUri.toString());
            } else if (selectedImageCount <= 10) {
                // Multiple images selected
                for (int i = 0; i < selectedImageCount; i++) {
                    Uri selectedImageUri = result.get(i);
                    isSelectedImageCount(selectedImageUri);
                    Log.d("Selected Image " + (i + 1), selectedImageUri.toString());
                }
            }
            if (isImageCountExceeded()){
                setImageUriListLiveData(imageUriList);
                return true;
            }
        }
        return false;
    }



}

