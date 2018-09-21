package com.tracklocation.workshop;

import android.provider.BaseColumns;

/**
 * Created by Windows10 on 5/7/2561.
 */

public class DB_Structure {

    //Database
    public static final String DATABASE_NAME = "mydatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "user";

    public class Column {
        public static final String ID = BaseColumns._ID;
        public static final String USER_NAME = "username";
        public static final String USER_PASSWORD = "password";
    }

    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
