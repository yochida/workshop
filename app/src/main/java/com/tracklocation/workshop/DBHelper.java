package com.tracklocation.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Windows10 on 5/7/2561.
 */

public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();
    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DB_Structure.DATABASE_NAME, null, DB_Structure.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_FRIEND_TABLE = String.format("CREATE TABLE %s " +
                        "(%s INTEGER PRIMARY KEY  AUTOINCREMENT, %s TEXT, %s TEXT)",
                DB_Structure.TABLE,
                DB_Structure.Column.ID,
                DB_Structure.Column.USER_NAME,
                DB_Structure.Column.USER_PASSWORD);

        Log.i(TAG, CREATE_FRIEND_TABLE);

        db.execSQL(CREATE_FRIEND_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_FRIEND_TABLE = "DROP TABLE IF EXISTS" + DB_Structure.TABLE;

        db.execSQL(DROP_FRIEND_TABLE);

        Log.i(TAG, "Upgrade Database from " + oldVersion + " to " + newVersion);

        onCreate(db);
    }

    public List<String> getUser() {
        List<String> user = new ArrayList<String>();

        sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (DB_Structure.TABLE, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        while (!cursor.isAfterLast()) {

            user.add(cursor.getLong(0) + " " +
                    cursor.getString(1) + " " +
                    cursor.getString(2));
            cursor.moveToNext();

        }

        sqLiteDatabase.close();

        return user;
    }

    public void addUser(DB_Structure user) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put(DB_Structure.Column.USER_NAME, user.getUsername());
        values.put(DB_Structure.Column.USER_PASSWORD, user.getPassword());

        sqLiteDatabase.insert(DB_Structure.TABLE, null, values);

        sqLiteDatabase.close();
    }

    public DB_Structure getUserID(String username, String password) {

        sqLiteDatabase = this.getReadableDatabase();
        DB_Structure user = new DB_Structure();

        try{
            Cursor cursor = sqLiteDatabase.query(DB_Structure.TABLE,
                    null,
                    DB_Structure.Column.USER_NAME + " = ? AND " + DB_Structure.Column.USER_PASSWORD + " = ? ",
                    new String[]{username, password},
                    null,
                    null,
                    null,
                    null);

            if (cursor != null) {
                cursor.moveToFirst();
            }

            user.setId((int) cursor.getLong(0));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));

        }catch (Exception e){

        }

        //Log.d("getData", "get by id: " + user.getId());
        return user;
    }

    public void updateUser(DB_Structure user) {

        sqLiteDatabase  = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DB_Structure.Column.ID, user.getId());
        values.put(DB_Structure.Column.USER_NAME, user.getUsername());
        values.put(DB_Structure.Column.USER_PASSWORD, user.getPassword());

        int row = sqLiteDatabase.update(DB_Structure.TABLE,
                values,
                DB_Structure.Column.ID + " = ? ",
                new String[] { String.valueOf(user.getId()) });

        sqLiteDatabase.close();
    }

    public void deleteUser(String id) {

        sqLiteDatabase = this.getWritableDatabase();

        /*sqLiteDatabase.delete(Friend.TABLE, Friend.Column.ID + " = ? ",
            new String[] { String.valueOf(friend.getId()) });*/

        sqLiteDatabase.delete(DB_Structure.TABLE, DB_Structure.Column.ID + " = " + id, null);

        sqLiteDatabase.close();
    }
}
