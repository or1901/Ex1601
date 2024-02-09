package com.example.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class FiveHpPlugsReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("" + R.string.FIVE_PLUGS_ACTION)) {
            MainActivity.increaseFiveHpPlugsCounter();
        }
    }
}