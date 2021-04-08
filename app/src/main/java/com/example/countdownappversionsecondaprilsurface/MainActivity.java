package com.example.countdownappversionsecondaprilsurface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private DrawerLayout drawerLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_goto_share_countdown:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Testing ana oop");
                intent.setType("text/plain");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.main_nav_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                (R.string.nav_open),
                (R.string.nav_drawer_closed)
        );

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_panel);
        navigationView.setNavigationItemSelectedListener(this);


        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, new CountdownFragment())
                .commit();
    }
//
//    public void dateSetting(DatePicker view, int year, int month, int dayOfMonth){
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        TextView tvDatePicker = findViewById(R.id.dateContext);
//        CountdownView myCountdownView = findViewById(R.id.Counter);
//
//        try {
//            tvDatePicker.setText(pickerDateString);
//            Date now = new Date();
//
//            long currentDate = now.getTime();
//            long pickerDate = calendar.getTimeInMillis();
//            long countDownToPickerDate = pickerDate - currentDate;
//            myCountdownView.start(countDownToPickerDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        System.out.println(pickerDateString);
//        // Works up to here
//        TextView tvDatePicker = findViewById(R.id.dateContext);
//        CountdownView myCountdownView = findViewById(R.id.Counter);
//
//        try {
//            tvDatePicker.setText(pickerDateString);
//            // Picker date string is not the issue
//            Date now = new Date();
//
//            long currentDate = now.getTime();
//            long pickerDate = calendar.getTimeInMillis();
//            long countDownToPickerDate = pickerDate - currentDate;
//            myCountdownView.start(countDownToPickerDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public void changeFragment(Fragment fragment, String backstacktag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(backstacktag)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nav_a) {
            changeFragment(new CountdownFragment(), "Countdown View main");
        } else if (id == R.id.menu_nav_b) {
            changeFragment(new EditCountdownFragment(), "Edit Countdown Fragment");
        } else if (id == R.id.menu_nav_c) {
            changeFragment(new HowToUseFragment(), "How to use");
        } else if (id == R.id.menu_nav_d) {
            System.exit(0);
        }


        drawerLayout.closeDrawer(GravityCompat.START);


        return false;
    }

//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
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
//            SharedPreferences.Editor editorForDates = this.getSharedPreferences("DateDifference", MODE_PRIVATE).edit();
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
//
////        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//////        System.out.println(pickerDateString);
////        TextView tvDatePicker = findViewById(R.id.dateContext);
////        CountdownView myCountdownView = findViewById(R.id.Counter);
////
////        try {
//////            tvDatePicker.setText(pickerDateString);
//////            Log.d("datePickerStuff", tvDatePicker.getText().toString());
////            Date now = new Date();
////
////            long currentDate = now.getTime();
////            long pickerDate = calendar.getTimeInMillis();
////            long countDownToPickerDate = pickerDate - currentDate;
////            System.out.println(countDownToPickerDate);
////            myCountdownView.start(countDownToPickerDate);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }

//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        System.out.println(pickerDateString);
//        TextView tvDatePicker = findViewById(R.id.dateContext);
//        CountdownView myCountdownView = findViewById(R.id.Counter);
//
//        try {
//            tvDatePicker.setText(pickerDateString);
//            Log.d("datePickerStuff", tvDatePicker.getText().toString());
//            Date now = new Date();
//
//            long currentDate = now.getTime();
//            long pickerDate = calendar.getTimeInMillis();
//            long countDownToPickerDate = pickerDate - currentDate;
//            myCountdownView.start(countDownToPickerDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

//        SharedPreferences sharedPreferencesMain = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences sharedPreferencesMain = this.getSharedPreferences("mainActSharedPref", Context.MODE_PRIVATE);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        sharedPreferencesMain.edit().putString("nameofDate", pickerDateString).apply();


        Log.d("pickerDateStringEntry", pickerDateString);

        Log.d("pickerDateSharedPref", sharedPreferencesMain.getString("nameofDate", "nameofDateSharedPreferenceContainsNothing"));


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
            sharedPreferencesMain.edit().putLong("counterMain", countDownToPickerDate).apply();

            Log.d("counterMainSharedPref", String.valueOf(sharedPreferencesMain.getLong("counterMain", 0)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}