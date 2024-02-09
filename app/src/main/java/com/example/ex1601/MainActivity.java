package com.example.ex1601;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences bootCounterFile;
    static TextView tvReboot, tvHps;
    HpPlugReceiver hpPlugsReceiver;

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

        hpPlugsReceiver = new HpPlugReceiver();
    }

    public static void increaseHpPlugsCounter() {
        tvHps.setText("" + (Integer.parseInt(tvHps.getText() + "") + 1));
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter hpPlugsFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(hpPlugsReceiver, hpPlugsFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(hpPlugsReceiver);
    }
}