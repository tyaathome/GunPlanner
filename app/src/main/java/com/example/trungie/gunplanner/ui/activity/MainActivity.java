package com.example.trungie.gunplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.ui.myview.MyProgressAnimation;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button btnPlanner = null;
    private Button btnExit = null;
    private MyProgressAnimation progress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        finish();
    }

    private void initView() {
        btnPlanner = (Button) findViewById(R.id.btn_planner);
        btnExit = (Button) findViewById(R.id.btn_exit);
        progress = (MyProgressAnimation) findViewById(R.id.myprogressanomation);
        progress.setMaxProgress(100);
        progress.setCurrentProgress(70);
        super.setHeadVisibility(View.GONE);
    }

    private void initEvent() {
        btnPlanner.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    private void gotoNextActivity() {
        Intent intent = new Intent(this, PlanerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_planner:
                gotoNextActivity();
                break;
            case R.id.btn_exit:
                finish();
                break;
        }
    }
}
