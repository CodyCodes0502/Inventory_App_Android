package com.zybooks.cs_360_project.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zybooks.cs_360_project.repo.DatabaseHelper;

public class Login {

    private final DatabaseHelper dbHelper;

    public Login(Context context) {
        dbHelper =  DatabaseHelper.getInstance(context);
    }
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USERS + " WHERE " +
                DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] args = {username, password};
        Cursor cursor = db.rawQuery(query, args);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USERS + " WHERE " +
                DatabaseHelper.COLUMN_USERNAME + " = ?";
        String[] args = {username};
        Cursor cursor = db.rawQuery(query, args);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }



}
