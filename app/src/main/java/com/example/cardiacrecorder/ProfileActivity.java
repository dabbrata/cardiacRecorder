package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

//    TextView idView;
//    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

//        idView = (TextView)findViewById(R.id.textView2);
//        idView = (TextView)findViewById(R.id.textView2);
//        idView = (TextView)findViewById(R.id.textView2);
//        idView = (TextView)findViewById(R.id.textView2);
//
//        Bundle bundle = getIntent().getExtras();
//        String bpmComment = bundle.getString("bpm_com");
//
//        idView.setText(bpmComment);


    }
}