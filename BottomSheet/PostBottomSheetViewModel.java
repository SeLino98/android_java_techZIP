package com.example.testorangapp.post.create;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class PostBottomSheetViewModel extends ViewModel {
    private static Bundle bundle;
    public static BottomSheetDialog bottomSheetDialog;
    public static View bottomSheetView;
    public PostBottomSheetViewModel(BottomSheetDialog bottomSheetDialog, View bottomSheetView){
        bundle = new Bundle();
        this.bottomSheetDialog = bottomSheetDialog;
        this.bottomSheetView = bottomSheetView;
        Log.d("POSTBOTTOMSHEET_CONSTRUCT_CALLED"," "+bottomSheetView);
    }
}
