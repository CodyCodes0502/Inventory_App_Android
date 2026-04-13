package com.zybooks.cs_360_project.model;

import androidx.annotation.NonNull;

public class Inventory {
    private long id;
    private String name;
    private long updateTime;

    public Inventory(@NonNull String name) {
        this.name = name;
        updateTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateTime = System.currentTimeMillis();
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }


}
