package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class CountdownFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.countdownfragment_main, container, false);
    AppCompatButton btnDatePicker = v.findViewById(R.id.buttonDatePicker);
    // https://developer.android.com/reference/androidx/fragment/app/Fragment#getFragmentManager()
    // Double check Fragment Manager has been removed
    getChildFragmentManager();
    btnDatePicker.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getChildFragmentManager(), "date picker");
        }
    });

    return v;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
