package com.zybooks.cs_360_project.model;

public class Item {
    private long id;
    private String name;
    private long quantity;
    private long inventoryId;
    private long createdTime;
    private long updateTime;


    public Item(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    public Item(long id, String name, long quantity, long inventoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    public Item(long id, String name, long quantity, long inventoryId, long createdTime, long updateTime) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public long getInventoryId() {
        return inventoryId;
    }
    public long getCreatedTime() {
        return createdTime;
    }
    public long getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(){
        this.updateTime = System.currentTimeMillis();
    }
    public void setUpdateTime(long updateTime){
        this.updateTime = updateTime;
    }
    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

}
