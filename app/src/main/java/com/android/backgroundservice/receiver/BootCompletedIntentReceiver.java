package com.android.backgroundservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.backgroundservice.customservice.TimeTableAlarm;

/**
 * Created by nwt on 10/7/2016.
 */
public class BootCompletedIntentReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.i("TEST RECEIVER : ", "WORK");
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            Intent pushIntent = new Intent(context, TimeTableAlarm.class);
            context.startService(pushIntent);
        }
    }
}
