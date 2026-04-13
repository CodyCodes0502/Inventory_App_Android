package com.zybooks.cs_360_project.repo;

import android.content.Context;

import com.zybooks.cs_360_project.model.Inventory;
import com.zybooks.cs_360_project.model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InventoryRepository {
    private static InventoryRepository mInvRepo;
    private final List<Inventory> mInventoryList;
    private final HashMap<Long, List<Item>> mItemListMap;

    public static InventoryRepository getInstance(Context context) {
        if (mInvRepo == null) {
            mInvRepo = new InventoryRepository();
        }
        return mInvRepo;
    }

    private InventoryRepository() {
        mInventoryList = new ArrayList<>();
        mItemListMap = new HashMap<>();

        addStarterData();
    }

    private void addStarterData() {
        Inventory inventory = new Inventory("Inventory 1");
        inventory.setId(1);
        addInventory(inventory);

        Item item = new Item("Example Item", 2);
        item.setInventoryId(1);
        addItem(item);
    }

    public void addInventory(Inventory inventory) {
        if (inventory.getId() == 0) {
            inventory.setId(mInventoryList.size() + 1);
        }
        mInventoryList.add(inventory);
        mItemListMap.put(inventory.getId(), new ArrayList<>());
    }

    public Inventory getInventory(long id) {
        for (Inventory inventory : mInventoryList) {
            if (inventory.getId() == id) {
                return inventory;
            }
        }
        return null;
    }

    public List<Inventory> getInventoryList() {
        return mInventoryList;
    }

    public void addItem(Item item) {
        List<Item> itemList = mItemListMap.get(item.getInventoryId());
        if(itemList != null) {
         itemList.add(item);
        }
    }

    public List<Item> getItemList(long inventoryId) {
        return mItemListMap.get(inventoryId);
    }

}
