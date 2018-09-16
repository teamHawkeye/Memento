package com.example.vjfernandez.memento.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vjfernandez.memento.R;
import com.example.vjfernandez.memento.helpers.InputValidation;
import com.example.vjfernandez.memento.sql.DatabaseHelper;

public class TutorialUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDb;
    InputValidation inputValidation;
    EditText et1,et2,et3;
    Button bt1,bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_update);

        myDb = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

        et1 = (EditText)findViewById(R.id.courseEditText);
        et2 = (EditText)findViewById(R.id.tutorialEditText);
        et3 = (EditText)findViewById(R.id.markEditText);
        bt1 = (Button)findViewById(R.id.resetButton);
        bt2 = (Button)findViewById(R.id.okButton);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.okButton:
                storeData();
                break;
            case R.id.resetButton:
                emptyInputEditText();
                myDb.resetTable("Tutorial");
        }
    }

    private void storeData() {
        if (!inputValidation.isInputEditTextFilled(et1)) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(et2)) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(et3)) {
            return;
        }
        if (!myDb.checkTutorial(et1.getText().toString(),et2.getText().toString())) {
            if(myDb.insertDataTutorial(et1.getText().toString(),et2.getText().toString(),et3.getText().toString())){
                finish();
            }
            else
                Toast.makeText(TutorialUpdateActivity.this,"Adding is not Success",Toast.LENGTH_LONG).show();        }
        else
            Toast.makeText(TutorialUpdateActivity.this,"Tutorial Already Exist!",Toast.LENGTH_LONG).show();
    }

    private void emptyInputEditText() {
        et1.setText(null);
        et2.setText(null);
        et3.setText(null);
    }
}
