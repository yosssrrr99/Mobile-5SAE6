package com.example.projectcitysport;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "my_table";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Title";
    public static final String COLUMN_NAME2 = "nbPlace";
    public static final String COLUMN_NAME3 = "Date";
    public static final String COLUMN_NAME4 = "Image";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NAME2 + " INTEGER, " +
                COLUMN_NAME3 + " TEXT, " +
                COLUMN_NAME4 + " TEXT " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Event> getAllData() {
        List<Event> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME, // The table name
                null, // The columns you want to retrieve (null for all)
                null, // The selection criteria (null for all)
                null, // Selection arguments (null for all)
                null, // Group by (null for no grouping)
                null, // Having (null for no having)
                null  // Order by (null for no sorting)
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Event data = new Event();
                    data.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_ID))+"" );
                    data.Title = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME));
                    data.nbPlace = Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME2)));
                    data.Date = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME3));
                    data.Image = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME4));
                    // Add more columns as needed
                    dataList.add(data);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return dataList;
    }


    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public int updateData(long id, Integer nbPlace) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME2, nbPlace);

        // Define the WHERE clause to specify which row(s) to update
        String whereClause = DBHelper.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};

        // Execute the update operation
        int rowsAffected = db.update(DBHelper.TABLE_NAME, values, whereClause, whereArgs);

        db.close();
        return rowsAffected;
    }

    public long insertData() {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        String str="2023-03-31";
        Date date=Date.valueOf(str);

        values.put(DBHelper.COLUMN_NAME, "Marathon");
        values.put(DBHelper.COLUMN_NAME2, 112);
        values.put(DBHelper.COLUMN_NAME3, date.toString());
        values.put(DBHelper.COLUMN_NAME4, "event2");
        long newRowId = db.insert(DBHelper.TABLE_NAME, null, values);

        values.put(DBHelper.COLUMN_NAME, "Camping");
        values.put(DBHelper.COLUMN_NAME2, 12);
        values.put(DBHelper.COLUMN_NAME3, date.toString());
        values.put(DBHelper.COLUMN_NAME4, "event3");
        long newRowId2 = db.insert(DBHelper.TABLE_NAME, null, values);

        values.put(DBHelper.COLUMN_NAME, "Climbing");
        values.put(DBHelper.COLUMN_NAME2, 12);
        values.put(DBHelper.COLUMN_NAME3, date.toString());
        values.put(DBHelper.COLUMN_NAME4, "event4");
        long newRowId3 = db.insert(DBHelper.TABLE_NAME, null, values);

        values.put(DBHelper.COLUMN_NAME, "Diving");
        values.put(DBHelper.COLUMN_NAME2, 12);
        values.put(DBHelper.COLUMN_NAME3,Date.valueOf("2023-12-31").toString());
        values.put(DBHelper.COLUMN_NAME4, "event5");
        long newRowId4 = db.insert(DBHelper.TABLE_NAME, null, values);

        db.close();
        return newRowId;
    }
    public Cursor selectDataById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                DBHelper.COLUMN_ID,
                DBHelper.COLUMN_NAME
        };

        String selection = DBHelper.COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};

        Cursor cursor = db.query(
                DBHelper.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        return cursor;
    }


}