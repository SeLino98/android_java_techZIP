package com.example.testorangapp.post.create;
import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testorangapp.databinding.FragmentPostCreateSecondPageBinding;

import java.util.List;
public class PostCreateSecondPageFragment extends Fragment {
    private FragmentPostCreateSecondPageBinding binding;
    private ActivityResultLauncher<String> imagePickerLauncher;
    private ActivityResultLauncher<String> permissionLauncher;
    private List<Uri> imageUriList;
    private PostCreateViewModel postCreateViewModel;
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
        Log.d("onCreate로 들어옴 ","");
        super.onCreate(savedInstanceState);
        postCreateViewModel = new ViewModelProvider(this).get(PostCreateViewModel.class);
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetMultipleContents(),
                new ActivityResultCallback<List<Uri>>() {
                    @Override
                    public void onActivityResult(List<Uri> result) {
                        postCreateViewModel.processSelectedImages(result);
                    }
                }
        );
        permissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean isGranted) {
                        if (isGranted) {
                            Log.d("permission",""+isGranted);
                            performDesiredAction(isGranted);
                        } else {
                            Log.d("Denypermission",""+isGranted);
                            performDesiredAction(isGranted);
                        }
                    }
                }
        );
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onCreateView로 들어옴 ","");
        binding = FragmentPostCreateSecondPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        postCreateViewModel.getImageUriListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Uri>>() {
            @Override
            public void onChanged(List<Uri> imageUriList) {
                processSelectedImages(imageUriList);
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.postUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.animator.post_btn_scale_anim);
//                binding.postUploadImage.startAnimation(animation);
                if (postCreateViewModel.isImageCountExceeded()){
                    requestPermission();
                }else{
                    Toast.makeText(getContext(), "10장 이하로 등록해야 합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void openPhotoAlbum() {
        imagePickerLauncher.launch("image/*");
    }
    private void requestPermission() {
        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }
    private void performDesiredAction(boolean isGranted) {
        if (isGranted){
            openPhotoAlbum();
        }else{
            Log.d("permission Denied",""+isGranted);
        }
    }
    private void processSelectedImages(List<Uri> imageList) {
        RecyclerView recyclerView = binding.recyclerViewPhotos;
        PostCreateImageAdapter adapter = new PostCreateImageAdapter(imageList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
