package com.example.cardiacrecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * In this activity recycler view is added and all items are shown sequentially.
 */

public class AllHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> dataHolder = new ArrayList<>();

    ImageView backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_history);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        backButton = (ImageView)findViewById(R.id.imageBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AllHistory.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        /**
         * find recyler view id
         * by cursor it will point a row of the data from sqlite database
         * Then using while loop all rows will be fetched
         */
        recyclerView = (RecyclerView)findViewById(R.id.recViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DatabaseHelper(this).readAllData();

        while(cursor.moveToNext()){
            Model obj = new Model(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            dataHolder.add(obj);
        }

        /**
         * the data show method is called for showing the data into the recycler view item
         */
        dataShow();
    }


    /**
     * the dataShow method is created for showing data in recycler view.
     */
    private void dataShow() {
        MyAdapter myAdapter = new MyAdapter(dataHolder);
        recyclerView.setAdapter(myAdapter);
    }
}