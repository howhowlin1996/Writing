package com.example.writing.badge;

import android.content.ContentValues;
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
                "file_name VARCHAR(100), " +
                "practice_time INT" +
                ");";
        db.execSQL(SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void update(String file_name,int practice_time){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("practice_time",practice_time);
        db.update("table_"+tableName,values,"file_name = "+"'"+file_name+"'",null);

    }

    public void insert(String file_name,int practice_time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("file_name",file_name);
        values.put("practice_time",practice_time);
        db.insert("table_"+tableName,null,values);

    }

    public int practiceTime(String file_name){
        final String SQL = "SELECT practice_time FROM " + "table_"+tableName +" WHERE file_name="+"'"+file_name+"'"+ ";";
        Log.d("error",new String(" "+SQL));
        Cursor ptime=this.getWritableDatabase().rawQuery(SQL,null);
        ptime.moveToFirst();
        if (ptime.getCount()==0){
            //Log.d("error",new String(" "+"0"));
            return 0;
        }
        else {
            //Log.d("error"," "+ new String(ptime.getInt(0)+" "+ptime.getCount()));
            return ptime.getInt(0);

        }

    }
}
