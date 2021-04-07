package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.iwgang.countdownview.CountdownView;

import static android.content.Context.MODE_PRIVATE;

public class EditCountdownFragment extends Fragment implements  DatePickerDialog.OnDateSetListener {

    EditCountdownFragment(String pickerDateString) {
        this.pickerDateString = pickerDateString;
    }

    SharedPreferences sp;
    AppCompatEditText textbox;
    private TextView eventNameBox;
    TextView tvDatePickerEdit;
    CountdownView myCountdownView;
    CheckBox DaysCheckBox;
    CheckBox HoursCheckBox;
    CheckBox MinutesCheckBox;
    CheckBox SecondsCheckBox;
    CheckBox MillisecondsCheckBox;
    String DaysTextCheckBox;
    ProgressBar progressBar;
    int count = 0;
    Timer timer;
    private String pickerDateString;
    DatePicker view;
    int year;
    int month;
    int dayOfMonth;
    String selectedDate;

    private DatePickerDialog.OnDateSetListener datePickerDialog;

    public EditCountdownFragment() {
        // Empty
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.countdownedit_main, container, false);

        final FragmentManager fm = ((AppCompatActivity)getActivity()).getSupportFragmentManager();
        final Context context = getContext();
        if (context == null) return v;

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        eventNameBox = v.findViewById(R.id.nameOfEventTextBox);

        AppCompatButton btnDatePicker = v.findViewById(R.id.buttonDatePicker);
        AppCompatButton saveButton = v.findViewById(R.id.countdownEditSaveBUtton);
        AppCompatButton holidayAPIButton = v.findViewById(R.id.buttonAPIHoliday);
        final AppCompatEditText textbox = v.findViewById(R.id.countDownNameEditBox);


        final CountdownView myCountdownView = v.findViewById(R.id.Counter);

        final String daysCheckBoxTextTrue = getActivity().getResources().getString(R.string.isShowDayString);
        final String daysCheckBoxTextFalse = getActivity().getResources().getString(R.string.isShowDayString);

//        progressBar = v.findViewById(R.id.progressBar);


        textbox.setText(sp.getString("textboxText", ""));

        getChildFragmentManager();
        // https://developer.android.com/reference/androidx/fragment/app/Fragment#getFragmentManager()
        // Double check Fragment Manager has been removed
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getChildFragmentManager(), "date picker");


//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);

//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog_MinWidth, datePickerDialog, year,month,day);
//
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                MainActivity mainActivity = (MainActivity) getActivity();
//
//
//                mainActivity.onDateSet(view, year, month, dayOfMonth);
//
//                Calendar calendar = Calendar.getInstance();
//                year = calendar.get(Calendar.YEAR);
////                calendar.set(Calendar.YEAR, year);
////                calendar.get(Calendar.YEAR);
//                Log.d("Dates", String.valueOf(year));
////                calendar.set(Calendar.MONTH, month);
//                month = calendar.get(Calendar.MONTH);
//                Log.d("Dates", String.valueOf(month));
////                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//                Log.d("Dates", String.valueOf(dayOfMonth));
//
//
//                String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//                Log.d("Dates", pickerDateString);
//                try {
//                    TextView tvDatePickerEdit = null;
////            tvDatePicker.setText(pickerDateString);
////            Log.d("datePickerStuff", tvDatePicker.getText().toString());
//                    Date now = new Date();
////                    tvDatePickerEdit.setText(pickerDateString);
////                    System.out.println("HELLO " + pickerDateString);
////                    System.out.print("tvDatePickerEdit" + pickerDateString);
//                    long currentDate = now.getTime();
//                    Log.d("Dates", String.valueOf(currentDate));
//                    long pickerDate = calendar.getTimeInMillis();
//                    Log.d("Dates", String.valueOf(pickerDate));
//                    long countDownToPickerDate = pickerDate - currentDate;
//                    Log.d("Dates", String.valueOf(countDownToPickerDate));
//                    SharedPreferences.Editor editorForDates = getContext().getSharedPreferences("DateDifference", MODE_PRIVATE).edit();
//                    editorForDates.putLong("EditDate", countDownToPickerDate);
//                    editorForDates.apply();
//                    Log.d("Dates", "Hello Shared Preferences One");
////                    SharedPreferences.Editor stringDateTitle = getContext().getSharedPreferences("DateNamer", MODE_PRIVATE).edit();
////                    stringDateTitle.putString("NamerOfDate", tvDatePickerEdit.getText().toString());
////                    stringDateTitle.apply();
//                    Log.d("Dates", "Hello Shared Preferences Two");
////                    System.out.println("Total amount of days " + countDownToPickerDate);
//                } catch (Exception e) {
//                    Log.d("Dates", e.toString());
//                    e.printStackTrace();
//                }
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new CountdownFragment(sp), "Countdown Fragment");
//                String textboxContents = textbox.getText().toString();
//
//                sp.edit().putString("textboxText", textboxContents).apply();
                SharedPreferences.Editor editor = getContext().getSharedPreferences("nameofCount", MODE_PRIVATE).edit();
                editor.putString("nameofCount", textbox.getText().toString());
                editor.putString("nameofDate", pickerDateString);
                editor.apply();

