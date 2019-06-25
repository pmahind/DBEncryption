package com.example.databaseencriptionexample.model;

import android.provider.BaseColumns;

public class User {
    public static abstract class UserDetails implements BaseColumns{
        public static final String TABLE_NAME = "USER";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
    }
}
