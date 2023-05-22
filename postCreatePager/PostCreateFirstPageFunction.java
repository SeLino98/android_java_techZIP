package com.example.testorangapp.post.create;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testorangapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class PostCreateFirstPageFunction extends PostCreateFirstPageFragment{
    public BottomSheetDialog bottomSheetDialog;
    public View bottomSheetView;
    protected Bundle bundle;
    private static String BOTTON_TITLE;
    private int selectedYear, selectedMonth, selectedDay;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar = Calendar.getInstance();
    //bottomDaySelected
    private static Button startDate;
    private static Button endDate;
    private Context context ;
    private static int BUTTON_Complete_LABEL = 0;
    public PostCreateFirstPageFunction(BottomSheetDialog bottomSheetDialog,View bottomSheetView,String title){
        this.bottomSheetDialog = bottomSheetDialog;
        this.bottomSheetView = bottomSheetView;
        bundle = new Bundle();
        Log.d("BOTTOMSHERASDVUEW",bottomSheetView+" ");
        TextView textView =bottomSheetView.findViewById(R.id.tv_bottom_sheet_title);
        textView.setText(title);
        BOTTON_TITLE = textView.getText().toString();
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
        this.context = super.context;
        Log.d("ClassFRAGMENT!",context+"");
    }
    public void PostBottomSheet_Complete_Button(){
        Button button = bottomSheetDialog.findViewById(R.id.complete_button_text);
        switch (BUTTON_Complete_LABEL){
            case 1: //TEXT
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }
    public EditText PostBottomSheet_Text(){
        BUTTON_Complete_LABEL = 1; //TEXT
        EditText editText = bottomSheetView.findViewById(R.id.post_bottom_sheet_Text);
        Log.d("asdsad",BOTTON_TITLE+"");
        switch (BOTTON_TITLE){
            case "모집 대상" :
                editText.setHint("대상을 입력해주세요");
                break;
            case "진행 장소" :
                editText.setHint("장소를 입력해주세요");
                break;
            case "신청 링크" :
                editText.setHint("링크를 입력해주세요");
                editText.setHeight(80);
                break;
            default:
                editText.setHint("기한을 입력해주세요.");
                break;
        }
        TextView characterCountTextView = bottomSheetView.findViewById(R.id.characterCountTextView);
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
                bundle.putString("Target_Text",editText.getText().toString());
            }
        });
        return editText;
    }


//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        selectedYear = year;
//        selectedMonth = month;
//        selectedDay = dayOfMonth;
//
//        String date = String.format(Locale.getDefault(), "%d-%02d-%02d", year, month + 1, dayOfMonth);
//
//        if (startDate.isFocused()) {
//            startDate.setText(date);
//        } else if (endDate.isFocused()) {
//            endDate.setText(date);
//        }
//    }
//    private void showDatePickerDialog(boolean isStartDate) {
//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(
//                getContext(),
//                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
//                this,
//                year,
//                month,
//                day
//        );
//
//        if (!isStartDate) {
//            datePickerDialog.getDatePicker().setMinDate(getDateInMillis(startDate.getText().toString()));
//        }
//        datePickerDialog.show();
//    }
//    private long getDateInMillis(String dateString) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
//        try {
//            Date date = sdf.parse(dateString);
//            return date.getTime();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }



    public EditText PostBottomSheet_Link_Text(){
        BUTTON_Complete_LABEL = 3;
        EditText editText = bottomSheetView.findViewById(R.id.post_bottom_sheet_Text);
        TextView characterCountTextView = bottomSheetView.findViewById(R.id.characterCountTextView);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // not used
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentCount = s.length();
                characterCountTextView.setText(currentCount + "/100");
            }
            @Override
            public void afterTextChanged(Editable s) {
                bundle.putString("Target_Text",editText.getText().toString());
            }
        });
        return editText;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (datePickerDialog != null && datePickerDialog.isShowing()) {
            datePickerDialog.dismiss();
        }
    }
}
