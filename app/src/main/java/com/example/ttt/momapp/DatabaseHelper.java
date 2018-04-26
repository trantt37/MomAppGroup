package com.example.ttt.momapp;

/**
 * Created by Bharat on 4/4/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

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
    public String getRow(int ID){
        db= this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = 0;

        String a = "";
        if(cursor.moveToFirst()){
            do{

                a = cursor.getString(1);
                if((Integer.parseInt(a))==ID){

                    return count + "";
                }
                count++;

            }
            while (cursor.moveToNext());

        }

        return "";
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
    public int getID(String name){
        db= this.getReadableDatabase();
        String query = "select * from "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int id = 0;
        String a = "";
        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(0).equals(name))
                    id = cursor.getInt(1);

            }
            while (cursor.moveToNext());

        }
        return id;

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
        m.setId(idVal);
        idVal++;
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public boolean deleteItem(int id)
    {
        db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID + "=" + 1, null);
        db.close();
        return true;

    }
    public void deleteRow(String value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COLUMN_NAME+"="+"d");
        db.close();
    }
    public Integer deleteName(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "name = ?",new String[] {name});

    }


    public int getItemID(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ID + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())
            return data.getInt(1);
        return -1;
    }
    public int getItemCount(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_COUNT + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst()) {

            return data.getInt(2);

        }
        return -1;

    }
    public String getItemLocation(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_LOCATION + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())

            return data.getString(3);
        return "";

    }
    public double getItemPrice(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_PRICE + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())

            return data.getDouble(4);
        return -1;

    }
    public String getItemCategory(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_CATEGORY + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())

            return data.getString(5);
        return "";

    }
    public String getItemExpiration(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_EXPIRATION + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())

            return data.getString(6);
        return "";

    }
    public String getItemMisc(String name) {
        db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_MISC + " FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + name + "";
        Cursor data = db.rawQuery(query, null);
        if(data.moveToFirst())

            return data.getString(7);
        return "";

    }
    public void updateName(String newName, String oldName){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_NAME
                + " = " + newName + " WHERE " + COLUMN_NAME + " = " + oldName + "";
        db.execSQL(query);
    }

    public void updateCount(int count, String name){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_COUNT
                + " = " + count + " WHERE " + COLUMN_NAME + " = " + name + "";
        db.execSQL(query);
    }

    public void updatePrice(double price, String name){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_PRICE
                + " = " + price + " WHERE " + COLUMN_NAME + " = " + name + "";
        db.execSQL(query);
    }

    public void updateMisc(String newMisc, String name){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_MISC
                + " = " + newMisc + " WHERE " + COLUMN_NAME + " = " + name + "";
        db.execSQL(query);
    }

    public void updateExpiration(String exp, String name){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_EXPIRATION
                + " = " + exp + " WHERE " + COLUMN_NAME + " = " + name + "";
        db.execSQL(query);
    }

    public void updateLocation(String location, String name){
        db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COLUMN_LOCATION
                + " = " + location + " WHERE " + COLUMN_NAME + " = " + name + "";
        db.execSQL(query);
    }
    public boolean updateItem(Item m){
        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_NAME, m.getName());
//        values.put(COLUMN_COUNT, m.getItemCount());
//        values.put(COLUMN_PRICE, m.getPrice());
//        values.put(COLUMN_MISC, m.getMisc());
//        values.put(COLUMN_CATEGORY, m.getCategory());
//        values.put(COLUMN_EXPIRATION, m.getExpiration());
//        values.put(COLUMN_LOCATION, m.getLocation());
//        db.update(TABLE_NAME, values, "ID = ?", new String[] { "Item" });
//        updateName(m.getName(),getID(m.getName()));
//        updateCount(m.getItemCount(),getID(m.getName()));
//        updatePrice(m.getPrice(),getID(m.getName()));
//        updateMisc(m.getMisc(),getID(m.getName()));
//        updateExpiration(m.getExpiration(),getID(m.getName()));
//        updateLocation(m.getLocation(),getID(m.getName()));


        return true;
    }

}

