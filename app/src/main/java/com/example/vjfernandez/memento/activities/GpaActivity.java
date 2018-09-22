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

public class GpaActivity extends AppCompatActivity {

    Button updateButton,refreshButton;
    TextView c1,t1,m1,c2,t2,m2,c3,t3,m3,c4,t4,m4,c5,t5,m5,c6,t6,m6,c7,t7,m7,c8,t8,m8,finalGpa,finalGpaValue;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        myDB= new DatabaseHelper(this);

        updateButton = findViewById(R.id.updateGpaButton);
        refreshButton=findViewById(R.id.refreshGpaButton);
        c1 = findViewById(R.id.year1_1);
        t1 = findViewById(R.id.semester1);
        m1 = findViewById(R.id.gpa1);
        c2 = findViewById(R.id.year1_2);
        t2 = findViewById(R.id.semester2);
        m2 = findViewById(R.id.gpa2);
        c3 = findViewById(R.id.year2_1);
        t3 = findViewById(R.id.semester3);
        m3 = findViewById(R.id.gpa3);
        c4 = findViewById(R.id.year2_2);
        t4 = findViewById(R.id.semester4);
        m4 = findViewById(R.id.gpa4);
        c5 = findViewById(R.id.year3_1);
        t5 = findViewById(R.id.semester5);
        m5 = findViewById(R.id.gpa5);
        c6 = findViewById(R.id.year3_2);
        t6 = findViewById(R.id.semester6);
        m6 = findViewById(R.id.gpa6);
        c7 = findViewById(R.id.year4_1);
        t7 = findViewById(R.id.semester7);
        m7 = findViewById(R.id.gpa7);
        c8 = findViewById(R.id.year4_2);
        t8 = findViewById(R.id.semester8);
        m8 = findViewById(R.id.gpa8);
        finalGpa=findViewById(R.id.finalGpaTextView);
        finalGpaValue=findViewById(R.id.finalGpaValueTextView);

        displayGpa();
        updateTable();
        refreshTable();

    }

    public void displayGpa(){
        Cursor result = myDB.getAllDataTutorial("Results");

       /* int raw = 1;
        while (result.moveToNext()) {

            String year = result.getString(1);
            String semester = result.getString(2);

            switch (raw) {
                case 1:
                    c1.setText(year);
                    t1.setText(semester);
                    break;
                case 2:
                    c2.setText(year);
                    t2.setText(semester);
                    break;
                case 3:
                    c3.setText(year);
                    t3.setText(semester);
                    break;
                case 4:
                    c4.setText(year);
                    t4.setText(semester);
                    break;
                case 5:
                    c5.setText(year);
                    t5.setText(semester);
                    break;
                case 6:
                    c6.setText(year);
                    t6.setText(semester);
                    break;
                case 7:
                    c7.setText(year);
                    t7.setText(semester);
                    break;
                case 8:
                    c8.setText(year);
                    t8.setText(semester);
                    break;
                default:
                    break;
            }
             raw++;
        }*/

        float totalMark=0;
        float totalCredit=0;
        while(result.moveToNext()) {
            String mark = result.getString(3);
            String courseCode = result.getString(0);
            char lastIndex = courseCode.charAt(9);
            int credit = Integer.parseInt(String.valueOf(lastIndex));
            switch (mark) {
                case "A+":
                    totalMark += 4 * credit;
                    totalCredit += credit;
                case "A":
                    totalMark += 4 * credit;
                    totalCredit += credit;
                case "A-":
                    totalMark += 3.7 * credit;
                    totalCredit += credit;
                case "B+":
                    totalMark += 3.3 * credit;
                    totalCredit += credit;
                case "B":
                    totalMark += 3.0 * credit;
                    totalCredit += credit;
                case "B-":
                    totalMark += 2.7 * credit;
                    totalCredit += credit;
                case "C+":
                    totalMark += 2.3 * credit;
                    totalCredit += credit;
                case "C":
                    totalMark += 2.0 * credit;
                    totalCredit += credit;
                case "C-":
                    totalMark += 1.7 * credit;
                    totalCredit += credit;
                case "D+":
                    totalMark += 1.3 * credit;
                    totalCredit += credit;
                case "D":
                    totalMark += 1.0 * credit;
                    totalCredit += credit;
                case "E":
                    totalMark += 0;
                    totalCredit += credit;
            }
        }

        float finalGpa=totalMark/totalCredit;
        String finalGpaString=Float.toString(finalGpa);
        finalGpaValue.setText(finalGpaString);

        result.close();
    }

    public void refreshTable(){
        refreshButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        displayGpa();
                    }
                }
        );

    }

    public void updateTable(){
        updateButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),GpaUpdateActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }
}
