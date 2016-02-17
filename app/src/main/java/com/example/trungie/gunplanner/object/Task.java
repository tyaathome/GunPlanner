package com.example.trungie.gunplanner.object;

import android.content.Context;

import com.example.trungie.gunplanner.MyApplication;
import com.example.trungie.gunplanner.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable{

    public List<SubTask> subTasks;

    public String name;
    public boolean isCompleted;

//    public Task() {
//
//    }

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
        initSubTasks();
    }

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
        initSubTasks();
    }

    private void initSubTasks() {
        subTasks = new ArrayList<SubTask>();
        String[] arraySubTask = MyApplication.getContext().getResources().getStringArray(R.array.subtasks);
        for(String name : arraySubTask) {
            SubTask subTask = new SubTask(name);
            subTasks.add(subTask);
        }
    }

    public void set(String name, boolean isCompleted) {
        for(SubTask subTask : subTasks) {
            if(subTask.name.equals(name)) {
                int index = subTasks.indexOf(subTask);
                if(index == -1) {
                    return;
                }
                subTask.isCompleted = isCompleted;
                subTasks.set(index, subTask);
                break;
            }
        }
    }

    public void set(int index, boolean isCompleted) {
        if(index < 0 || index >= subTasks.size()) {
            return ;
        }
        SubTask subTask = subTasks.get(index);
        subTask.setIsCompleted(isCompleted);
        subTasks.set(index, subTask);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return name;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
