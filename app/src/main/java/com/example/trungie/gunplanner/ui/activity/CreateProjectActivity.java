package com.example.trungie.gunplanner.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.trungie.gunplanner.R;

public class CreateProjectActivity extends BaseActivity implements View.OnClickListener{

    private EditText etName = null;
    private EditText etModel = null;
    private Button btnSave = null;
    private Button btnCancel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        etModel = (EditText) findViewById(R.id.et_model);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
    }

    private void initEvent() {
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                break;
            case R.id.btn_cancel:
                finish();
                break;
        }
    }
}
