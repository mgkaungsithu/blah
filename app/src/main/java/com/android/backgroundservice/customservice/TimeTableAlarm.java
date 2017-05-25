package com.android.backgroundservice.customservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by nwt on 10/7/2016.
 */
public class TimeTableAlarm extends Service {

    private Context context;

    // run on another Thread to avoid crash
    private Handler mHandler = new Handler();

    // run with Timer to Rerun
    private static Timer timer;

    public TimeTableAlarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        context = this;
        Toast.makeText(this, "The new Service was Created", Toast.LENGTH_SHORT).show();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimeDisplayTimerTask(), 0, 5000);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        // For time consuming an long tasks you can launch a new thread here...
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
        timer.cancel();
        timer.purge();
    }

    class TimeDisplayTimerTask extends TimerTask {

        @Override
        public void run() {
            // run on another thread
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // display toast
                    Toast.makeText(getApplicationContext(), getDateTime(),
                            Toast.LENGTH_SHORT).show();
                }

            });
        }

        private String getDateTime() {

            SimpleDateFormat sdf = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return sdf.format(new Date());
        }

    }
}
