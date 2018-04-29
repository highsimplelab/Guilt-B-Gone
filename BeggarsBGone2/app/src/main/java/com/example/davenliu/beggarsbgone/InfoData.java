package com.example.davenliu.beggarsbgone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davenliu on 2018/4/28.
 */


public class InfoData extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "people.db";
    public static final String TABLE_NAME ="people_table";
    public static final String COL1 ="NAME";
    public static final String COL2 ="ADDRESS";
    public static final String COL3 ="CLOTHES";
    public static final String COL4 ="WATER";
    public static final String COL5 ="TOILETRIES";
    public static final String COL6 ="SHOES";
    public static final String COL7 ="FOOD";


    public InfoData(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE  " + TABLE_NAME + "( " + COL1 + " TEXT NOT NULL, " + COL2  + " TEXT NOT NULL," + COL3 + " INTEGER," + COL4 + " INTEGER," + COL5 + " INTEGER," + COL6 + " IINTEGER," + COL7 + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder().append("DROP IF TABLE EXISTS ").append(TABLE_NAME).toString());
        onCreate(db);
    }

    public boolean addData(String name, String address, int clothes, int water, int toiletries, int shoes, int food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,name);
        contentValues.put(COL2,address);
        contentValues.put(COL3,clothes);
        contentValues.put(COL4,water);
        contentValues.put(COL5,toiletries);
        contentValues.put(COL6,shoes);
        contentValues.put(COL7,food);
        
        long result =db.insert(TABLE_NAME, null , contentValues);

        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Cursor showData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
