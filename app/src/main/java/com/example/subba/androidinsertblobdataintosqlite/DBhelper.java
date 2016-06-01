package com.example.subba.androidinsertblobdataintosqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 5;

    private static final String DATABASE_NAME = "imagedb";

    private static final String TABLE_CONTACTS = "employee";

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_IMAGE = "image";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_AGE + " INTEGER,"
                + KEY_IMAGE + " BLOB" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

// Create tables again
        onCreate(db);
    }

    public void addEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employee._name);
        values.put(KEY_AGE, employee._age);
        values.put(KEY_IMAGE, employee._image);

// Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<Employee> getEmployeeList() {
        List<Employee> contactList = new ArrayList<Employee>();
// Select All Query
        String selectQuery = "SELECT * FROM employee ORDER BY name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Employee emp = new Employee();
                emp.setID(Integer.parseInt(cursor.getString(0)));
                emp.setName(cursor.getString(1));
                emp.setAge(cursor.getInt(2));
                emp.setImage(cursor.getBlob(3));
// Adding contact to list
                contactList.add(emp);
            } while (cursor.moveToNext());
        }
// close inserting data from database
        db.close();
// return contact list
        return contactList;

    }
}