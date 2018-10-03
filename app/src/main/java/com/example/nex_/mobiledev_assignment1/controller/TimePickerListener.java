package com.example.nex_.mobiledev_assignment1.controller;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.example.nex_.mobiledev_assignment1.view.TimePickerFragment;

public class TimePickerListener extends FragmentActivity implements View.OnClickListener {
    private static final TimePickerListener ourInstance = new TimePickerListener();

    public static TimePickerListener getInstance() {
        return ourInstance;
    }
    private TimePickerListener() {
    }
    @Override
    public void onClick(View v) {
        android.support.v4.app.DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");


    }
}
