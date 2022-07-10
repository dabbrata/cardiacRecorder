package com.example.cardiacrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "Record.db";
    private  static final String TABLE_NAME = "information";
    private  static final int VERSION_NUMBER = 5;
    private  static final String ID = "id";
    private  static final String USERNAME = "username";
    private  static final String BPM = "bpm";
    private  static final String SYSTOLIC = "systolic";
    private  static final String DYASTOLIC = "dyastolic";
    private  static final String BPM_CONDITION = "bpm_condition";
    private  static final String SYS_CONDITION = "sys_condition";
    private  static final String DYAS_CONDITION = "dyas_condition";
    private  static final String DATE = "record_date";
    private  static final String TIME = "record_time";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(550), "+BPM+" INTEGER,"+SYSTOLIC+" INTEGER, "+DYASTOLIC+" INTEGER, "+BPM_CONDITION+" VARCHAR(100), "+SYS_CONDITION+" VARCHAR(200), "+DYAS_CONDITION+" VARCHAR(200),"+DATE+" VARCHAR(100),"+TIME+" VARCHAR(100)); ";
    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE);
            Toast.makeText(context, "onCreate is called and table created", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(context, "Exception : "+e , Toast.LENGTH_LONG).show();
        }
        

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try{
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(context, "Exception "+e , Toast.LENGTH_LONG).show();
        }

    }

    //insert data in database table...

    public long insertData(String user,Integer bpm,Integer sys,Integer dyas,String bpmCondition, String sysCondition, String dyasCondition,String date,String time)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,user);
        contentValues.put(BPM,bpm);
        contentValues.put(SYSTOLIC,sys);
        contentValues.put(DYASTOLIC,dyas);
        contentValues.put(BPM_CONDITION,bpmCondition);
        contentValues.put(SYS_CONDITION,sysCondition);
        contentValues.put(DYAS_CONDITION,dyasCondition);
        contentValues.put(DATE,date);
        contentValues.put(TIME,time);
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor readAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }
    public boolean deleteOne(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + " = "+id;
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
    public Cursor showData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }
}
