package com.example.countdownappversionsecondaprilsurface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private static final String GOV_UK = "https://www.gov.uk/bank-holidays";

    private DrawerLayout drawerLayout;

    Intent intent;

//    SharedPreferences sharedPreferencesName = this.getSharedPreferences("nameofCount", MODE_PRIVATE);
//    SharedPreferences sharedPreferencesDate = this.getSharedPreferences("mainActSharedPref", MODE_PRIVATE);






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_goto_share_countdown:
                        intent = new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT, "Hey! Let's countdown to an event together using The Countdown App!");
                        intent.setType("text/plain");
                        this.startActivity(intent);
                return true;
            case R.id.action_goto_view_public_holidays:
                this.startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(GOV_UK)
                ));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

    // REFERENCE https://developer.android.com/reference/android/app/DatePickerDialog.OnDateSetListener
    // Using onDateSetListener to listen for when a date has been set using the dialog, implemented at top
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        SharedPreferences sharedPreferencesMain = this.getSharedPreferences("mainActSharedPref", Context.MODE_PRIVATE);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        sharedPreferencesMain.edit().putString("nameofDate", pickerDateString).apply();


        Log.d("pickerDateStringEntry", pickerDateString);

        Log.d("pickerDateSharedPref", sharedPreferencesMain.getString("nameofDate", "nameofDateSharedPreferenceContainsNothing"));


        // Valid

        try {
            Date now = new Date();

            long currentDateLocal = now.getTime();
            System.out.println("Current Date " + currentDateLocal);
            long pickedDateDialog = calendar.getTimeInMillis();
            System.out.println("Picker Date " + pickedDateDialog);
            long differenceBetweenDates = pickedDateDialog - currentDateLocal;
            Log.d("Counter", String.valueOf(differenceBetweenDates));
            sharedPreferencesMain.edit().putLong("counterMain", differenceBetweenDates).apply();

            Log.d("counterMainSharedPref", String.valueOf(sharedPreferencesMain.getLong("counterMain", 0)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}