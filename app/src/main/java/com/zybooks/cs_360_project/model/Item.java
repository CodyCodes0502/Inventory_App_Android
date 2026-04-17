package com.zybooks.cs_360_project.model;

public class Item {
    private long id;
    private String name;
    private long quantity;
    private long inventoryId;

    public Item(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item(long id, String name, long quantity, long inventoryId) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
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

}
