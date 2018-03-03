package com.example.sulta.datamanagment.com.example.sulta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sulta.datamanagment.com.example.sulta.beans.User;

/**
 * Created by sulta on 3/2/2018.
 */

public class DbHandler {
    private Dbhelper dbHelper;

    private SQLiteDatabase database;

    public final static String USER_TABLE = "Myuser"; // name of table

    public final static String USER_ID = "userid"; // id value for employee
    public final static String USER_PHONENO = "PHONENO";  // name of employee
    public final static String USER_MESSAGE = "MESSAGE";  // name of employee

    public DbHandler(Context context) {
        dbHelper = new Dbhelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public long insertRecords(String phone, String message) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID, getLastId() + 1);
        values.put(USER_PHONENO, phone);
        values.put(USER_MESSAGE, message);

        return database.insert(USER_TABLE, null, values);
    }

    public int getLastId() {
        int id = 0;
        String selectQuery = "SELECT  max(" + USER_ID + ") FROM " + USER_TABLE;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0) != null)
                id = Integer.parseInt(cursor.getString(0));
            else
                id = 0;
        }
        return id;
    }

    public User getlastRecord() {
        int id = getLastId();
        database = dbHelper.getReadableDatabase();
        User myuser = null;
        String[] cols = new String[]{USER_PHONENO, USER_MESSAGE};
        Cursor mCursor = database.query(true, USER_TABLE, cols, USER_ID + " = ?"
                , new String[]{String.valueOf(id)}, null, null, null, null);
        if (mCursor.moveToFirst()) {
            myuser = new User(mCursor.getString(0), mCursor.getString(1));
        }
        return myuser; // iterate to get each value.
    }


}
