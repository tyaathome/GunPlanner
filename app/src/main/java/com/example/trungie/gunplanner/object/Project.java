package com.example.trungie.gunplanner.object;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project implements Serializable{
    public Calendar startData;
    public Calendar finishData;
    public String backLog;
    public List<Task> tasks;
    private String projectName;

    public Project() {

    }

    public Project(String name) {
        this.startData = Calendar.getInstance();
        this.backLog = "";
        this.projectName = name;
        initTasks();
    }

    private void initTasks() {
        tasks = new ArrayList<Task>();
        String[] arrayTask = MyApplication.getContext().getResources().getStringArray(R.array.tasks);
        for(String name : arrayTask) {
            Task task = new Task(name);
            tasks.add(task);
        }
    }

    public void set(Task task) {
        for(Task t : tasks) {
            if(t.getName().equals(task.getName())) {
                int index = tasks.indexOf(t);
                tasks.set(index, task);
                break;
            }
        }
    }

    public boolean isAllCompleted(String name) {
        for(Task task : tasks) {
            if(task.name.equals(name)) {
                int index = tasks.indexOf(task);
                if (index == -1) {
                    return false;
                }
                for (SubTask subTask : task.subTasks) {
                    if (subTask.isCompleted == false) {
                        return false;
                    }
                }
                this.finishData = Calendar.getInstance();
                return true;
            }
        }
        return false;
    }

    public boolean isAllCompleted(int index) {
        if(index < 0 || index >= tasks.size()) {
            return false;
        }
        Task task = tasks.get(index);
        for (SubTask subTask : task.subTasks) {
            if (subTask.isCompleted == false) {
                return false;
            }
        }
        this.finishData = Calendar.getInstance();
        return true;
    }

    public boolean isAllTaskCompleted() {
        for(Task task : this.tasks) {
            for(SubTask subTask : task.subTasks) {
                if(subTask.isCompleted == false) {
                    this.finishData = null;
                    return false;
                }
            }
        }
        this.finishData = Calendar.getInstance();
        return true;
    }

    public String getName() {
        return this.projectName;
    }

}
