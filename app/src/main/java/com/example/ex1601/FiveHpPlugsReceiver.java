package com.example.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FiveHpPlugsReceiver extends BroadcastReceiver {
    public static final String FIVE_PLUGS_ACTION = "com.example.ex1601.receivers.FiveHpPlugsReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(FIVE_PLUGS_ACTION)) {
            MainActivity.increaseFiveHpPlugsCounter();
        }
    }
}