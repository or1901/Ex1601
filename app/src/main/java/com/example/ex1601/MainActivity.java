package com.example.ex1601;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The main activity:
 * displays 3 counters - for the phone reboots, for headset plugs and for five headset plugs.
 * @author Ori Roitzaid <or1901 @ bs.amalnet.k12.il>
 * @version	1
 * @since 23/1/2024
 */
public class MainActivity extends AppCompatActivity {
    SharedPreferences bootCounterFile;
    static TextView tvReboot, tvHps, tvFiveHps;
    HpPlugReceiver hpPlugsReceiver;
    FiveHpPlugsReceiver fiveHpPlugsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewsAndBrs();

        // Sets the boot counter to the value saved in the SP file
        bootCounterFile = (SharedPreferences) getSharedPreferences("BOOT_COUNTER", MODE_PRIVATE);
        tvReboot.setText("" + bootCounterFile.getInt("bootCount", 0));
    }

    /**
     * This function initializes the objects of the xml views, and the dynamic broadcast receivers.
     */
    private void initViewsAndBrs() {
        tvReboot = (TextView) findViewById(R.id.tvReboot);
        tvHps = (TextView) findViewById(R.id.tvHps);
        tvFiveHps = (TextView) findViewById(R.id.tvFiveHps);

        hpPlugsReceiver = new HpPlugReceiver();
        fiveHpPlugsReceiver = new FiveHpPlugsReceiver();
    }

    /**
     * This function increases the counter of headset plugs by one.
     */
    public static void increaseHpPlugsCounter() {
        tvHps.setText("" + (Integer.parseInt(tvHps.getText() + "") + 1));
    }

    /**
     * This function increases the counter of five headset plugs by one.
     */
    public static void increaseFiveHpPlugsCounter() {
        tvFiveHps.setText("" + (Integer.parseInt(tvFiveHps.getText() + "") + 1));
    }

    /**
     * This function gets the value saved in the counter of headset plugs.
     * @return The value saved in the counter of headset plugs
     */
    public static int getHpPlugsCounter() {
        return Integer.parseInt("" + tvHps.getText());
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Registers the BR for headset plugs
        IntentFilter hpPlugsFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(hpPlugsReceiver, hpPlugsFilter);

        // Registers the BR for five headset plugs
        IntentFilter fivePlugsFilter = new IntentFilter(FiveHpPlugsReceiver.FIVE_PLUGS_ACTION);
        registerReceiver(fiveHpPlugsReceiver, fivePlugsFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(hpPlugsReceiver);
        unregisterReceiver(fiveHpPlugsReceiver);
    }
}