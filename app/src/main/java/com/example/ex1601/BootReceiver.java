package com.example.ex1601;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Broadcast receiver which listens to the phone's reboots, and increases the counter of reboots.
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 6/2/2024
 */
public class BootReceiver extends BroadcastReceiver {
    SharedPreferences bootCounterFile;
    SharedPreferences.Editor editor;
    int bootCounter = 0;

    /**
     * This function is called when the phone reboots. It increases the counter of reboots in the
     * shared preferences file by one.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        bootCounterFile = (SharedPreferences) context.getSharedPreferences("BOOT_COUNTER", MODE_PRIVATE);
        editor = bootCounterFile.edit();

        bootCounter = bootCounterFile.getInt("bootCount", 0);
        editor.putInt("bootCount", bootCounter + 1);
        editor.commit();
    }
}