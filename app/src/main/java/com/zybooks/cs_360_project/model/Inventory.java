package com.zybooks.cs_360_project.model;

import androidx.annotation.NonNull;

public class Inventory {
    private long id;
    private String name;
    private long createdTime;
    private long updateTime;


    /**
     * Create a new inventory with the given name.
     * @param name - the name of the inventory.
     */
    public Inventory(@NonNull String name) {
        this.name = name;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    /**
     * Create a new inventory with the given ID, name, and creation time.
     * @param id - the ID of the inventory.
     * @param name - the name of the inventory.
     * @param createdTime - the creation time of the inventory.
     * @param updateTime - the last update time of the inventory.
     */

    public Inventory(long id, String name, long createdTime, long updateTime) {
        this.id = id;
        this.name = name;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    /**
     * Get the ID of the inventory.
     * @return - the ID of the inventory.
     */

    public long getId() {
        return id;
    }

    /**
     * Set the ID of the inventory.
     * @param id - the ID of the inventory.
     */


    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the name of the inventory.
     * @return - the name of the inventory.
     */


    public String getName() {
        return name;
    }

    /**
     * Set the name of the inventory.
     * @param name - the name of the inventory.
     */

    public void setName(String name) {
        this.name = name;
        updateTime = System.currentTimeMillis();
    }

    /**
     * Get the creation time of the inventory.
     * @return - the creation time of the inventory.
     */


    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * Get the last update time of the inventory.
     * @return - the last update time of the inventory.
     */

    public long getUpdateTime() {
        return updateTime;
    }



}
