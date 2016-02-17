package com.example.trungie.gunplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.Project;
import com.example.trungie.gunplanner.object.SubTask;
import com.example.trungie.gunplanner.object.Task;
import com.example.trungie.gunplanner.ui.myview.MyProgressAnimation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ProjectActivity extends BaseActivity implements View.OnClickListener{

    private TextView tvStartData = null;
    private TextView tvFinishData = null;
    private MyProgressAnimation myProgress = null;
    private TextView tvBackLog = null;
    private TextView tvTasks = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_project);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        initView();
        initEvent();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setData();
        setProgress();
    }

    private void initView() {
        tvStartData = (TextView) findViewById(R.id.tv_start_data);
        tvFinishData = (TextView) findViewById(R.id.tv_finish_data);
        myProgress = (MyProgressAnimation) findViewById(R.id.myprogress);
        tvBackLog = (TextView) findViewById(R.id.tv_backlog);
        tvTasks = (TextView) findViewById(R.id.tv_tasks);
    }

    private void initEvent() {
        tvBackLog.setOnClickListener(this);
        tvTasks.setOnClickListener(this);
    }

    private void initData() {
        setData();
        setProgress();
    }

    private void setData() {
        int index = getIntent().getIntExtra("index", -1);
        if (index != -1) {
            DataBase db = MyApplication.getDB();
            Project project = db.projectList.get(index);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
            Date startDate = new Date(project.startData.getTimeInMillis());
            String strStart = getResources().getString(R.string.start);
            strStart += format.format(startDate);
            tvStartData.setText(strStart);
            if (project.finishData != null) {
                if(project.isAllTaskCompleted()) {
                    Date finishDate = new Date(project.finishData.getTimeInMillis());
                    String strFinish = getResources().getString(R.string.finish);
                    strFinish += format.format(finishDate);
                    tvFinishData.setText(strFinish);
                } else {
                    tvFinishData.setText(getResources().getString(R.string.finish));
                }
            }

        }
    }

    private void setProgress() {
        int index = getIntent().getIntExtra("index", -1);
        if (index != -1) {
            DataBase db = MyApplication.getDB();
            Project project = db.projectList.get(index);
            int count = 0;
            int total = 0;
            for (Task task : project.tasks) {
                for (SubTask subTask : task.subTasks) {
                    total++;
                    if (subTask.getIsCompleted()) {
                        count++;
                    }
                }
            }
            if (count != 0) {
                myProgress.setMaxProgress(total);
                myProgress.setCurrentProgress(count);
            }
        }
    }

    private void gotoBackLogActivity() {
        Intent intent = new Intent(this, BackLogActivity.class);
        intent.putExtra("index", getIntent().getIntExtra("index", -1));
        startActivity(intent);
    }

    private void gotoTaskActivity() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra("index", getIntent().getIntExtra("index", -1));
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tv_backlog:
                gotoBackLogActivity();
                break;
            case R.id.tv_tasks:
                gotoTaskActivity();
                break;
        }
    }
}
