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
import android.widget.Toast;

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

public class EditCountdownFragment extends Fragment {

    EditCountdownFragment(String pickerDateString) {
        this.pickerDateString = pickerDateString;
    }

    SharedPreferences sp;
    SharedPreferences sharePref;
    private TextView eventNameBox;
    String pickerDateString;
    SharedPreferences.Editor editor;

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

        final FragmentManager fm = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        final Context context = getContext();
        if (context == null) return v;

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharePref = getContext().getSharedPreferences("nameofCount", Context.MODE_PRIVATE);

        eventNameBox = v.findViewById(R.id.nameOfEventTextBox);

        AppCompatButton btnDatePicker = v.findViewById(R.id.buttonDatePicker);
        AppCompatButton saveButton = v.findViewById(R.id.countdownEditSaveBUtton);
        AppCompatButton holidayAPIButton = v.findViewById(R.id.buttonAPIHoliday);
        final AppCompatEditText textbox = v.findViewById(R.id.countDownNameEditBox);

        final CountdownView myCountdownView = v.findViewById(R.id.Counter);

        editor = getContext().getSharedPreferences("nameofCount", MODE_PRIVATE).edit();


        // https://developer.android.com/reference/androidx/fragment/app/Fragment#getFragmentManager()
        // Double check Fragment Manager has been removed
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getChildFragmentManager(), "date picker");
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textbox.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please enter a name for the countdown", Toast.LENGTH_SHORT).show();
                } else {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new CountdownFragment(sp), "Countdown Fragment");
                editor.putString("nameofCountText", textbox.getText().toString());
                editor.apply();

                textbox.setText(sharePref.getString("nameofCountText", ""));

                Log.d("nameofCountText", sharePref.getString("nameofCountText", "nothing"));
                }
            }
        });

        holidayAPIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.changeFragment(new PublicHolidaysFragment(), "Public Holiday Fragment");
            }
        });

        return v;
    }

}
