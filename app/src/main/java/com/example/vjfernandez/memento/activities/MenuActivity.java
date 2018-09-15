package com.example.vjfernandez.memento.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vjfernandez.memento.R;

public class MenuActivity extends AppCompatActivity {

    Button bt1,bt2,bt3,bt4;
    TextView tv1;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt1 = (Button)findViewById(R.id.button1);
        bt2 = (Button)findViewById(R.id.button2);
        bt3 = (Button)findViewById(R.id.button3);
        bt4 = (Button)findViewById(R.id.button4);
        tv1 = (TextView)findViewById(R.id.textView1);
        iv = (ImageView)findViewById(R.id.imageView);

        greetingText();
    }

    public void greetingText(){
        //String studentName = MainActivity.getName();
        tv1.append("Hi "+MainActivity.name+", Welcome to Memento!");
    }
}
