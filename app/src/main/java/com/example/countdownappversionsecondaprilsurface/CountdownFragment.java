package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
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

import cn.iwgang.countdownview.CountdownView;

public class CountdownFragment extends Fragment {

    private String daysCheck;
    private TextView eventNameBox;
    private SharedPreferences sp;
    private TextView dateDisplayer;

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

    dateDisplayer = v.findViewById(R.id.dateContext);
    CountdownView counterDowner = v.findViewById(R.id.Counter);

    daysCheck = getString(R.string.isShowDayString);
    eventNameBox = v.findViewById(R.id.nameOfEventTextBox);

    SharedPreferences sharedPreferences = getContext().getSharedPreferences("nameofCount", Context.MODE_PRIVATE);
    SharedPreferences datePreference = getContext().getSharedPreferences("DateDifference", Context.MODE_PRIVATE);
//    SharedPreferences nameOfDate = getContext().getSharedPreferences("DateNamer", Context.MODE_PRIVATE);

    Log.d("sharedPreferences hun", sharedPreferences.getString("nameofCount", "Nowt here"));

    eventNameBox.setText(sharedPreferences.getString("nameofCount", "No Countdown Name entered"));

//    dateDisplayer.setText(nameOfDate.getString("NamerOfDate", "No Date"));

//        System.out.println("hello" + eventNameBox.getText().toString());

    Log.d("sharedPreferences2", (String) eventNameBox.getText());

    System.out.println("Counter down time " + datePreference.getLong("Nnnn", 0));

//    Log.d("DateDifference hun", datePreference.getLong("Nnnn", 0));

    Long dateSeperation = datePreference.getLong("Nnnn", 0);

    System.out.println("Date Seperation " + dateSeperation);

    counterDowner.start(dateSeperation);

    System.out.println("Apple " + counterDowner);


    

//    onCheckboxClicked(v);

    FloatingActionButton fabEditButton = v.findViewById(R.id.fabEditIcon);

    fabEditButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.changeFragment(new EditCountdownFragment(), "edit countdown");

        }
    });





    return v;
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



}
