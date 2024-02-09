package com.example.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Broadcast receiver which listens to five headset plugs, and increases its counter in MainActivity.
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 9/2/2024
 */
public class FiveHpPlugsReceiver extends BroadcastReceiver {
    public static final String FIVE_PLUGS_ACTION = "com.example.ex1601.receivers.FiveHpPlugsReceiver";

    /**
     * This function is called when a broadcast of five headset plugs is received. It insures the
     * broadcast action, and increases the suitable counter in MainActivity.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(FIVE_PLUGS_ACTION)) {
            MainActivity.increaseFiveHpPlugsCounter();
        }
    }
}