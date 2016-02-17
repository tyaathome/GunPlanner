package com.example.trungie.gunplanner.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.ui.adapter.AdapterProject;
import com.example.trungie.gunplanner.ui.myview.MyTwoButtonDialog;


public class PlanerActivity extends BaseActivity implements View.OnClickListener {

    private Button btnNewProject = null;

    private MyTwoButtonDialog dialog = null;
    private ListView lvProject = null;
    private AdapterProject adapterProject = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planner);
//        NoteItem n = new NoteItem();
//        n.setKey("key");
//        n.setText("text");
//        DiskLruUtils.saveObject("data", n);
//        NoteItem n2 = (NoteItem) DiskLruUtils.getObject("data");

        initView();
        initEvent();
        initData();
    }

    private void initView() {
        btnNewProject = (Button) findViewById(R.id.btn_new_project);
        lvProject = (ListView) findViewById(R.id.lvProject);
    }

    private void initEvent() {
        btnNewProject.setOnClickListener(this);
        lvProject.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                createDeleteProjectDialog(position);
                return false;
            }
        });
        lvProject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                gotoProjectActivity(position);
            }
        });
    }

    private void initData() {
        adapterProject = new AdapterProject(this, MyApplication.getDB());
        lvProject.setAdapter(adapterProject);
    }

    private void gotoProjectActivity(int index) {
        DataBase db = MyApplication.getDB();
        String title = db.projectList.get(index).getName();
        Intent intent = new Intent(this,ProjectActivity.class);
        intent.putExtra("index", index);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    private void createNewProjectDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_create_project, null);
        final EditText editText = (EditText) view.findViewById(R.id.et_name);
        dialog = new MyTwoButtonDialog(this, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.setPositiveInfo(getResources().getString(R.string.create), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db = MyApplication.getDB();
                db.createProject(editText.getText().toString());
                adapterProject.setData(db);
                MyApplication.saveDB(db);
                dialog.dismiss();
            }
        });
        dialog.setNegativeInfo(getResources().getString(R.string.cancel), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void createDeleteProjectDialog(final int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_delete_project, null);
        TextView tvProjectName = (TextView) view.findViewById(R.id.tv_project_name);
        tvProjectName.setText(MyApplication.getDB().projectList.get(position).getName());
        dialog = new MyTwoButtonDialog(PlanerActivity.this, R.style.CustomDialog);
        dialog.setContentView(view);
        dialog.setPositiveInfo(getResources().getString(R.string.yes), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase db = MyApplication.getDB();
                db.deleteProject(position);
                adapterProject.setData(db);
                MyApplication.saveDB(db);
                dialog.dismiss();
            }
        });
        dialog.setNegativeInfo(getResources().getString(R.string.no), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new_project:
                //gotoCreateProjectActivity();
                createNewProjectDialog();
                break;
        }
    }
}
