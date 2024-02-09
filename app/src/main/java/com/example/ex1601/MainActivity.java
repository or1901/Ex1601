package com.example.ex1601;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences bootCounterFile;
    static TextView tvReboot, tvHps, tvFiveHps;
    HpPlugReceiver hpPlugsReceiver;
    FiveHpPlugsReceiver fiveHpPlugsReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        bootCounterFile = (SharedPreferences) getSharedPreferences("BOOT_COUNTER", MODE_PRIVATE);
        tvReboot.setText("" + bootCounterFile.getInt("bootCount", 0));
    }

    private void initViews() {
        tvReboot = (TextView) findViewById(R.id.tvReboot);
        tvHps = (TextView) findViewById(R.id.tvHps);
        tvFiveHps = (TextView) findViewById(R.id.tvFiveHps);

        hpPlugsReceiver = new HpPlugReceiver();
        fiveHpPlugsReceiver = new FiveHpPlugsReceiver();
    }

    public static void increaseHpPlugsCounter() {
        tvHps.setText("" + (Integer.parseInt(tvHps.getText() + "") + 1));
    }

    public static void increaseFiveHpPlugsCounter() {
        tvFiveHps.setText("" + (Integer.parseInt(tvFiveHps.getText() + "") + 1));
    }

    public static int getHpPlugsCounter() {
        return Integer.parseInt("" + tvHps.getText());
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter hpPlugsFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(hpPlugsReceiver, hpPlugsFilter);

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