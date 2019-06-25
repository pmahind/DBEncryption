package com.example.databaseencriptionexample.database;

import com.example.databaseencriptionexample.model.User;

import net.sqlcipher.database.SQLiteDatabase;

public class UserDetailsDao {

    public static void createTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("CREATE TABLE " + "IF NOT EXISTS " + User.UserDetails.TABLE_NAME + " (" +
                        User.UserDetails.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                        User.UserDetails.COLUMN_FIRST_NAME + " TEXT, " +
                        User.UserDetails.COLUMN_LAST_NAME + " TEXT);");
    }

    public static void dropTable(SQLiteDatabase sqLiteDatabase){
        sqLiteDatabase.execSQL("DROP TABLE " + "IF EXISTS " + User.UserDetails.TABLE_NAME );
    }
}
