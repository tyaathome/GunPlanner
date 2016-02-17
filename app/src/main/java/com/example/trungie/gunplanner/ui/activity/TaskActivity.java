package com.example.trungie.gunplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.Project;
import com.example.trungie.gunplanner.object.Task;
import com.example.trungie.gunplanner.ui.adapter.AdapterTask;

public class TaskActivity extends BaseActivity {

    private ListView lvTask = null;
    private AdapterTask adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_task);
        setActivityTitle(R.string.tasks);
        initView();
        initEvent();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }

    private void initView() {
        lvTask = (ListView) findViewById(R.id.lv_task);
    }

    private void initEvent() {
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoSubTaskActivity(position);
            }
        });
    }

    private void initData() {
        int index = getIntent().getIntExtra("index", -1);
        if(index != -1) {
            DataBase db = MyApplication.getDB();
            adapter = new AdapterTask(this, db.projectList.get(index));
            lvTask.setAdapter(adapter);
        }
    }

    private void update() {
        int index = getIntent().getIntExtra("index", -1);
        if(index != -1) {
            DataBase db = MyApplication.getDB();
            Project project = db.projectList.get(index);
            for (Task task : project.tasks) {
                int i = project.tasks.indexOf(task);
                db.set(index, i, project.isAllCompleted(i));
            }
            adapter.setData(db.projectList.get(index));
            MyApplication.saveDB(db);
        }
    }

    private void gotoSubTaskActivity(int position) {
        Intent intent = new Intent(TaskActivity.this, SubTaskActivity.class);
        intent.putExtra("index", getIntent().getIntExtra("index", -1));
        intent.putExtra("taskindex", position);
        startActivity(intent);
    }
}
