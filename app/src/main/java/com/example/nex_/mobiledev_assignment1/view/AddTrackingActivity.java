package com.example.nex_.mobiledev_assignment1.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.nex_.mobiledev_assignment1.controller.Listeners;
import com.example.nex_.mobiledev_assignment1.R;

public class AddTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tracking);
        final Button button = (Button) findViewById(R.id.button25);

        button.setOnClickListener(Listeners.getInstance());
    }
}
