package com.zybooks.cs_360_project.repo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UserManger.db";
    private static final int DATABASE_VERSION = 4;

    // Users table
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "ID";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";

    // Inventories table
    public static final String TABLE_INVENTORIES = "inventories";
    public static final String COLUMN_INV_ID = "id";
    public static final String COLUMN_INV_NAME = "name";
    public static final String COLUMN_INV_CREATED = "created_time";
    public static final String COLUMN_INV_UPDATED = "updated_time";

    // Items table
    public static final String TABLE_ITEMS = "items";
    public static final String COLUMN_ITEM_ID = "id";
    public static final String COLUMN_ITEM_NAME = "name";
    public static final String COLUMN_ITEM_QUANTITY = "quantity";
    public static final String COLUMN_ITEM_INV_ID = "inventory_id";
    public static final String COLUMN_ITEM_CREATED = "created_time";
    public static final String COLUMN_ITEM_UPDATED = "updated_time";


    private static DatabaseHelper mInstance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create users table
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT UNIQUE, " +
                COLUMN_PASSWORD + " TEXT)");

        // Create inventories table
        db.execSQL("CREATE TABLE " + TABLE_INVENTORIES + " (" +
                COLUMN_INV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_INV_NAME + " TEXT, " +
                COLUMN_INV_CREATED + " INTEGER, " +
                COLUMN_INV_UPDATED + " INTEGER)");

        // Create items table
        db.execSQL("CREATE TABLE " + TABLE_ITEMS + " (" +
                COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ITEM_NAME + " TEXT, " +
                COLUMN_ITEM_QUANTITY + " INTEGER, " +
                COLUMN_ITEM_INV_ID + " INTEGER, " +
                COLUMN_ITEM_CREATED + " INTEGER, " +
                COLUMN_ITEM_UPDATED + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_ITEM_INV_ID + ") REFERENCES " +
                TABLE_INVENTORIES + "(" + COLUMN_INV_ID + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }
}
