package com.example.ttt.momapp;

/**
 * Created by Bharat on 4/4/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Bharat on 3/21/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "items.db";
    private static final String TABLE_NAME = "matches";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COUNT = "count";
    private static final String COLUMN_LOCATION = "location";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_EXPIRATION = "expiration";
    private static final String COLUMN_MISC = "misc";
    private static final String COLUMN_ID = "id";
    private static int idVal = 0;

    private static final String TABLE_CREATE = "create table matches (name text primary key not null , " + "id text not null, count text not null , location text not null , price text not null , category text not null ,expiration text not null ,misc text not null );";
    SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS "+DATABASE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
    public String[] getItem(int ID){
        db= this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<String> array = new ArrayList<String>();

        String a = "";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(1);
                if((Integer.parseInt(a))==ID){
                    for(int x = 0; x<7; x++){
                        array.add(cursor.getString(x));
                    }
                    break;
                }

            }
            while (cursor.moveToNext());

        }
        return array.toArray(new String[0]);

    }
    public String[] getNames(){
        db= this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<String> list = new ArrayList<String>();
        String a = "";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);
                list.add(a);

            }
            while (cursor.moveToNext());

        }
        return list.toArray(new String[0]);

    }
    public void insertItem(Item m){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, m.getName());
        values.put(COLUMN_COUNT, m.getItemCount());
        values.put(COLUMN_PRICE, m.getPrice());
        values.put(COLUMN_MISC, m.getMisc());
        values.put(COLUMN_CATEGORY, m.getCategory());
        values.put(COLUMN_EXPIRATION, m.getExpiration());
        values.put(COLUMN_LOCATION, m.getLocation());
        values.put(COLUMN_ID, idVal);
        idVal++;
        db.insert(TABLE_NAME,null,values);
        db.close();

    }

    public boolean updateItem(Item m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, m.getName());
        values.put(COLUMN_COUNT, m.getItemCount());
        values.put(COLUMN_PRICE, m.getPrice());
        values.put(COLUMN_MISC, m.getMisc());
        values.put(COLUMN_CATEGORY, m.getCategory());
        values.put(COLUMN_EXPIRATION, m.getExpiration());
        values.put(COLUMN_LOCATION, m.getLocation());
        db.update(TABLE_NAME, values, "ID = ?", new String[] { "Item" });
        return true;
    }
}

