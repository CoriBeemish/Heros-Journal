package com.example.Beemish.HerosJournal.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StatsDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "StatsDatabase";
    public static final String TABLE_NAME = "stats";
    public static final String COLUMN_NAME = "statname";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_VALUE = "value";

    public static final int DB_VERSION = 1;

    public StatsDBHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME+ " (" +COLUMN_VALUE + " INTEGER, " +COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " VARCHAR)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String sql = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addStat(String name, int value)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_VALUE, value);

        if(db.insert(TABLE_NAME,null,contentValues) == -1)
            return false;
        return true;
    }

    public Cursor getStat(String name)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT " + COLUMN_VALUE+ " FROM "+TABLE_NAME +" WHERE " +COLUMN_NAME+ " LIKE '"+ name +"'"+";";
        return db.rawQuery(sql,null);
    }

    public boolean updateValue(int currentvalue, int newvalue, int id) {
        SQLiteDatabase db = getReadableDatabase();
        // String sql = "UPDATE "+TABLE_NAME + "SET " +COLUMN_VALUE+ " = " + String.valueOf(currentvalue+newvalue) + " WHERE " +COLUMN_NAME+ " = " + name;
        ContentValues args = new ContentValues();
        int finalvalue = currentvalue + newvalue;

        args.put(COLUMN_VALUE,finalvalue);
        //args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + id, null) > 0;
    }
}
