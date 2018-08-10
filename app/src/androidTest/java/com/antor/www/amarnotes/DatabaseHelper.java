package com.antor.www.amarnotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final  String dbName="student.db";
    public static final  String tableName ="student_table";
    public static final  String col1="ID";
    public static final  String col2="NAME";
    public static final  String col3="SURNAME";
    public static final  String col4="MARKS";
    public DatabaseHelper(Context context) {
        super(context,dbName,null,1);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        String sql="CREATE TABLE " +tableName+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)";
        sqLiteDatabase.execSQL(sql);
        Log.d("antor",sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
       onCreate(sqLiteDatabase);
         }
}
