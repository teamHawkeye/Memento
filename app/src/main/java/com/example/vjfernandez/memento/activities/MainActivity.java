package com.example.vjfernandez.memento.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vjfernandez.memento.sql.DatabaseHelper;
import com.example.vjfernandez.memento.R;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText ed1,ed3;
    Button bt1;
    TextView tv;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        ed1=(EditText)findViewById(R.id.editText1);
        ed3=(EditText)findViewById(R.id.editText3);
        bt1=(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView);

        login();
        createAccount();
        myDb.close();
    }


    public void login(){
        bt1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String enteredStuNo = ed1.getText().toString();
                        String enteredPassword = ed3.getText().toString();
                        if(myDb.getData(enteredStuNo,enteredPassword)){
                            getName();
                            Intent intent = new Intent(getApplicationContext(),MenuActivity.class);
                            startActivity(intent);
                        }
                        else
                            Toast.makeText(MainActivity.this,"Login is not Success",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void createAccount(){
        tv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),RegisterActivity.class);
                        startActivity(intent);
                    }
                }
        );
    }

    public void getName(){
        String stuNo=ed1.getText().toString();
        name =myDb.getStudentName(stuNo);
    }
}
