package com.example.trungie.gunplanner.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.trungie.gunplanner.R;

public class LoadingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        gotoNextActivity();
    }

    private void gotoNextActivity() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        thread.start();
    }
}
