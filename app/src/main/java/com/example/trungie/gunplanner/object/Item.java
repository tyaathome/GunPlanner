package com.example.trungie.gunplanner.object;

public class Item {
    protected String name;
    protected boolean isCompleted;

//    public Item() {
//        this.name = null;
//        this.isCompleted = false;
//    }

    public Item(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public Item(String name, boolean isCompleted) {
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
