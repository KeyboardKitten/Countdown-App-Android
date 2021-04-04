package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class EditCountdownFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.countdownedit_main, container, false);

        Context context = getContext();
        if (context == null) return v;

        sp = PreferenceManager.getDefaultSharedPreferences(getContext());

        AppCompatButton btnDatePicker = v.findViewById(R.id.buttonDatePicker);
        AppCompatButton saveButton = v.findViewById(R.id.countdownEditSaveBUtton);
        AppCompatButton holidayAPIButton = v.findViewById(R.id.buttonAPIHoliday);
        final AppCompatEditText textbox = v.findViewById(R.id.countDownNameEditBox);

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
                mainActivity.changeFragment(new CountdownFragment(), "Countdown Fragment");
                String textboxContents = textbox.getText().toString();

                sp.edit().putString("textboxText", textboxContents).apply();
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.onDateSet(view, year, month, dayOfMonth);
    }
}
