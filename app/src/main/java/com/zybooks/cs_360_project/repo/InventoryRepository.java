package com.zybooks.cs_360_project.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zybooks.cs_360_project.model.Inventory;
import com.zybooks.cs_360_project.model.Item;

import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {
    private static InventoryRepository mInvRepo;
    private final DatabaseHelper dbHelper;

    public static InventoryRepository getInstance(Context context) {
        if (mInvRepo == null) {
            mInvRepo = new InventoryRepository(context);
        }
        return mInvRepo;
    }

    private InventoryRepository(Context context) {
        dbHelper = DatabaseHelper.getInstance(context);
    }

    public void addInventory(Inventory inventory) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_INV_NAME, inventory.getName());
        values.put(DatabaseHelper.COLUMN_INV_CREATED, inventory.getCreatedTime());
        values.put(DatabaseHelper.COLUMN_INV_UPDATED, inventory.getUpdateTime());
        long id = db.insert(DatabaseHelper.TABLE_INVENTORIES, null, values);
        inventory.setId(id);
    }

    public List<Inventory> getInventoryList() {
        List<Inventory> inventories = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_INVENTORIES, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_NAME));
                long created = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_CREATED));
                long updated = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_UPDATED));
                inventories.add(new Inventory(id, name, created, updated));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return inventories;
    }

    public Inventory getInventory(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_INVENTORIES, null,
                DatabaseHelper.COLUMN_INV_ID + "=?", new String[]{String.valueOf(id)},
                null, null, null);

        Inventory inventory = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_NAME));
            long created = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_CREATED));
            long updated = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_INV_UPDATED));
            inventory = new Inventory(id, name, created, updated);
        }
        cursor.close();
        return inventory;
    }

    public void addItem(Item item) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ITEM_NAME, item.getName());
        values.put(DatabaseHelper.COLUMN_ITEM_QUANTITY, item.getQuantity());
        values.put(DatabaseHelper.COLUMN_ITEM_INV_ID, item.getInventoryId());
        long id = db.insert(DatabaseHelper.TABLE_ITEMS, null, values);
        item.setId(id);
    }

    /**
     * Get the list of items associated with a specific inventory.
     * @param inventoryId - the inventory ID for which to get the items.
     * @return - a list of items associated with the inventory.
     */

    public List<Item> getItemList(long inventoryId) {
        List<Item> items = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_ITEMS, null,
                DatabaseHelper.COLUMN_ITEM_INV_ID + "=?", new String[]{String.valueOf(inventoryId)},
                null, null, null);

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_NAME));
                long quantity = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ITEM_QUANTITY));
                Item item = new Item(name, quantity);
                item.setId(id);
                item.setInventoryId(inventoryId);
                items.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return items;
    }

    /**
     * Delete an inventory and its associated items.
     * @param inventoryId - the inventory ID to delete.
     */

    public void deleteInventory(long inventoryId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Delete items associated with the inventory
        db.delete(DatabaseHelper.TABLE_ITEMS, DatabaseHelper.COLUMN_ITEM_INV_ID + "=?", new String[]{String.valueOf(inventoryId)});

        // Delete the inventory
        db.delete(DatabaseHelper.TABLE_INVENTORIES, DatabaseHelper.COLUMN_INV_ID + "=?", new String[]{String.valueOf(inventoryId)});

    }
}
