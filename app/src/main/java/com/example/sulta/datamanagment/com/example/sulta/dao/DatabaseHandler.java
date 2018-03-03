package com.example.sulta.datamanagment.com.example.sulta.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sulta.datamanagment.com.example.sulta.beans.Employee;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper
{
    // Database Name
    private static final int databaseVersion = 1;

    // Database Name
    private static final String databaseName = "CompanyDB";

    //  table name
    private static final String tableEmployees = "Employee";

    //  Table Columns names
    public static final String key_ID = "ID";
    public static final String key_Name = "Name";
    public static final String key_Phone = "Phone";
    public static final String key_Salary = "Salary";

    public DatabaseHandler(Context context)
    {
        super(context, databaseName, null, databaseVersion);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + tableEmployees + "("
                + key_ID 		+ " INTEGER PRIMARY KEY autoincrement,"
                + key_Name 		+ " TEXT not null,"
                + key_Phone 	+ " TEXT ,"
                + key_Salary 	+ " INTEGER )"
                ;
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableEmployees);
        // Create tables again
        onCreate(db);
    }
    public int getLastId(){
        int id=0;
        String selectQuery = "SELECT  nvl(max("+key_ID+"),0) FROM " + tableEmployees;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst())
        {
            id=Integer.parseInt(cursor.getString(0));
        }
    return id;
    }
    public void addEmployee(Employee employee)
    {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(key_Name, employee.getName());
        values.put(key_Phone, employee.getPhone());
        values.put(key_Salary, employee.getSalary());

        // Inserting Row
        mydb.insert(tableEmployees, null, values);
        mydb.close(); // Closing database connection
    }

    public Employee getEmployeeByID(int employeeID)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(tableEmployees, new String[] {key_Name, key_Phone ,key_Salary}, key_ID+" = ?",new String[]{String.valueOf(employeeID)}, null, null, null, null);

        Employee employee = null;

        if (cursor.moveToFirst())
        {
            employee = new Employee(employeeID,cursor.getString(0), cursor.getString(1), cursor.getInt(2));
        }

        db.close();
        return employee;
    }



    public ArrayList<Employee> getAllEmployees()
    {
        ArrayList<Employee> employeesList = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tableEmployees;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst())
        {
            do
            {
                Employee oneEmployee = new Employee(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
                // Adding contact to list
                employeesList.add(oneEmployee);
            }
            while (cursor.moveToNext());
        }

        db.close();
        // return contact list
        return employeesList;
    }

    public int getEmployeesCount()
    {
        String countQuery = "SELECT  * FROM " + tableEmployees;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        db.close();
        // return count
        return cursor.getCount();
    }

    public int deleteEmployeeByID(int employeeID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int count = db.delete(tableEmployees, key_ID + " = "+employeeID,null);
        db.close();
        return count;
    }

}
