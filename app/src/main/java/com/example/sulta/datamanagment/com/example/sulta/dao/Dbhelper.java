package com.example.sulta.datamanagment.com.example.sulta.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by sulta on 3/2/2018.
 */

public class Dbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MYDATABASE";

    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME="MYUSER";
    private static final String  ID="USERID";


    private static final String DATABASE_CREATE = "create table "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY ,PHONENO text not null,MESSAGE text not null);";


    public Dbhelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(Dbhelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS MyEmployees");
        onCreate(sqLiteDatabase);

    }
}