//                eventNameBox.setText(sharedPreferences.getString("nameofCount", "Orange is the new black"));

            }
        });

        holidayAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer=new Timer();
//                TimerTask timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        count++;
//                        progressBar.setProgress(count);
//                        if (count==100){
//                            timer.cancel();
//                        }
//                    }
//                };
//                timer.schedule(timerTask, 0, 100);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new PublicHolidaysFragment(), "Public Holiday Fragment");
            }
        });





        return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        System.out.println("pickerDateString "  + pickerDateString);

        SharedPreferences.Editor editor = getContext().getSharedPreferences("nameofCount", MODE_PRIVATE).edit();
        editor.putString("nameOfDate", pickerDateString);
        editor.apply();


        // Valid

        try {
//            dateDisplayer.setText(pickerDateString);
//            System.out.println("date Displayer " + dateDisplayer);
            Date now = new Date();

            long currentDate = now.getTime();
            System.out.println("Current Date " + currentDate);
            long pickerDate = calendar.getTimeInMillis();
            System.out.println("Picker Date " + pickerDate);
            long countDownToPickerDate = pickerDate - currentDate;
            Log.d("Counter", String.valueOf(countDownToPickerDate));
//            System.out.println("Difference " + countDownToPickerDate);
//            counterEditor = getActivity().getSharedPreferences("counterDifference", MODE_PRIVATE).edit();
//            counterEditor.putLong("difference", countDownToPickerDate);
//            counterEditor.apply();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
////                calendar.set(Calendar.YEAR, year);
////                calendar.get(Calendar.YEAR);
//        Log.d("Dates", String.valueOf(year));
////                calendar.set(Calendar.MONTH, month);
//        month = calendar.get(Calendar.MONTH);
//        Log.d("Dates", String.valueOf(month));
////                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//        Log.d("Dates", String.valueOf(dayOfMonth));
//
//
//        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        Log.d("Dates", pickerDateString);
//        try {
//            TextView tvDatePickerEdit = null;
////            tvDatePicker.setText(pickerDateString);
////            Log.d("datePickerStuff", tvDatePicker.getText().toString());
//            Date now = new Date();
////                    tvDatePickerEdit.setText(pickerDateString);
////                    System.out.println("HELLO " + pickerDateString);
////                    System.out.print("tvDatePickerEdit" + pickerDateString);
//            long currentDate = now.getTime();
//            Log.d("Dates", String.valueOf(currentDate));
//            long pickerDate = calendar.getTimeInMillis();
//            Log.d("Dates", String.valueOf(pickerDate));
//            long countDownToPickerDate = pickerDate - currentDate;
//            Log.d("Dates", String.valueOf(countDownToPickerDate));
//            SharedPreferences.Editor editorForDates = getContext().getSharedPreferences("DateDifference", MODE_PRIVATE).edit();
//            editorForDates.putLong("EditDate", countDownToPickerDate);
//            editorForDates.apply();
//            Log.d("Dates", "Hello Shared Preferences One");
////                    SharedPreferences.Editor stringDateTitle = getContext().getSharedPreferences("DateNamer", MODE_PRIVATE).edit();
////                    stringDateTitle.putString("NamerOfDate", tvDatePickerEdit.getText().toString());
////                    stringDateTitle.apply();
//            Log.d("Dates", "Hello Shared Preferences Two");
////                    System.out.println("Total amount of days " + countDownToPickerDate);
//        } catch (Exception e) {
//            Log.d("Dates", e.toString());
//            e.printStackTrace();
//        }
//    }


//    @Override
////    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
////
//////        try {
//////            tvDatePicker.setText(pickerDateString);
//////            System.out.println(pickerDateString);
//////            Log.d("datePickerStuff", tvDatePicker.getText().toString());
//////            Date now = new Date();
//////
//////            long currentDate = now.getTime();
//////            long pickerDate = calendar.getTimeInMillis();
//////            long countDownToPickerDate = pickerDate - currentDate;
//////            myCountdownView.start(countDownToPickerDate);
//////        } catch (Exception e) {
//////            e.printStackTrace();
//////        }
////    }


}
