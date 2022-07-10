package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView idView,userView,dateView,timeView,hrView,dyasView,sysView,hrCommentView,dyasComment,sysComment;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ImageView backButton;
    Button updateBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        backButton = (ImageView)findViewById(R.id.imageView2);
        updateBtn = (Button)findViewById(R.id.button4);

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
//        String bpmCommentVal = bundle.getString("bpm_com");
//        String dyasCommentVal = bundle.getString("dyas_com");
//        String sysCommentVal = bundle.getString("sys_com");
        String bpmVal = bundle.getString("bpm");
        String dyasVal = bundle.getString("dyas");
        String sysVal = bundle.getString("sys");
        String dateVal = bundle.getString("date");
        String timeVal = bundle.getString("time");
        String userVal = bundle.getString("username");

        userView.setText(userVal);
        dateView.setText(dateVal);
        timeView.setText(timeVal);

        hrView.setText(bpmVal+" BPM");
        dyasView.setText(dyasVal+" mmHg");
        sysView.setText(sysVal+" mmHg");

        Cursor cursor = databaseHelper.showData(id);
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();

        while (cursor.moveToNext()){
            stringBuilder1.append(cursor.getString(5));
            stringBuilder2.append(cursor.getString(6));
            stringBuilder3.append(cursor.getString(7));
        }

        hrCommentView.setText(stringBuilder1);
        sysComment.setText(stringBuilder2);
        dyasComment.setText(stringBuilder3);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,AllHistory.class);
                startActivity(i);
                finish();
            }
        });

        Bundle bundle1 = new Bundle();
        bundle1.putInt("id",id);
        bundle1.putString("username",userVal);
        bundle1.putString("bpm",bpmVal);
        bundle1.putString("sys",sysVal);
        bundle1.putString("dyas",dyasVal);
        bundle1.putString("sys_comment",stringBuilder2.toString());
        bundle1.putString("dyas_comment",stringBuilder3.toString());
        bundle1.putString("bpm_comment",stringBuilder1.toString());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this,UpdatePage.class);
                i.putExtras(bundle1);
                startActivity(i);
                finish();
            }
        });



    }
}