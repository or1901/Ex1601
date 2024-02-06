package com.example.ex1601;

import static android.content.Context.MODE_PRIVATE;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class BootReceiver extends BroadcastReceiver {
    SharedPreferences bootCounterFile;
    SharedPreferences.Editor editor;
    int bootCounter = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        bootCounterFile = (SharedPreferences) context.getSharedPreferences("BOOT_COUNTER", MODE_PRIVATE);
        editor = bootCounterFile.edit();

        bootCounter = bootCounterFile.getInt("bootCount", 0);
        editor.putInt("bootCount", bootCounter + 1);
        editor.commit();
    }
}