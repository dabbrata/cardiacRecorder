package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView idView,userView,dateView,timeView,hrView,dyasView,sysView,hrCommentView,dyasComment,sysComment;
    DatabaseHelper databaseHelper;
    ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        backButton = (ImageView)findViewById(R.id.imageView2);

        idView = (TextView)findViewById(R.id.textView2);
        userView = (TextView)findViewById(R.id.textView3);
        dateView = (TextView)findViewById(R.id.textView4);
        timeView = (TextView)findViewById(R.id.textView5);
        hrView = (TextView)findViewById(R.id.textView22);
        dyasView = (TextView)findViewById(R.id.textView13);
        sysView = (TextView)findViewById(R.id.textView14);
        hrCommentView = (TextView)findViewById(R.id.textView15);
        dyasComment = (TextView)findViewById(R.id.textView24);
        sysComment = (TextView)findViewById(R.id.textView25);

        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        String bpmCommentVal = bundle.getString("bpm_com");
        String dyasCommentVal = bundle.getString("dyas_com");
        String sysCommentVal = bundle.getString("sys_com");
        String bpmVal = bundle.getString("bpm");
        String dyasVal = bundle.getString("dyas");
        String sysVal = bundle.getString("sys");
        String dateVal = bundle.getString("date");
        String timeVal = bundle.getString("time");
        String userVal = bundle.getString("username");

        userView.setText(userVal);
        dateView.setText(dateVal);
        timeView.setText(timeVal);

        hrView.setText(bpmVal);
        dyasView.setText(dyasVal);
        sysView.setText(sysVal);

        hrCommentView.setText(bpmCommentVal);
        dyasComment.setText(dyasCommentVal);
        sysComment.setText(sysCommentVal);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,AllHistory.class);
                startActivity(i);
                finish();
            }
        });


    }
}