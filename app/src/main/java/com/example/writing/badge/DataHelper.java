package com.example.writing.badge;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
     String table_name;

    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version,String table_name) {
        super(context, name, factory, version);
        this.table_name=table_name;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLTable = "CREATE TABLE IF NOT EXISTS " + table_name + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "File TEXT, " +
                ");";
        db.execSQL(SQLTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
