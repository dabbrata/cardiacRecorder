package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePage extends AppCompatActivity {

    TextView idView,userView,dateView,timeView,hrView,dyasView,sysView,hrCommentView,dyasComment,sysComment;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ImageView backButton;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        backButton = (ImageView)findViewById(R.id.imageView5);
        updateButton = (Button)findViewById(R.id.buttonUpdate);

//       idView = (TextView)findViewById(R.id.textView2);
        userView = (EditText)findViewById(R.id.textInputEditText);
        hrView = (EditText)findViewById(R.id.textInputEditText2);
        dyasView = (EditText)findViewById(R.id.textInputEditText4);
        sysView = (EditText)findViewById(R.id.textInputEditText3);
        hrCommentView = (EditText)findViewById(R.id.textInputEditText7);
        dyasComment = (EditText)findViewById(R.id.textInputEditText6);
        sysComment = (EditText)findViewById(R.id.textInputEditText5);

        Bundle bundle = getIntent().getExtras();

        int id = bundle.getInt("id");

        userView.setText(bundle.getString("username"));
        hrView.setText(bundle.getString("bpm"));
        dyasView.setText(bundle.getString("dyas"));
        sysView.setText(bundle.getString("sys"));
        hrCommentView.setText(bundle.getString("bpm_comment"));
        dyasComment.setText(bundle.getString("dyas_comment"));
        sysComment.setText(bundle.getString("sys_comment"));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdatePage.this,AllHistory.class);
                startActivity(intent);
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userNameStr = userView.getText().toString().trim();
                String bpmStr = hrView.getText().toString().trim();
                String sysPressStr = sysView.getText().toString().trim();
                String diasPressStr = dyasView.getText().toString().trim();



                //field emptiness check.......
                if(userNameStr.isEmpty()){
                    userView.setError("Provide username");
                    userView.requestFocus();
                    return;
                }
                if(bpmStr.isEmpty()){
                    hrView.setError("Provide your heart rate");
                    hrView.requestFocus();
                    return;
                }

                if(sysPressStr.isEmpty()){
                    sysView.setError("Provide systolic pressure");
                    sysView.requestFocus();
                    return;
                }


                if(diasPressStr.isEmpty()){
                    dyasView.setError("Provide diastolic pressure");
                    dyasView.requestFocus();
                    return;
                }

                //end....
                int bpmNum = new Integer(bpmStr).intValue();
                int dysPressure = new Integer(diasPressStr).intValue();
                int sysPressure = new Integer(sysPressStr).intValue();

                if(bpmNum<0){
                    hrView.setError("Provide positive value");
                    hrView.requestFocus();
                    return;
                }else if(bpmNum<40){
                    hrView.setError("Provide relevant value between 40 to 120");
                    hrView.requestFocus();
                    return;
                }
                else if(bpmNum > 120){
                    hrView.setError("Provide relevant value between 40 to 120");
                    hrView.requestFocus();
                    return;
                }

                if(sysPressure<0){
                    sysView.setError("Provide positive value");
                    sysView.requestFocus();
                    return;
                }else if(sysPressure<60 || sysPressure >200){
                    sysView.setError("Provide relevant value between 60 to 200 where normal is 120");
                    sysView.requestFocus();
                    return;
                }


                if(dysPressure<0){
                    dyasView.setError("Provide positive value");
                    dyasView.requestFocus();
                    return;
                }
                else if(dysPressure < 40 || dysPressure > 150){
                    dyasView.setError("Provide relevant value between 40 to 150 where normal is 80");
                    dyasView.requestFocus();
                    return;
                }

                //comment string size restriction..

                String bpm_com = hrCommentView.getText().toString();
                String sys_com = sysComment.getText().toString();
                String dyas_com = dyasComment.getText().toString();


                if(bpm_com.length() > 6){
                    hrCommentView.setError("Text size must not greater than 6");
                    hrCommentView.requestFocus();
                    return;
                }
                if(sys_com.length() > 6){
                    sysComment.setError("Text size must not greater than 6");
                    sysComment.requestFocus();
                    return;
                }
                if(dyas_com.length() > 6){
                    dyasComment.setError("Text size must not greater than 6");
                    dyasComment.requestFocus();
                    return;
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(id);
                String id = stringBuilder.toString();
                Boolean updated = databaseHelper.updateData(id,userNameStr,bpmStr,sysPressStr,diasPressStr,bpm_com,sys_com,dyas_com);

                if(updated){
                    Toast.makeText(UpdatePage.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdatePage.this,AllHistory.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(UpdatePage.this, "Updation not successful", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}