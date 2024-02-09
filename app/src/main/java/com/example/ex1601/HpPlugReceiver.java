package com.example.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public class HpPlugReceiver extends BroadcastReceiver {
    int counter = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // If the action is plugging
        if(intent.getExtras().getInt("state", 0) == 1)
        {
            MainActivity.increaseHpPlugsCounter();

            counter = MainActivity.getFiveHpPlugsCounter();
            if((counter % 5 == 0) && (counter != 0)) {
                Intent brIntent = new Intent("" + R.string.FIVE_PLUGS_ACTION);
                context.sendBroadcast(brIntent);
            }
        }

    }
}