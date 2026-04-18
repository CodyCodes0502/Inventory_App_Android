package com.zybooks.cs_360_project.model;

/**
 * Item.java
 * Represents an item in an inventory.
 */
public class Item {
    private long id;
    private String name;
    private long quantity;
    private long inventoryId;
    private long createdTime;
    private long updateTime;


    /**
     * Create a new item with the given name and quantity.
     * @param name - the name of the item.
     * @param quantity - the quantity of the item.
     */

    public Item(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    /**
     * Create a new item with the given ID, name, quantity, and inventory ID.
     * @param id - the ID of the item.
     * @param name - the name of the item.
     * @param quantity - the quantity of the item.
     * @param inventoryId - the ID of the inventory the item belongs to.
     */

    public Item(long id, String name, long quantity, long inventoryId) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.updateTime = System.currentTimeMillis();
        this.createdTime = System.currentTimeMillis();
    }

    /**
     * Create a new item with the given ID, name, quantity, inventory ID, and creation and update times.
     * @param id - the ID of the item.
     * @param name - the name of the item.
     * @param quantity - the quantity of the item.
     * @param inventoryId - the ID of the inventory the item belongs to.
     * @param createdTime - the creation time of the item.
     * @param updateTime - the last update time of the item.
     */

    public Item(long id, String name, long quantity, long inventoryId, long createdTime, long updateTime) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.inventoryId = inventoryId;
        this.createdTime = createdTime;
        this.updateTime = updateTime;
    }

    /**
     * Set the ID of the item.
     * @param id - the ID of the item.
     */

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the ID of the item.
     * @return - the ID of the item.
     */


    public long getId() {
        return id;
    }

    /**
     * Set the name of the item.
     * @param name - the name of the item.
     */


    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the item.
     * @return - the name of the item.
     */

    public String getName() {
        return name;
    }

    /**
     * Set the quantity of the item.
     * @param quantity - the quantity of the item.
     */


    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    /**
     * Get the quantity of the item.
     * @return - the quantity of the item.
     */


    public long getQuantity() {
        return quantity;
    }

    /**
     * Set the ID of the inventory the item belongs to.
     * @param inventoryId - the ID of the inventory the item belongs to.
     */

    public void setInventoryId(long inventoryId) {
        this.inventoryId = inventoryId;
    }

    /**
     * Get the ID of the inventory the item belongs to.
     * @return - the ID of the inventory the item belongs to.
     */


    public long getInventoryId() {
        return inventoryId;
    }

    /**
     * Get the creation time of the item.
     * @return - the creation time of the item.
     */
    public long getCreatedTime() {
        return createdTime;
    }

    /**
     * Get the last update time of the item.
     * @return - the last update time of the item.
     */
    public long getUpdateTime(){
        return updateTime;
    }


}
