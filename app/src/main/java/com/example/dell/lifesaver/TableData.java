package com.example.dell.lifesaver;

import android.provider.BaseColumns;

public class TableData{

    public TableData() {
    }

    public static abstract class TableInfo implements BaseColumns{

        public static final String DATABASE_NAME = "users.db";
        public static final String TABLE_NAME = "user_info";

        public static final String USER_NAME = "user_name";
        public static final String USER_EMAIL = "user_email";
        public static final String USER_ADDRESS = "user_address";
        public static final String USER_PHONE = "user_phone";
        public static final String USER_BG = "user_bg";
        public static final String EMERGENCY_CONTACT = "emergency_contact";
        public static final String EMERGENCY_NUMBER = "emergency_number";
    }
}
