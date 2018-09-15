package com.example.vjfernandez.memento.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vjfernandez.memento.R;
import com.example.vjfernandez.memento.helpers.InputValidation;
import com.example.vjfernandez.memento.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDb;
    InputValidation inputValidation;
    EditText ed1,ed2,ed3,ed4;
    Button bt1,bt2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);
        inputValidation = new InputValidation(this);

        ed1 = (EditText)findViewById(R.id.editText1);
        ed2 = (EditText)findViewById(R.id.editText2);
        ed3 = (EditText)findViewById(R.id.editText3);
        ed4 = (EditText)findViewById(R.id.editText4);
        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);
        tv = (TextView)findViewById(R.id.textView);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                storeData();
                break;
            case R.id.textView:
                finish();
                break;
            case R.id.button2:
                emptyInputEditText();
                myDb.resetTable();
        }
    }

    private void storeData() {
        if (!inputValidation.isInputEditTextFilled(ed1)) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(ed2)) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(ed3)) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(ed3, ed4)) {
            return;
        }

        if (!myDb.checkUser(ed1.getText().toString())) {

            if(myDb.insertData(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString())){
                Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_LONG).show();
                finish();
            }
            else
                Toast.makeText(RegisterActivity.this,"Login is not Success",Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(RegisterActivity.this,"User Already Exist!",Toast.LENGTH_LONG).show();


    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        ed1.setText(null);
        ed2.setText(null);
        ed3.setText(null);
        ed4.setText(null);
    }
}