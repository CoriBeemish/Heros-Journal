package com.example.Beemish.HerosJournal.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.Beemish.HerosJournal.models.UserModel;

public class UserDBHelper {
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public UserDBHelper(Context context) {
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    //add new user
    public boolean addNewUser(UserModel userModel) {
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_USER_EMAIL, userModel.getUserEmail());
        contentValues.put(DatabaseHelper.COL_USER_PASSWORD, userModel.getUserPassword());
        contentValues.put(DatabaseHelper.COL_USER_AVATAR, userModel.getUserAvatar());
        contentValues.put(DatabaseHelper.COL_USER_WEAPON, userModel.getUserWeaponValue());
        contentValues.put(DatabaseHelper.COL_USER_HELMET, userModel.getUserHelmetValue());
        contentValues.put(DatabaseHelper.COL_USER_SHIRT, userModel.getUserShirtValue());
        contentValues.put(DatabaseHelper.COL_USER_BACKGROUND, userModel.getUserBackgroundValue());
        sqLiteDatabase.insert(DatabaseHelper.TABLE_USER_EMAIL, null, contentValues);
        sqLiteDatabase.close();
        return true;
    }

    //check whether the user exists or not
    public boolean userExists(String userEmail) {
        SQLiteDatabase sqLiteDatabase = this.databaseHelper.getReadableDatabase();
        String query = "SELECT " + DatabaseHelper.COL_USER_EMAIL + " FROM " + DatabaseHelper.TABLE_USER_EMAIL + " WHERE " + DatabaseHelper.COL_USER_EMAIL + "=?";
        Cursor cursor = sqLiteDatabase.rawQuery(query, new String[]{userEmail});
        return (cursor.getCount()>0?true:false);
    }

    //fetch specific userModel from the database
    public UserModel fetchUser(String email) {
        SQLiteDatabase sqLiteDatabase = this.databaseHelper.getReadableDatabase();
        UserModel userModel = new UserModel(email);

        String query = "SELECT * FROM " + DatabaseHelper.TABLE_USER_EMAIL + " ORDER BY " + DatabaseHelper.COL_USER_ID + " DESC";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            if (email.compareTo(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_EMAIL))) == 0) {
                userModel = new UserModel(cursor.getInt(cursor.getColumnIndex((DatabaseHelper.COL_USER_ID))),
                        email, cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USER_PASSWORD)));
                userModel.setUserAvatar(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_AVATAR)));
                userModel.setUserID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_ID)));
                userModel.setUserWeaponValue(cursor.getInt(cursor.getColumnIndex((DatabaseHelper.COL_USER_WEAPON))));
                userModel.setUserHelmetValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_HELMET)));
                userModel.setUserShirtValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_SHIRT)));
                userModel.setUserBackgroundValue(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_USER_BACKGROUND)));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return userModel;
    }

    //update user according to tag id
    public boolean saveUser(UserModel userModel) {
        SQLiteDatabase sqLiteDatabase = this.databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_USER_EMAIL, userModel.getUserEmail());
        contentValues.put(DatabaseHelper.COL_USER_PASSWORD, userModel.getUserPassword());
        contentValues.put(DatabaseHelper.COL_USER_AVATAR, userModel.getUserAvatar());
        contentValues.put(DatabaseHelper.COL_USER_WEAPON, userModel.getUserWeaponValue());
        contentValues.put(DatabaseHelper.COL_USER_HELMET, userModel.getUserHelmetValue());
        contentValues.put(DatabaseHelper.COL_USER_SHIRT, userModel.getUserShirtValue());
        contentValues.put(DatabaseHelper.COL_USER_BACKGROUND, userModel.getUserBackgroundValue());
        sqLiteDatabase.update(DatabaseHelper.TABLE_USER_EMAIL, contentValues, DatabaseHelper.COL_USER_ID + "=?",
                new String[]{String.valueOf(userModel.getUserID())});
        sqLiteDatabase.close();
        return true;
    }

    public int countUsers() {
        SQLiteDatabase sqLiteDatabase = this.databaseHelper.getReadableDatabase();
        String query = "SELECT " + DatabaseHelper.COL_USER_ID + " FROM " + DatabaseHelper.TABLE_USER_EMAIL;
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        int count;
        if (cursor == null) {
            count = 0;
        } else count = cursor.getCount();
        cursor.close();
        String s = Integer.toString(count);
        Log.v("COUNT", s);
        return count;
    }

    //
}
