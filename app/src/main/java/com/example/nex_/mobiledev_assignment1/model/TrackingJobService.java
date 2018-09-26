package com.example.nex_.mobiledev_assignment1.model;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;

public class TrackingJobService extends JobService {
    private static final String TAG = "TrackingJobService";
    private boolean jobCanceled = false;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: Job started");
        doBackgroundWOrd(params);
        return true;
    }

    private void doBackgroundWOrd(final JobParameters params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (jobCanceled){
                    return;
                }
                //TODO: Add tracking checking for stopping and location suggestion here
                jobFinished(params, false);
            }

        }).start();
    }
    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "onStopJob: Job cancel before complation");
        jobCanceled = true;
        return false;
    }
}
