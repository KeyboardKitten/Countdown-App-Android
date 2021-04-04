package com.example.countdownappversionsecondaprilsurface;

import android.app.DatePickerDialog;
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

public class CountdownFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private String daysCheck;
    private TextView eventNameBox;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.countdownfragment_main, container, false);

    daysCheck = getString(R.string.isShowDayString);
    eventNameBox = v.findViewById(R.id.nameOfEventTextBox);

    

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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

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
