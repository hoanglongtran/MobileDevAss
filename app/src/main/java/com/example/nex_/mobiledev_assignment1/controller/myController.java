package com.example.nex_.mobiledev_assignment1.controller;



import android.content.Intent;
import android.view.View;
import com.example.nex_.mobiledev_assignment1.model.TestTrackingService;
import com.example.nex_.mobiledev_assignment1.view.AddTrackingActivity;
import com.example.nex_.mobiledev_assignment1.view.MainActivity;

public class myController implements View.OnClickListener {
    private static final myController ourInstance = new myController();

    public static myController getInstance() {
        return ourInstance;
    }
    private myController() {
    }
    @Override
    public void onClick(View v) {
        // Code here executes on main thread after user presses button
        // TODO: add code here
        Intent addTracking = new Intent(v.getContext(), AddTrackingActivity.class);
        v.getContext().startActivity(addTracking);
        TestTrackingService.test(v.getContext());
    }
}
