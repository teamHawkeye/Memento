package com.example.vjfernandez.memento.activities;


import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vjfernandez.memento.R;
import com.example.vjfernandez.memento.sql.DatabaseHelper;

public class TutorialActivity extends AppCompatActivity {

    Button updateButton,refreshButton;
    TextView c1,t1,m1,c2,t2,m2,c3,t3,m3,c4,t4,m4,c5,t5,m5,c6,t6,m6,c7,t7,m7,c8,t8,m8,c9,t9,m9,c10,t10,m10;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        myDB= new DatabaseHelper(this);

        updateButton = findViewById(R.id.updateButton);
        refreshButton=findViewById(R.id.refreshButton);
        c1 = findViewById(R.id.courseCode1);
        t1 = findViewById(R.id.tutorial1);
        m1 = findViewById(R.id.marks1);
        c2 = findViewById(R.id.courseCode2);
        t2 = findViewById(R.id.tutorial2);
        m2 = findViewById(R.id.marks2);
        c3 = findViewById(R.id.courseCode3);
        t3 = findViewById(R.id.tutorial3);
        m3 = findViewById(R.id.marks3);
        c4 = findViewById(R.id.courseCode4);
        t4 = findViewById(R.id.tutorial4);
        m4 = findViewById(R.id.marks4);
        c5 = findViewById(R.id.courseCode5);
        t5 = findViewById(R.id.tutorial5);
        m5 = findViewById(R.id.marks5);
        c6 = findViewById(R.id.courseCode6);
        t6 = findViewById(R.id.tutorial6);
        m6 = findViewById(R.id.marks6);
        c7 = findViewById(R.id.courseCode7);
        t7 = findViewById(R.id.tutorial7);
        m7 = findViewById(R.id.marks7);
        c8 = findViewById(R.id.courseCode8);
        t8 = findViewById(R.id.tutorial8);
        m8 = findViewById(R.id.marks8);
        c9 = findViewById(R.id.courseCode9);
        t9 = findViewById(R.id.tutorial9);
        m9 = findViewById(R.id.marks9);
        c10 = findViewById(R.id.courseCode10);
        t10 = findViewById(R.id.tutorial10);
        m10 = findViewById(R.id.marks10);

        displayMarks();
        updateTable();
        refreshTable();

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

    public void refreshTable(){
        refreshButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayMarks();
                    }
                }
        );

    }


    public void displayMarks(){
        Cursor result = myDB.getAllDataTutorial("Tutorial");

            int raw = 1;
            while (result.moveToNext()) {

                String courseCode = result.getString(0);
                String tutorial = result.getString(1);
                String mark = result.getString(2);

                switch (raw) {
                    case 1:
                        c1.setText(courseCode);
                        t1.setText(tutorial);
                        m1.setText(mark);
                        break;
                    case 2:
                        c2.setText(courseCode);
                        t2.setText(tutorial);
                        m2.setText(mark);
                        break;
                    case 3:
                        c3.setText(courseCode);
                        t3.setText(tutorial);
                        m3.setText(mark);
                        break;
                    case 4:
                        c4.setText(courseCode);
                        t4.setText(tutorial);
                        m4.setText(mark);
                        break;
                    case 5:
                        c5.setText(courseCode);
                        t5.setText(tutorial);
                        m5.setText(mark);
                        break;
                    case 6:
                        c6.setText(courseCode);
                        t6.setText(tutorial);
                        m6.setText(mark);
                        break;
                    case 7:
                        c7.setText(courseCode);
                        t7.setText(tutorial);
                        m7.setText(mark);
                        break;
                    case 8:
                        c8.setText(courseCode);
                        t8.setText(tutorial);
                        m8.setText(mark);
                        break;
                    case 9:
                        c9.setText(courseCode);
                        t9.setText(tutorial);
                        m9.setText(mark);
                        break;
                    case 10:
                        c10.setText(courseCode);
                        t10.setText(tutorial);
                        m10.setText(mark);
                        break;
                    default:
                        break;
                }
                raw++;
            }
        result.close();
    }
}
