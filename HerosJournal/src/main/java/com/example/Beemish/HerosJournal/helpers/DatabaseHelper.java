package com.example.Beemish.HerosJournal.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=6;
    private static final String DATABASE_NAME="todomanager";

    //tag table and columns
    public static final String TABLE_TAG_NAME="tags";
    public static final String COL_TAG_ID="tag_id";
    public static final String COL_TAG_TITLE="tag_title";
    public static final String COL_TAG_LEVEL="tag_level";
    public static final String COL_TAG_EXP="tag_exp";

    //todos table and columns
    public static final String TABLE_TODO_NAME="todos";
    public static final String COL_TODO_ID="todo_id";
    public static final String COL_TODO_TITLE="todo_title";
    public static final String COL_TODO_CONTENT="todo_content";
    public static final String COL_TODO_TAG="todo_tag";
    public static final String COL_TODO_DATE="todo_date";
    public static final String COL_TODO_TIME="todo_time";
    public static final String COL_TODO_STATUS="todo_status";
    public static final String COL_DEFAULT_STATUS="pending";
    public static final String COL_STATUS_COMPLETED="completed";

    //user table and columns
    public static final String TABLE_USER_EMAIL="users";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_EMAIL = "user_email";
    public static final String COL_USER_PASSWORD = "user_password";
    public static final String COL_USER_AVATAR = "user_avatar";
    public static final String COL_USER_WEAPON = "user_weapon";
    public static final String COL_USER_HELMET = "user_helmet";
    public static final String COL_USER_SHIRT = "user_shirt";
    public static final String COL_USER_BACKGROUND = "user_background";

    //forcing foreign key
    public static final String FORCE_FOREIGN_KEY="PRAGMA foreign_keys=ON";

    //creating tags table query
    private static final String CREATE_TAGS_TABLE="CREATE TABLE IF NOT EXISTS " + TABLE_TAG_NAME+"("+
            COL_TAG_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
            COL_TAG_TITLE+" TEXT NOT NULL UNIQUE,"+
            COL_TAG_LEVEL+" INT NOT NULL,"+
            COL_TAG_EXP+" INT NOT NULL" +")";

    //creating todos table query
    private static final String CREATE_TODOS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_TODO_NAME +
                    "("+ COL_TODO_ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + COL_TODO_TITLE+" TEXT NOT NULL,"
                    + COL_TODO_CONTENT + " TEXT NOT NULL,"
                    + COL_TODO_TAG + " INTEGER NOT NULL,"
                    + COL_TODO_DATE+" TEXT NOT NULL,"
                    + COL_TODO_TIME+" TEXT NOT NULL,"
                    + COL_TODO_STATUS+" TEXT NOT NULL DEFAULT "
                    + COL_DEFAULT_STATUS+ " ,FOREIGN KEY( "
                    + COL_TODO_TAG+") REFERENCES "
                    + TABLE_TAG_NAME + "(" + COL_TAG_ID +" ) ON UPDATE CASCADE ON DELETE CASCADE" + ")";

    //creating user table query
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_USER_EMAIL +
                    "(" + COL_USER_ID+ " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                    + COL_USER_EMAIL + " TEXT NOT NULL,"
                    + COL_USER_PASSWORD + " TEXT NOT NULL,"
                    + COL_USER_AVATAR + " INTEGER NOT NULL,"
                    + COL_USER_WEAPON + " INTEGER,"
                    + COL_USER_HELMET + " INTEGER,"
                    + COL_USER_SHIRT + " INTEGER,"
                    + COL_USER_BACKGROUND + " INTEGER NOT NULL" + ")";

    //dropping tags table
    private static final String DROP_TAGS_TABLE="DROP TABLE IF EXISTS " + TABLE_TAG_NAME;
    //dropping todos table
    private static final String DROP_TODOS_TABLE="DROP TABLE IF EXISTS " + TABLE_TODO_NAME;
    //dropping users table
    private static final String DROP_USER_TABLE="DROP TABLE IF EXISTS " + TABLE_USER_EMAIL;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TAGS_TABLE);
        sqLiteDatabase.execSQL(CREATE_TODOS_TABLE);
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(FORCE_FOREIGN_KEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TAGS_TABLE);
        sqLiteDatabase.execSQL(DROP_TODOS_TABLE);
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }
}
