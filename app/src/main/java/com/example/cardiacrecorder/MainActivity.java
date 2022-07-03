package com.example.cardiacrecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    ImageButton refreshButton;
    ImageButton crossButton;
    EditText userName,bpm,sysPress,diasPress;
    Button checkButton,saveButton,hisButton;
    TextView sysText,diasText,bpmText;

    int bpmNum,dysPressure,sysPressure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    sysText.setText("Normal");
                    sysText.setTextColor(Color.parseColor("#00ff00"));

                }
                else{
                    sysText.setText("Risk");
                    sysText.setTextColor(Color.parseColor("#ff0000"));
                }

                if(dysPressure <= 90 && dysPressure >= 60)
                {
                    diasText.setText("Normal");
                    diasText.setTextColor(Color.parseColor("#00ff00"));
                }
                else{

                    diasText.setText("Risk");
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
                    bpmText.setText("Normal");
                    bpmText.setTextColor(Color.parseColor("#00ff00"));
                }
                else{
                    bpmText.setText("Risk");
                    bpmText.setTextColor(Color.parseColor("#ff0000"));
                }


            }
        });


        //refresh the main activity page to clear input data
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        //crossButton to exit this app
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