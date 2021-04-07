package com.example.countdownappversionsecondaprilsurface;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

import static android.content.Context.MODE_PRIVATE;

public class CountdownFragment extends Fragment implements View.OnClickListener {

    private String daysCheck;
    private TextView eventNameBox;
    private SharedPreferences sp;
    TextView dateDisplayer;
    CountdownView counterDowner;
    SharedPreferences.Editor counterEditor;

    public CountdownFragment(SharedPreferences sp) {
        this.sp = sp;
    }

    public CountdownFragment() {
        this.sp = sp;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.countdownfragment_main, container, false);


    Context mContext= getContext();

        if (container == null) {
            return null;
        }

        RelativeLayout ii = (RelativeLayout)inflater.inflate(R.layout.countdownfragment_main, container, false);
        dateDisplayer = (TextView) ii.findViewById(R.id.dateContext);

//    v.findViewById(R.id.action_goto_share_countdown).setOnClickListener(this);

    daysCheck = getString(R.string.isShowDayString);
    eventNameBox = v.findViewById(R.id.nameOfEventTextBox);
    counterDowner = v.findViewById(R.id.Counter);
    dateDisplayer = v.findViewById(R.id.dateContext);

    SharedPreferences sharedPreferences = getContext().getSharedPreferences("nameofCount", Context.MODE_PRIVATE);
//    SharedPreferences datePreference = getContext().getSharedPreferences("DateDifference", Context.MODE_PRIVATE);
//    SharedPreferences datePickerCaller = getContext().getSharedPreferences("datePickerTotalString", MODE_PRIVATE);

    Log.d("sharedPreferences hun", sharedPreferences.getString("nameofCount", "Nowt here"));

    eventNameBox.setText(sharedPreferences.getString("nameofCount", "No Countdown Name entered"));
//    counterDowner.start(sharedPreferences.getLong("nameOfDate", 0));
    dateDisplayer.setText(sharedPreferences.getString("nameOfDate", "No date selected"));

//    dateDisplayer.setText(nameOfDate.getString("NamerOfDate", "No Date"));

//        System.out.println("hello" + eventNameBox.getText().toString());

//    Log.d("sharedPreferences2", (String) eventNameBox.getText());
//
//    System.out.println("Counter down time " + datePreference.getLong("Nnnn", 0));
//
////    Log.d("DateDifference hun", datePreference.getLong("Nnnn", 0));
//
//    Long dateSeperation = datePreference.getLong("Nnnn", 0);
//
//    System.out.println("Date Seperation " + dateSeperation);
//
//    counterDowner.start(dateSeperation);
//
//    System.out.println("Apple " + counterDowner);


    

//    onCheckboxClicked(v);

    FloatingActionButton fabEditButton = v.findViewById(R.id.fabEditIcon);

    fabEditButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragment(new EditCountdownFragment(), "edit countdown");

        }
    });



    SharedPreferences prefs = getContext().getSharedPreferences("counterDifference", Context.MODE_PRIVATE);
    Long counterOnCreate = prefs.getLong("difference", 0);

    Log.d("Counter", String.valueOf(counterOnCreate));

    counterDowner.start(counterOnCreate);

    return v;
    }

    @Override
    public void onClick(View v) {
//        int id = v.getId();
//
//        Intent i = null;
//        switch (id) {
//            case R.id.action_goto_share_countdown:
//                i = new Intent(Intent.ACTION_SEND);
//                i.putExtra(Intent.EXTRA_TEXT, "Testing ana oop");
//                i.setType("text/plain");
//                break;
//        }
    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        System.out.println("pickerDateString "  + pickerDateString);
//
////        SharedPreferences.Editor datePickerStringSP = getActivity().getSharedPreferences("datePickerString", MODE_PRIVATE).edit();
////        datePickerStringSP.putString("datePickerStringTotal", pickerDateString);
////        datePickerStringSP.apply();
//
//
//        // Valid
//
//        try {
////            dateDisplayer.setText(pickerDateString);
////            System.out.println("date Displayer " + dateDisplayer);
//            Date now = new Date();
//
//            long currentDate = now.getTime();
//            System.out.println("Current Date " + currentDate);
//            long pickerDate = calendar.getTimeInMillis();
//            System.out.println("Picker Date " + pickerDate);
//            long countDownToPickerDate = pickerDate - currentDate;
//            Log.d("Counter", String.valueOf(countDownToPickerDate));
////            System.out.println("Difference " + countDownToPickerDate);
////            counterEditor = getActivity().getSharedPreferences("counterDifference", MODE_PRIVATE).edit();
////            counterEditor.putLong("difference", countDownToPickerDate);
////            counterEditor.apply();
//            counterDowner.start(countDownToPickerDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
//        Log.d("Dates", "Value of date " + pickerDateString);
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
//
////            SharedPreferences editorForDatesTwo = PreferenceManager.getDefaultSharedPreferences(getContext());
////            Editor editorForDatesTwoEditor = editorForDatesTwo.edit();
////            editorForDatesTwoEditor.putLong("EditDateTwo", countDownToPickerDate);
////            editorForDatesTwoEditor.apply();
////            SharedPreferences.Editor editorForDates = getContext().getSharedPreferences("DateDifference", MODE_PRIVATE).edit();
////            editorForDates.putLong("EditDate", countDownToPickerDate);
////            editorForDates.apply();
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
    }


//    public void onCheckboxClicked(View view) {
//        // Is the view now checked?
//        boolean checked = ((CheckBox) view).isChecked();
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.checkBoxDays:
//                if (checked) {
//                    daysCheck.replace(daysCheck, "true");
//                    Log.d("checkboxes", "ticked true - days");
//                } else {
//                    daysCheck.replace(daysCheck, "false");
//                    Log.d("checkboxes", "ticked false - days");
//                }
//        }
//    }

