package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import cn.iwgang.countdownview.CountdownView;

import static android.content.Context.MODE_PRIVATE;

public class EditCountdownFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    SharedPreferences sp;
    AppCompatEditText textbox;
    private TextView eventNameBox;
    TextView tvDatePicker;
    CountdownView myCountdownView;
    CheckBox DaysCheckBox;
    CheckBox HoursCheckBox;
    CheckBox MinutesCheckBox;
    CheckBox SecondsCheckBox;
    CheckBox MillisecondsCheckBox;
    String DaysTextCheckBox;
    ProgressBar progressBar;
    int count=0;
    Timer timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.countdownedit_main, container, false);

        Context context = getContext();
        if (context == null) return v;

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        eventNameBox = v.findViewById(R.id.nameOfEventTextBox);

        AppCompatButton btnDatePicker = v.findViewById(R.id.buttonDatePicker);
        AppCompatButton saveButton = v.findViewById(R.id.countdownEditSaveBUtton);
        AppCompatButton holidayAPIButton = v.findViewById(R.id.buttonAPIHoliday);
        final AppCompatEditText textbox = v.findViewById(R.id.countDownNameEditBox);
        TextView tvDatePicker = v.findViewById(R.id.dateContext);
        CountdownView myCountdownView = v.findViewById(R.id.Counter);
        DaysCheckBox = v.findViewById(R.id.checkBoxDays);
        HoursCheckBox = v.findViewById(R.id.checkBoxHours);
        MinutesCheckBox = v.findViewById(R.id.checkBoxMinutes);
        SecondsCheckBox = v.findViewById(R.id.checkBoxSeconds);
        MillisecondsCheckBox = v.findViewById(R.id.checkBoxMillieSeconds);
        final String daysCheckBoxTextTrue = getActivity().getResources().getString(R.string.isShowDayString);
        final String daysCheckBoxTextFalse = getActivity().getResources().getString(R.string.isShowDayString);

        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());

        progressBar = v.findViewById(R.id.progressBar);



        textbox.setText(sp.getString("textboxText", ""));

        getChildFragmentManager();
        // https://developer.android.com/reference/androidx/fragment/app/Fragment#getFragmentManager()
        // Double check Fragment Manager has been removed
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getChildFragmentManager(), "date picker");
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
                editor.apply();

//                eventNameBox.setText(sharedPreferences.getString("nameofCount", "Orange is the new black"));

            }
        });

        holidayAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer=new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        count++;
                        progressBar.setProgress(count);
                        if (count==100){
                            timer.cancel();
                        }
                    }
                };
                timer.schedule(timerTask, 0, 100);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new PublicHolidaysFragment(), "Public Holiday Fragment");
            }
        });

//        DaysCheckBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DaysCheckBox.isChecked()){
//                    daysCheckBoxText.replace(R.string.isShowDayStringTrue, R.string.isShowDayStringFalse);
//                    Log.d("StringCheck", daysCheckBoxText);
//                } else {
//                    daysCheckBoxText.equals("false");
//                    Log.d("StringCheck", daysCheckBoxText);
//                }
//            }
//        });

        return v;
    }



    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        try {
            tvDatePicker.setText(pickerDateString);
            Log.d("datePickerStuff", tvDatePicker.getText().toString());
            Date now = new Date();

            long currentDate = now.getTime();
            long pickerDate = calendar.getTimeInMillis();
            long countDownToPickerDate = pickerDate - currentDate;
            myCountdownView.start(countDownToPickerDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
