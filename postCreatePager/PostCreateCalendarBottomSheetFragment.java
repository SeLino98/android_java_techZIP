package com.example.testorangapp.post.create;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.testorangapp.R;
import com.example.testorangapp.databinding.FragmentPostCreateCalendarBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostCreateCalendarBottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostCreateCalendarBottomSheetFragment extends BottomSheetDialogFragment {
    protected FragmentPostCreateCalendarBottomSheetBinding binding;
    private Button btn_StartDate, btn_EndDate;
    private Calendar startDate, endDate;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected static Bundle args;
    private static int VIEW_PAGE = 2; // 2 = calendar
    public PostCreateCalendarBottomSheetFragment() {
        // Required empty public constructor
    }
    public static PostCreateCalendarBottomSheetFragment newInstance(String param1, String param2) {
        PostCreateCalendarBottomSheetFragment fragment = new PostCreateCalendarBottomSheetFragment();
        args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostCreateCalendarBottomSheetBinding.inflate(getLayoutInflater());
        RadioGroup radioGroup =binding.postBottomRadio;
        // bottomSheetDialog.findViewById(R.id.post_bottom_sheet_Text)
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                EditText editText = binding.postBottomSheetText;
                switch (i){
                    case R.id.radio_btn_collect :
                        editText.setText("");
                        editText.setHint("기한을 입력해주세요");
                        binding.postBottomLayoutCalendar.setVisibility(View.GONE);
                        binding.postBottomLayoutText.setVisibility(View.VISIBLE);
                        PostBottomSheet_Calendar_Text();
                        VIEW_PAGE = 1;
                        break;
                    case R.id.radio_btn_deadline:
                        binding.postBottomLayoutCalendar.setVisibility(View.VISIBLE);
                        binding.postBottomLayoutText.setVisibility(View.GONE);
                        btn_StartDate = binding.btnStartDate;
                        btn_EndDate = binding.btnEndDate;
                        startDate = Calendar.getInstance();
                        endDate = Calendar.getInstance();
                        VIEW_PAGE = 2;
                        btn_StartDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDatePickerDialog(true);
                            }
                        });

                        btn_EndDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showDatePickerDialog(false);
                            }
                        });
                        break;
                    case R.id.radio_btn_etc:
                        editText.setText("");
                        editText.setHint("기한을 입력해주세요");
                        binding.postBottomLayoutCalendar.setVisibility(View.GONE);
                        binding.postBottomLayoutText.setVisibility(View.VISIBLE);
                        PostBottomSheet_Calendar_Text();
                        VIEW_PAGE = 3;
                        break;
                    default:
                        break;
                }
            }
        });
        binding.completeButtonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                switch (VIEW_PAGE){
                    case 1:
                        break;
                    case 2:

                        break;
                    case 3:
                        break;
                    default:
                        break;
                }

                onButtonPressed();
            }
        });

        View view = binding.getRoot();

        return view;
        //return inflater.inflate(R.layout.fragment_post_create_calendar_bottom_sheet, container, false);
    }
    private void onButtonPressed() {
        // Retrieve the existing instance of Fragment1 from the FragmentManager
        PostCreateFirstPageFragment fragment1 = (PostCreateFirstPageFragment) requireActivity().getSupportFragmentManager().findFragmentByTag("fragment_post_first_tag");
        String deadline = "";

        deadline = binding.btnStartDate.getText().toString()+"-"+binding.btnEndDate.getText().toString();
        //bund le.putString("deadline",deadline);
        // Your button click logic

        if (fragment1 != null) {
            // Call a custom method in Fragment1 to pass the data
            fragment1.receiveData(deadline);
        }
        dismiss();
    }

    public EditText PostBottomSheet_Calendar_Text(){

        EditText editText =  binding.postBottomSheetText;
        TextView characterCountTextView =binding.characterCountTextView;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // not used
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentCount = s.length();
                characterCountTextView.setText(currentCount + "/15");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return editText;
    }
    private void showDatePickerDialog(final boolean isStartDate) {
        Calendar date = isStartDate ? startDate : endDate;
        Activity activity = getActivity();
        if (activity == null) return; // return if getActivity() returns null

        //R.style.SpinnerDatePickerDialogTheme,
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),

                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(Calendar.YEAR, year);
                        date.set(Calendar.MONTH, monthOfYear);
                        date.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        // Update button text
                        String dateString = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(date.getTime());
                        if (isStartDate) {
                            btn_StartDate.setText(dateString);
                        } else {
                            btn_EndDate.setText(dateString);
                        }
                    }
                }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        if (!isStartDate) { // If setting end date, disable selection of dates before the start date
            datePickerDialog.getDatePicker().setMinDate(startDate.getTimeInMillis() + 24*60*60*1000); // Set min date to one day after the start date
        }
        datePickerDialog.show();
    }
}