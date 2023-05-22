package com.example.testorangapp.post.create;


import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.testorangapp.R;
import com.example.testorangapp.databinding.FragmentPostCreateFirstPageBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class PostCreateFirstPageFragment extends Fragment {
    protected FragmentPostCreateFirstPageBinding binding;
    public View view;
    protected Context context;
    private int Count, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar = Calendar.getInstance();
    //bottomDaySelected

    public PostCreateFirstPageFragment(){
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

        Log.d("FRAGMENT!context",context+"");
        Log.d("i made now",Count+"");

    }
    private void navigateToFragmentSecond() {
        // Access the parent activity and ViewPager2
        PostCreateActivity parentActivity = (PostCreateActivity) requireActivity();
        ViewPager2 viewPager = parentActivity.getViewPager();

        // Set the current item to the second tab (index 1)
        viewPager.setCurrentItem(1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostCreateFirstPageBinding.inflate(getLayoutInflater());
        binding.postNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //table에 객체 담아서 액티비티에 저장.
                //Bundle활용 제 2프래그먼트에서는 ViewModel이용
                //Navigation.findNavController(view).navigate(R.id.action_postCreateFirstPageFragment_to_postCreateSecondPageFragment); //뒤에 번들 값추가.
                navigateToFragmentSecond();
            }
        });

        binding.btnSetPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
                View bottomSheetView = getLayoutInflater().inflate(R.layout.post_create_bottom_sheet_text, null);
                String text = binding.tvPagePlace.getText().toString();
                PostCreateFirstPageFunction postCreateFirstPageFunction = new PostCreateFirstPageFunction(bottomSheetDialog,bottomSheetView,text);
                EditText editText = postCreateFirstPageFunction.PostBottomSheet_Text();
                Button button = bottomSheetDialog.findViewById(R.id.complete_button_text);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = editText.getText().toString();
                        binding.tvPostPlace.setText(text);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        binding.btnSetProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnSetLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
                View bottomSheetView = getLayoutInflater().inflate(R.layout.post_create_bottom_sheet_link, null);
                String text = binding.tvPageLink.getText().toString();
                PostCreateFirstPageFunction postCreateFirstPageFunction = new PostCreateFirstPageFunction(bottomSheetDialog,bottomSheetView,text);
                EditText editText = postCreateFirstPageFunction.PostBottomSheet_Link_Text();
                Button button = bottomSheetDialog.findViewById(R.id.complete_button_text);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = editText.getText().toString();
                        binding.tvPostLink.setText(text);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        binding.btnSetCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
                View bottomSheetView = getLayoutInflater().inflate(R.layout.post_create_bottom_sheet, null);
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
                Button button = bottomSheetDialog.findViewById(R.id.complete_button);
                button.setOnClickListener(
                        view1 -> {
                            Log.d("ASDF","button 들옴 ");
                            RadioGroup radioGroup = bottomSheetDialog.findViewById(R.id.radio_group);
                            RadioButton radioButton = bottomSheetDialog.findViewById(radioGroup.getCheckedRadioButtonId());
                            String text = radioButton.getText().toString();
                            binding.tvPostCategory.setText(text);
                            bottomSheetDialog.dismiss();
                        }
                );
            }
        });
        binding.btnSetTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
                View bottomSheetView = getLayoutInflater().inflate(R.layout.post_create_bottom_sheet_text, null);
                String text = binding.tvPageTarget.getText().toString();
                PostCreateFirstPageFunction postCreateFirstPageFunction = new PostCreateFirstPageFunction(bottomSheetDialog,bottomSheetView,text);
                EditText editText = postCreateFirstPageFunction.PostBottomSheet_Text();
                Button button = bottomSheetDialog.findViewById(R.id.complete_button_text);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String text = editText.getText().toString();
                        binding.tvPostTarget.setText(text);
                        bottomSheetDialog.dismiss();
                    }
                });
            }

        });
        binding.btnSetDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostCreateCalendarBottomSheetFragment postCreateCalendarBottomSheetFragment = new PostCreateCalendarBottomSheetFragment();
                postCreateCalendarBottomSheetFragment.show(getChildFragmentManager(),postCreateCalendarBottomSheetFragment.getTag());
                //Log.d("FragmentTAB",postCreateCalendarBottomSheetFragment.getTag()+"");
            }
        });

        view = binding.getRoot();
        return view;
    }
    public void receiveData(String data) {
        // Process the received data here
        // Example: Update UI elements with the received data
        binding.tvPostDeadline.setText(data);
    }
    private void showDatePickerDialog(boolean isStartDate) {
        Log.d("REQUIRECODE",getActivity()+"");
        datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Set the selected date to the calendar object
                        calendar.set(year, monthOfYear, dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
    public void PostBottomSheet_Calendar_Day(){
        //EditText editText = bottomSheetView.findViewById(R.id.post_bottom_sheet_Text);
        String dateSet ;
        Log.d("GSD","SADF"+"");
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        Button startDate = bottomSheetDialog.findViewById(R.id.startDateButton);
        Button endDate = bottomSheetDialog.findViewById(R.id.endDateButton);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(true);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(false);
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        context = getActivity();
        Log.d("FRAGMENT!context2",context+"");
        super.onAttach(context);
    }
    @Override
    public void onDetach() {
        context = getActivity();
        Log.d("FRAGMENT!context3",context+"");
        super.onDetach();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}