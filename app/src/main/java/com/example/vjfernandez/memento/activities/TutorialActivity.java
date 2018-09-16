package com.example.vjfernandez.memento.activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vjfernandez.memento.R;
import com.example.vjfernandez.memento.sql.DatabaseHelper;

public class TutorialActivity extends AppCompatActivity {

    Button updateButton;
    TextView c1,t1,m1,c2,t2,m2;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        myDB= new DatabaseHelper(this);


        updateButton = findViewById(R.id.updateButton);
        c1 = findViewById(R.id.courseCode1);
        t1 = findViewById(R.id.tutorial1);
        m1 = findViewById(R.id.marks1);

        //displayMarks();
        updateTable();

    }

    public void updateTable(){
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),TutorialUpdateActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void displayMarks(){
        String result=myDB.getAllDataTutorial();
        c1.setText(result);
    }
}
