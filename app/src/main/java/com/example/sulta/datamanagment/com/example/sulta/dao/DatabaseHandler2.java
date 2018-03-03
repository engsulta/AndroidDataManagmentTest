package com.example.sulta.datamanagment.com.example.sulta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sulta.datamanagment.com.example.sulta.beans.Note;

import java.util.ArrayList;

public class DatabaseHandler2 extends SQLiteOpenHelper
{

    private static final int databaseVersion = 1;

    // Database Name
    private static final String databaseName = "NoteDB";

    // Notes table name
    private static final String tableNotes = "Note";

    // Notes Table Columns names
    public static final String key_ID = "ID";
    public static final String key_Note = "Note";
    public static final String key_Time = "Time";
    public static final String key_Alarm = "Alarm";

    public DatabaseHandler2(Context context)
    {

        super(context, databaseName, null, databaseVersion);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + tableNotes + "("
                + key_ID 		+ "  INTEGER ,"
                + key_Note 		+ " TEXT ,"
                + key_Time 	+ " TEXT  ,"
                + key_Alarm 	+ " TEXT )"
                ;
        String CREATE_INDEX_TABLE=
               " CREATE UNIQUE INDEX "+"indexname" +"ON"+tableNotes+
            "("+key_ID+"ASC)";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableNotes);
        // Create tables again
        onCreate(db);
    }

    public int getLastId(){
        int id=0;
        String selectQuery = "SELECT  nvl(max("+key_ID+"),0) FROM " + tableNotes;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            id=Integer.parseInt(cursor.getString(0));
        }
        return id;
    }

    public void addNote(Note note)
    {
        //note.setId(getLastId()+1);
        SQLiteDatabase mydb = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(key_ID,note.getId());
        values.put(key_Note, note.getNote());
        values.put(key_Time, note.getTime());
        values.put(key_Alarm, note.getAlarm());

        // Inserting Row
        mydb.insert(tableNotes, null, values);
        mydb.close(); // Closing database connection
    }

    public Note getNoteByID(int noteID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableNotes, new String[] {key_Note, key_Time ,key_Alarm}, key_ID+" = ?",new String[]{String.valueOf(noteID)}, null, null, null, null);

        Note note = null;

        if (cursor.moveToFirst())
        {
            note = new Note(noteID,cursor.getString(0), cursor.getString(1), cursor.getString(2));
        }

        db.close();
        return note;
    }
    public Note getNoteBynotestr(String notestr)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableNotes, new String[] {key_ID,key_Note, key_Time ,key_Alarm}, key_Note+" = ?",new String[]{String.valueOf(notestr)}, null, null, null, null);

        Note note = null;

        if (cursor.moveToFirst())
        {
            note = new Note(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
        }

        db.close();
        return note;
    }


    public ArrayList<Note> getAllNotes()
    {
        ArrayList<Note> NotesList = new ArrayList<Note>();
        String selectQuery = "SELECT  * FROM " + tableNotes;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            do
            {
                Note oneNote = new Note(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                NotesList.add(oneNote);
            }
            while (cursor.moveToNext());
        }

        db.close();
        // return note list
        return NotesList;
    }
    public int getNotesCount() {

        String countQuery = "SELECT  * FROM " + tableNotes;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
    public int deleteNoteByID(int noteID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int len=getNotesCount();
        int count = db.delete(tableNotes, key_ID + " = "+noteID,null);
        ContentValues values = new ContentValues();
        for (int i=noteID+1;i<len;i++){
            values.put(key_ID,String.valueOf(i-1));
            db.update(tableNotes,values,key_ID+"="+i,null);
        }
        db.close();
        return count;
    }

    public int deletAllNotes(){

        SQLiteDatabase db = this.getWritableDatabase();
        /*int len=getNotesCount();
        ContentValues values = new ContentValues();*/
        int count =db.delete(tableNotes,null,null);

        /* for (int i=0;i<len;i++){
            values.put(key_ID,String.valueOf(i-1));
            db.update(tableNotes,values,key_ID+"="+i,null);
        }*/

        return count;
    }

    public void updatNoteById( Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        int noteID=note.getId();
        ContentValues values = new ContentValues();
        values.put(key_Note, note.getNote());
        values.put(key_Time, note.getTime());
        values.put(key_Alarm, note.getAlarm());
        db.update(tableNotes,values,key_ID+"="+noteID,null);
        db.close();

    }
}
