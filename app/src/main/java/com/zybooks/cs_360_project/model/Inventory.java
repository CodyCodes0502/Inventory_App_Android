package com.zybooks.cs_360_project.model;

import androidx.annotation.NonNull;

public class Inventory {
    private long id;
    private String name;
    private long createdTime;
    private long updateTime;

    public Inventory(@NonNull String name) {
        this.name = name;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    public Inventory(long id, String name, long createdTime, long updateTime) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
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

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }


    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }


}
