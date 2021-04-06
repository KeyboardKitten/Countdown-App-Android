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
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_goto:
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

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        System.out.println(pickerDateString);
        // Works up to here
        TextView tvDatePicker = findViewById(R.id.dateContext);
        CountdownView myCountdownView = findViewById(R.id.Counter);

        try {
            tvDatePicker.setText(pickerDateString);
            // Picker date string is not the issue
            Date now = new Date();

            long currentDate = now.getTime();
            long pickerDate = calendar.getTimeInMillis();
            long countDownToPickerDate = pickerDate - currentDate;
            myCountdownView.start(countDownToPickerDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeFragment(Fragment fragment, String backstacktag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .addToBackStack(backstacktag)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else  {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_nav_a){
            changeFragment(new CountdownFragment(), "Countdown View main");
        } else if (id == R.id.menu_nav_b){
            changeFragment(new EditCountdownFragment(), "Edit Countdown Fragment");
        }
        else if (id == R.id.menu_nav_c){
            // Create how to use fragment.
        }


        drawerLayout.closeDrawer(GravityCompat.START);


        return false;
    }
}