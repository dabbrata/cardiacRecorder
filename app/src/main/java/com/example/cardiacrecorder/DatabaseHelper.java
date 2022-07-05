package com.example.cardiacrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private  static final String DATABASE_NAME = "Record.db";
    private  static final String TABLE_NAME = "information";
    private  static final int VERSION_NUMBER = 2;
    private  static final String ID = "id";
    private  static final String USERNAME = "username";
    private  static final String BPM = "bpm";
    private  static final String SYSTOLIC = "systolic";
    private  static final String DYASTOLIC = "dyastolic";
    private  static final String BPM_CONDITION = "bpm_condition";
    private  static final String SYS_CONDITION = "sys_condition";
    private  static final String DYAS_CONDITION = "dyas_condition";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+USERNAME+" VARCHAR(550), "+BPM+" INTEGER,"+SYSTOLIC+" INTEGER, "+DYASTOLIC+" INTEGER, "+BPM_CONDITION+" VARCHAR(100), "+SYS_CONDITION+" VARCHAR(200), "+DYAS_CONDITION+" VARCHAR(200) ); ";
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

    public long insertData(String user,Integer bpm,Integer sys,Integer dyas,String bpmCondition, String sysCondition, String dyasCondition)
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
        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }
}
