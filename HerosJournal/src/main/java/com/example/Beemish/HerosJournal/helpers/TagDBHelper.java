package com.example.Beemish.HerosJournal.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Debug;
import android.util.Log;

import com.example.Beemish.HerosJournal.models.TagsModel;

import java.util.ArrayList;

public class TagDBHelper{
    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public TagDBHelper(Context context){
        this.context=context;
        databaseHelper=new DatabaseHelper(context);
    }

    // Adds new attribute tags into the database
    public boolean addNewTag(TagsModel tagsModel){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TAG_TITLE,tagsModel.getTagTitle());
        contentValues.put(DatabaseHelper.COL_TAG_EXP, tagsModel.getTagExp());
        contentValues.put(DatabaseHelper.COL_TAG_LEVEL, tagsModel.getTagLevel());
        sqLiteDatabase.insert(DatabaseHelper.TABLE_TAG_NAME,null,contentValues);
        sqLiteDatabase.close();
        return true;
    }

    // Check if the attribute tag exists already or not
    public boolean tagExists(String tagTitle){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TAG_TITLE + " FROM " + DatabaseHelper.TABLE_TAG_NAME + " WHERE " + DatabaseHelper.COL_TAG_TITLE+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(query,new String[]{tagTitle});
        return (cursor.getCount()>0)?true:false;
    }

    // Counts attribute tags from the database
    public int countTags(){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String query="SELECT " + DatabaseHelper.COL_TAG_ID + " FROM " + DatabaseHelper.TABLE_TAG_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        int count = cursor.getCount();
        cursor.close();
        String s = Integer.toString(count);
        Log.w("COUNT", s);
        return count;
    }

    // Gets all of the attribute tags from the database
    public ArrayList<TagsModel> fetchTags(){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        ArrayList<TagsModel> tagsModels=new ArrayList<>();
        String query="SELECT * FROM " + DatabaseHelper.TABLE_TAG_NAME + " ORDER BY " + DatabaseHelper.COL_TAG_ID + " DESC";
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            TagsModel tagsModel=new TagsModel();
            tagsModel.setTagID(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TAG_ID)));
            tagsModel.setTagTitle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TAG_TITLE)));
            tagsModel.setTagExp(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TAG_EXP)));
            tagsModels.add(tagsModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return tagsModels;
    }

    // Deletes attribute tag from the database according to the id
    public boolean removeTag(int tagID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        sqLiteDatabase.execSQL(DatabaseHelper.FORCE_FOREIGN_KEY);
        sqLiteDatabase.delete(DatabaseHelper.TABLE_TAG_NAME,DatabaseHelper.COL_TAG_ID+"=?",
                new String[]{String.valueOf(tagID)});
        sqLiteDatabase.close();
        return true;
    }

    // Update attribute tag from the database according to the tag id
    public boolean saveTag(TagsModel tagsModel){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL_TAG_TITLE,tagsModel.getTagTitle());
        contentValues.put(DatabaseHelper.COL_TAG_EXP, tagsModel.getTagExp());
        contentValues.put(DatabaseHelper.COL_TAG_LEVEL, tagsModel.getTagLevel());
        sqLiteDatabase.update(DatabaseHelper.TABLE_TAG_NAME,contentValues,DatabaseHelper.COL_TAG_ID+"=?",
                new String[]{String.valueOf(tagsModel.getTagID())});
        sqLiteDatabase.close();
        return true;
    }


    // Gets all of the attribute tags title strings from the database
    // This sets the strings for the spinner in the options menu from what's already in COL_TAG_TITLE
    public ArrayList<String> fetchTagStrings(){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        ArrayList<String> tagsModels=new ArrayList<>();
        String query="SELECT " + DatabaseHelper.COL_TAG_TITLE+ " FROM " + DatabaseHelper.TABLE_TAG_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        while (cursor.moveToNext()){
            tagsModels.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TAG_TITLE)));
        }
        cursor.close();
        sqLiteDatabase.close();
        return tagsModels;
    }

    // Gets attribute tag title from the database according to the tag id
    public String fetchTagTitle(int tagID){
        SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
        String fetchTitle="SELECT " + DatabaseHelper.COL_TAG_TITLE + " FROM " + DatabaseHelper.TABLE_TAG_NAME
                + " WHERE " + DatabaseHelper.COL_TAG_ID+"=?";
        Cursor cursor=sqLiteDatabase.rawQuery(fetchTitle,new String[]{String.valueOf(tagID)});
        String title="";
        if(cursor.moveToFirst()){
            title=cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_TAG_TITLE));
        }
        cursor.close();
        sqLiteDatabase.close();
        return title;
    }

    // Gets attribute tag ID from the database according to the tag title
    public int fetchTagID(String tagTitle){
        //check for illegal argument [Augi - 11/11/2018 edit]
        if(tagTitle != null){
            SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
            String fetchTitle="SELECT " + DatabaseHelper.COL_TAG_ID + " FROM " + DatabaseHelper.TABLE_TAG_NAME
                    + " WHERE " + DatabaseHelper.COL_TAG_TITLE+"=?";
            Cursor cursor=sqLiteDatabase.rawQuery(fetchTitle,new String[]{tagTitle});
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TAG_ID));
        }
        //returns 0 if there are no tags [Augi - 11/11/2018 edit ]
        else{
            return 0;
        }

    }

    public int fetchTagExp(String tagTitle) {
        if (tagTitle != null) {
            SQLiteDatabase sqLiteDatabase=this.databaseHelper.getReadableDatabase();
            String fetchTitle="SELECT " + DatabaseHelper.COL_TAG_EXP + " FROM " + DatabaseHelper.TABLE_TAG_NAME
                    + " WHERE " + DatabaseHelper.COL_TAG_TITLE+"=?";
            Cursor cursor = sqLiteDatabase.rawQuery(fetchTitle, new String[]{tagTitle});
            cursor.moveToFirst();
            return cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_TAG_EXP));
        } else return 0;
    }
}
