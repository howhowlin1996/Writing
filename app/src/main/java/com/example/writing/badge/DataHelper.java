package com.example.writing.badge;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataHelper extends SQLiteOpenHelper {
    String tableName;

    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, String table_name ) {
        super(context, name, factory, version);
        tableName=table_name;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL = "CREATE TABLE IF NOT EXISTS " +  "table_"+tableName + "( " +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "file_name VARCHAR(50), " +
                "practice_time INT" +
                ");";
        db.execSQL(SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int practiceTime(String file_name){
        final String SQL = "SELECT practice_time FROM " + "table_"+tableName + ";";
        Cursor ptime=this.getWritableDatabase().rawQuery(SQL,null);
        if (ptime.getCount()==0){

            return 0;
        }
        else {
            return Integer.valueOf(ptime.toString());
        }

    }
}
