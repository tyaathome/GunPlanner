package com.example.trungie.gunplanner.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.Project;

public class BackLogActivity extends BaseActivity{

    private EditText etNote = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_backlog);
        setActivityTitle(R.string.backlog);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        etNote = (EditText) findViewById(R.id.et_note);
    }

    private void initEvent() {
        super.setBackButtonEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = getIntent().getIntExtra("index", -1);
                if (index != -1) {
                    DataBase db = MyApplication.getDB();
                    Project project = db.projectList.get(index);
                    project.backLog = etNote.getText().toString();
                    db.updateProject(project);
                    MyApplication.saveDB(db);
                }
                finish();
            }
        });
    }

    private void initData() {
        setNote();
    }

    private void setNote() {
        int index = getIntent().getIntExtra("index", -1);
        if (index != -1) {
            DataBase db = MyApplication.getDB();
            Project project = db.projectList.get(index);
            etNote.setText(project.backLog);
        }
    }
}
