package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context,String dbname) {

        super(context, dbname+".db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key , contact TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Userdetails");
    }

    public boolean insertUserData(String name, String contact, String dob){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        long result = db.insert("Userdetails", null, contentValues);
        return result != -1;
    }

    public boolean updateUserData(String name, String contact, String dob){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("dob", dob);
        //cursor is selecting the row. what ever is selected is loaded in the cursor
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ? ", new String[]{name});
        //check if data is in database have some data
        if (cursor.getCount() > 0){
            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            return result != -1;
        }
        cursor.close();
        return false;
    }

    public boolean deleteUserData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        //cursor is selecting the row. what ever is selected is loaded in the cursor
        Cursor cursor = db.rawQuery("Select * from Userdetails where name = ? ", new String[]{name});
        //check if tata is in database have some data
        if (cursor.getCount() > 0){
            long result = db.delete("Userdetails", "name=?", new String[]{name});
            return result != -1;
        }
        cursor.close();
        return false;
    }

    public Cursor getUserData(){
        SQLiteDatabase db = this.getWritableDatabase();
        //cursor is selecting the row. what ever is selected is loaded in the cursor
        return db.rawQuery("Select * from Userdetails ", null);
    }
}
