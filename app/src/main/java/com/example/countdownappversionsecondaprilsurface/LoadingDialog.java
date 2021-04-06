package com.example.countdownappversionsecondaprilsurface;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.LayoutInflater;

public class LoadingDialog {

    Activity activity;
    AlertDialog dialog;

    LoadingDialog(Activity myActivity){
        activity = myActivity;
    }


    void loadingAnimation(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.progress_loader, null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }

    void dismissLoading(){
        dialog.dismiss();
    }
}
