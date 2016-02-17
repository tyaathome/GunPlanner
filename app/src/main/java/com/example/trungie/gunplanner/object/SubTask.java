package com.example.trungie.gunplanner.object;

import java.io.Serializable;

public class SubTask implements Serializable{

//    public SubTask() {
//
//    }

    public String name;
    public boolean isCompleted;

    public SubTask(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public SubTask(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
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
