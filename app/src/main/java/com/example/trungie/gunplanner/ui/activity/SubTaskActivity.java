package com.example.trungie.gunplanner.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.Project;
import com.example.trungie.gunplanner.object.SubTask;
import com.example.trungie.gunplanner.object.Task;
import com.example.trungie.gunplanner.ui.adapter.AdapterSubTask;

import java.util.List;

public class SubTaskActivity extends BaseActivity{

    private ListView lv = null;
    private AdapterSubTask adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_task);
        setActivityTitle(R.string.subtasks);
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        lv = (ListView) findViewById(R.id.lv_task);
    }

    private void initEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                set(position);
            }
        });
    }

    private void initData() {
        int index = getIntent().getIntExtra("index", -1);
        int taskindex = getIntent().getIntExtra("taskindex", -1);
        if(index != -1 && taskindex != -1) {
            DataBase db = MyApplication.getDB();
            List<SubTask> subTasks = db.projectList.get(index).tasks.get(taskindex).subTasks;
            adapter = new AdapterSubTask(this, subTasks);
            lv.setAdapter(adapter);
        }
    }

    private void set(int position) {
        int index = getIntent().getIntExtra("index", -1);
        int taskindex = getIntent().getIntExtra("taskindex", -1);
        if(index != -1 && taskindex != -1) {
            DataBase db = MyApplication.getDB();
            boolean b = db.projectList.get(index).tasks.get(taskindex).subTasks.get(position).isCompleted;
            db.set(index, taskindex, position, !b);
            List<SubTask> subTasks = db.projectList.get(index).tasks.get(taskindex).subTasks;
            adapter.setData(subTasks);
            MyApplication.saveDB(db);
        }
    }
}
