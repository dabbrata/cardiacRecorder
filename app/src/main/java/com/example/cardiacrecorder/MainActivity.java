package com.example.cardiacrecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * In this activity,users provide their records to check and store.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Intialize all the variable for finding the buuton,text etc.
     * Also creates some object of the specific class
     */
    ImageButton refreshButton;
    ImageButton crossButton;
    EditText userName,bpm,sysPress,diasPress;
    Button checkButton,saveButton,hisButton;
    TextView sysText,diasText,bpmText;

    int bpmNum,dysPressure,sysPressure;

    DatabaseHelper databaseHelper;
    String sysComment="No Comment",dyasComment="No Comment",bpmComment="No Comment";

    Calendar calendar;
    SimpleDateFormat simpleDateFormat,simpleTimeFormat;
    String curdate;
    String curtime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Hide the actionbar
         */
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        refreshButton = (ImageButton)findViewById(R.id.refreshButton);
        crossButton  = (ImageButton) findViewById(R.id.crossButton);

        userName = (TextInputEditText)findViewById(R.id.textInputEditText);
        bpm = (EditText)findViewById(R.id.textInputEditText2);
        sysPress = (EditText)findViewById(R.id.textInputEditText3);
        diasPress = (EditText)findViewById(R.id.textInputEditText4);

        sysText = (TextView)findViewById(R.id.textView11);
        diasText = (TextView)findViewById(R.id.textView12);
        bpmText = (TextView)findViewById(R.id.textView13);

        checkButton = (Button)findViewById(R.id.button2);
        saveButton = (Button)findViewById(R.id.button);
        hisButton = (Button)findViewById(R.id.button3);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();


        /**
         * the check button will be checked with respect to cardiac records given by users
         * After clicking check button the values are shown in three text view field
         * Here the values which are shown in text view is the cardiac condition of a person
         */
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameStr = userName.getText().toString().trim();
                String bpmStr = bpm.getText().toString().trim();
                String sysPressStr = sysPress.getText().toString().trim();
                String diasPressStr = diasPress.getText().toString().trim();

                if(userNameStr.isEmpty()){
                    userName.setError("Provide username");
                    userName.requestFocus();
                    return;
                  }
                if(bpmStr.isEmpty()){
                    bpm.setError("Provide your heart rate");
                    bpm.requestFocus();
                    return;
                }

                if(sysPressStr.isEmpty()){
                    sysPress.setError("Provide systolic pressure");
                    sysPress.requestFocus();
                    return;
                }


                if(diasPressStr.isEmpty()){
                    diasPress.setError("Provide diastolic pressure");
                    diasPress.requestFocus();
                    return;
                }

                //emppty check complete...

                bpmNum = new Integer(bpmStr).intValue();
                dysPressure = new Integer(diasPressStr).intValue();
                sysPressure = new Integer(sysPressStr).intValue();

                if(bpmNum<0){
                    bpm.setError("Provide positive value");
                    bpm.requestFocus();
                    return;
                }else if(bpmNum<40){
                    bpm.setError("Provide relevant value between 40 to 120");
                    bpm.requestFocus();
                    return;
                }
                else if(bpmNum > 120){
                    bpm.setError("Provide relevant value between 40 to 120");
                    bpm.requestFocus();
                    return;
                }

                if(sysPressure<0){
                    sysPress.setError("Provide positive value");
                    sysPress.requestFocus();
                    return;
                }else if(sysPressure<60 || sysPressure >200){
                    sysPress.setError("Provide relevant value between 60 to 200 where normal is 120");
                    sysPress.requestFocus();
                    return;
                }


                if(dysPressure<0){
                    diasPress.setError("Provide positive value");
                    diasPress.requestFocus();
                    return;
                }
                else if(dysPressure < 40 || dysPressure > 150){
                    diasPress.setError("Provide relevant value between 40 to 150 where normal is 80");
                    diasPress.requestFocus();
                    return;
                }

                //check range and category measurement

                if((sysPressure <= 140 && sysPressure>=90))
                {
                    sysComment = "Normal";
                    sysText.setText(sysComment);
                    sysText.setTextColor(Color.parseColor("#00ff00"));

                }
                else{
                    sysComment = "Risk";
                    sysText.setText(sysComment);
                    sysText.setTextColor(Color.parseColor("#ff0000"));
                }

                if(dysPressure <= 90 && dysPressure >= 60)
                {
                    dyasComment = "Normal";
                    diasText.setText(dyasComment);
                    diasText.setTextColor(Color.parseColor("#00ff00"));
                }
                else{
                    dyasComment = "Risk";
                    diasText.setText(dyasComment);
                    diasText.setTextColor(Color.parseColor("#ff0000"));
                }
                //end of normality of blood pressure

                if(bpmNum < 40 || bpmNum > 160){
                    bpm.setError("Provide relevant value of heart beat between 40 to 160");
                    bpm.requestFocus();
                    return;
                }

                if(bpmNum > 59 && bpmNum <101)
                {
                    bpmComment = "Normal";
                    bpmText.setText(bpmComment);
                    bpmText.setTextColor(Color.parseColor("#00ff00"));
                }
                else{
                    bpmComment = "Risk";
                    bpmText.setText(bpmComment);
                    bpmText.setTextColor(Color.parseColor("#ff0000"));
                }


            }
        });

        /**
         * after clicking dave button , the data is saved into the sqlite database
         * after inserting the data into sqlite database it checks some validation
         * If irrelevant values are given by user , then it shows some error message
         */

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userNameStr = userName.getText().toString().trim();
                String bpmStr = bpm.getText().toString().trim();
                String sysPressStr = sysPress.getText().toString().trim();
                String diasPressStr = diasPress.getText().toString().trim();

                if(userNameStr.isEmpty()){
                    userName.setError("Provide username");
                    userName.requestFocus();
                    return;
                }
                if(bpmStr.isEmpty()){
                    bpm.setError("Provide your heart rate");
                    bpm.requestFocus();
                    return;
                }

                if(sysPressStr.isEmpty()){
                    sysPress.setError("Provide systolic pressure");
                    sysPress.requestFocus();
                    return;
                }


                if(diasPressStr.isEmpty()){
                    diasPress.setError("Provide diastolic pressure");
                    diasPress.requestFocus();
                    return;
                }

                //emppty check complete...

                bpmNum = new Integer(bpmStr).intValue();
                dysPressure = new Integer(diasPressStr).intValue();
                sysPressure = new Integer(sysPressStr).intValue();

                if(bpmNum<0){
                    bpm.setError("Provide positive value");
                    bpm.requestFocus();
                    return;
                }else if(bpmNum<40){
                    bpm.setError("Provide relevant value between 40 to 120");
                    bpm.requestFocus();
                    return;
                }
                else if(bpmNum > 120){
                    bpm.setError("Provide relevant value between 40 to 120");
                    bpm.requestFocus();
                    return;
                }

                if(sysPressure<0){
                    sysPress.setError("Provide positive value");
                    sysPress.requestFocus();
                    return;
                }else if(sysPressure<60 || sysPressure >200){
                    sysPress.setError("Provide relevant value between 60 to 200 where normal is 120");
                    sysPress.requestFocus();
                    return;
                }


                if(dysPressure<0){
                    diasPress.setError("Provide positive value");
                    diasPress.requestFocus();
                    return;
                }
                else if(dysPressure < 40 || dysPressure > 150){
                    diasPress.setError("Provide relevant value between 40 to 150 where normal is 80");
                    diasPress.requestFocus();
                    return;
                }

                //check range and category measurement

                //end of normality of blood pressure

                if(bpmNum < 40 || bpmNum > 160){
                    bpm.setError("Provide relevant value of heart beat between 40 to 160");
                    bpm.requestFocus();
                    return;
                }

                calendar = Calendar.getInstance();
                simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
                curdate = simpleDateFormat.format(calendar.getTime());
                simpleTimeFormat = new SimpleDateFormat("hh:mm aa");
                curtime = simpleTimeFormat.format(calendar.getTime());


                /**
                 * here the insertData function is called and pass all the values which will be stored into the sqlite database
                 * before passing the data the time and date will be generated and this will store the insertion time in the database
                 */
                long rowId = databaseHelper.insertData(userNameStr,bpmNum,sysPressure,dysPressure,bpmComment,sysComment,dyasComment,curdate,curtime);
                if(rowId != -1){

                    userName.setText("");
                    bpm.setText("");
                    sysPress.setText("");
                    diasPress.setText("");

                    bpmText.setText("Type");
                    bpmText.setTextColor(Color.parseColor("#cbc3c3"));
                    sysText.setText("Type");
                    sysText.setTextColor(Color.parseColor("#cbc3c3"));
                    diasText.setText("Type");
                    diasText.setTextColor(Color.parseColor("#cbc3c3"));
                    Toast.makeText(getApplicationContext(), "Succesfully Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Insertion not successful!", Toast.LENGTH_LONG).show();
                }


            }
        });

        /**
         * click the history button will change the activity from main to all histiry activity
         * in all history activity all the records are shown from database
         */
        hisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(MainActivity.this,AllHistory.class);
                startActivity(i);
            }
        });
        /**
         * refresh button refreshes the main activity page to clear input data
         */
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        /**
         * crossButton is used to exit this app
         */
        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Do you want to exit?")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finishAffinity();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        }


    /**
     * After clicking on backpress button in mainactivity page,it shows a alert dialog box .
     * aftr clicking the yes of alert dialog box, the app will be closed
     */
    @Override
    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
   }


}