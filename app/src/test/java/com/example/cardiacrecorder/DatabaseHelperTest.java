package com.example.cardiacrecorder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@RunWith(RobolectricTestRunner.class)
public class DatabaseHelperTest {
    /**
     * this method test if a value is inserted in database properly
     */
    @Test
    public void testInsertData()
    {
        DatabaseHelper myDatabaseHelper = new DatabaseHelper((RuntimeEnvironment.application));

        String user = "Dabbrata";
        int bpm = 80;
        int sys = 120;
        int dias = 60;
        String bpmCondition = "normal";
        String sysCondition = "normal";
        String dyasCondition = "normal";
        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(calendar.getTime());

        long i = myDatabaseHelper.insertData(user,bpm,sys,dias,bpmCondition,sysCondition,dyasCondition,date,time);
        assertTrue(myDatabaseHelper.checkDataExistsOrNot(i));

        myDatabaseHelper.close();


    }

    /**
     * this method is to test to test the if the record is updated properly
     * if update successfull then the test case passed
     * the undefinable problem to parse integer in update testing
     */
    @Test
    public void testUpdateData() {
        DatabaseHelper myDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);

        String id = "2";
        String user = "Kowshik";
        String bpm = "80";
        String sys = "160";
        String dias = "60";
        String bpmCondition = "normal";
        String sysCondition = "Risk";
        String dyasCondition = "normal";

        Calendar calendar = Calendar.getInstance();

        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String time = dateFormat.format(calendar.getTime());

        long i = myDatabaseHelper.insertData(user,Integer.parseInt(bpm),Integer.parseInt(sys),Integer.parseInt(dias),bpmCondition,sysCondition,dyasCondition,date,time);


        myDatabaseHelper.updateData(Long.toString(myDatabaseHelper.insertData(user,Integer.parseInt(bpm),Integer.parseInt(sys),Integer.parseInt(dias),bpmCondition,sysCondition,dyasCondition,date,time)),"Dabbrata","100","165","65","risk","risk","normal",date,time);

        myDatabaseHelper.close();
    }
}
