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

    daysCheck = getString(R.string.isShowDayString);
    eventNameBox = v.findViewById(R.id.nameOfEventTextBox);
    counterDowner = v.findViewById(R.id.Counter);
    dateDisplayer = v.findViewById(R.id.dateContext);

    SharedPreferences sharedPreferences = getContext().getSharedPreferences("nameofCount", Context.MODE_PRIVATE);
    SharedPreferences sharedPreferencesMain = getContext().getSharedPreferences("mainActSharedPref", MODE_PRIVATE);

    Log.d("sharedPreferences hun", sharedPreferences.getString("nameofCountText", "Nowt here"));

    eventNameBox.setText(sharedPreferences.getString("nameofCountText", "No Countdown Name entered"));
    dateDisplayer.setText(sharedPreferencesMain.getString("nameofDate", "No date selected"));
    Log.d("SharedPref", sharedPreferences.getString("nameofDate", "Nothing date in CountdownFragment Text"));

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



    Long counterOnCreate = sharedPreferencesMain.getLong("counterMain", 0);

    Log.d("Counter Countdown Frag", String.valueOf(counterOnCreate));

    counterDowner.start(counterOnCreate);

    return v;
    }

    @Override
    public void onClick(View v) {

    }
    }




