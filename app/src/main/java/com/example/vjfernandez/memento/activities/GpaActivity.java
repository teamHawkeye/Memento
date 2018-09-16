package com.example.vjfernandez.memento.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vjfernandez.memento.R;

public class GpaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        textView1=findViewById(R.id.yearTextView);
        textView2=findViewById(R.id.firstSemesterTextView);
        textView3=findViewById(R.id.firstGpaTextView);
        textView4=findViewById(R.id.secondSemesterTextView);
        textView5=findViewById(R.id.secondGpaTextView);
        textView6=findViewById(R.id.finalGpaTextView);
        textView7=findViewById(R.id.finalGpaValueTextView);
        button=findViewById(R.id.markUpdateButton);

        button.setOnClickListener(this);

        viewGpa();

    }

    @Override
    public void onClick(View v) {
        v.getId();

    }

    public void viewGpa(){

    }
}
