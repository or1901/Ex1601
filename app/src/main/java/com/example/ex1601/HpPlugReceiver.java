package com.example.ex1601;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

/**
 * Broadcast receiver which listens to headset plugs, and increases its counter in MainActivity.
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 6/2/2024
 */
public class HpPlugReceiver extends BroadcastReceiver {
    int counter = 0;

    /**
     * This function is called when headset is plugged/unplugged from the phone. It increases the
     * suitable counter in MainActivity if the action is plugging, and also sends a broadcast of
     * five plugs if needed.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        // If the action is plugging
        if(intent.getExtras().getInt("state", 0) == 1)
        {
            MainActivity.increaseHpPlugsCounter();

            // Sends five plugs broadcast if needed
            counter = MainActivity.getHpPlugsCounter();
            if((counter % 5 == 0) && (counter != 0)) {
                Intent brIntent = new Intent(FiveHpPlugsReceiver.FIVE_PLUGS_ACTION);
                context.sendBroadcast(brIntent);
            }
        }

    }
}