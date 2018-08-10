package info.antor.www.amarnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final  String dbName="notes.db";
    public static final  String tableName ="notes_table";
    public static final  String col1="note_ID";
    public static final  String col2="note_text";

    public DatabaseHelper(Context context) {
        super(context,dbName,null,1);

    //    Log.d("antor","constructor");

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        String sql="CREATE TABLE " +tableName+" ( "+col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col2+" TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(sqLiteDatabase);
    }
    public boolean insertData(String text){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(col2,text);

        long flag=sqLiteDatabase.insert(tableName,null,values);
        if(flag!=-1) return  true;

        return false;
    }
    public Cursor getAll(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor results=sqLiteDatabase.rawQuery("SELECT * FROM "+tableName,null);
        return results;
    }
    public boolean clearAll(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM "+tableName);
        long rows= DatabaseUtils.queryNumEntries(sqLiteDatabase,tableName);
        if(rows==0) return true;
        return false;
    }


}
