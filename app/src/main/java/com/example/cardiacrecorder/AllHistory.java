package com.example.cardiacrecorder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;

public class AllHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Model> dataHolder = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_history);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        recyclerView = (RecyclerView)findViewById(R.id.recViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = new DatabaseHelper(this).readAllData();

        while(cursor.moveToNext()){
            Model obj = new Model(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));
            dataHolder.add(obj);
        }

        dataShow();
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    private void dataShow() {

        MyAdapter myAdapter = new MyAdapter(dataHolder);
        recyclerView.setAdapter(myAdapter);
    }
}