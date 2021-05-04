package com.example.todo_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mEdit1, mEdit2;
    private Button btn_add, btn_sub, btn_mul, btn_div;
    private TextView mResult;
    private Calculator mcalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEdit1 = findViewById(R.id.editTextTextPersonName);
        mEdit2 = findViewById(R.id.editTextTextPersonName2);
        btn_add = findViewById(R.id.button);
        btn_sub = findViewById(R.id.button2);
        btn_mul = findViewById(R.id.button3);
        btn_div = findViewById(R.id.button4);
        mResult = findViewById(R.id.textView);

        //creating an object
        mcalculator = new Calculator();

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = mEdit1.getText().toString();
                String num2 = mEdit2.getText().toString();
                double Result = mcalculator.add(Double.valueOf(num1), Double.valueOf(num2));
                mResult.setText(String.valueOf(Result));
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = mEdit1.getText().toString();
                String num2 = mEdit2.getText().toString();
                double Result = mcalculator.sub(Double.valueOf(num1), Double.valueOf(num2));
                mResult.setText(String.valueOf(Result));
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = mEdit1.getText().toString();
                String num2 = mEdit2.getText().toString();
                double Result = mcalculator.mul(Double.valueOf(num1), Double.valueOf(num2));
                mResult.setText(String.valueOf(Result));
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num1 = mEdit1.getText().toString();
                String num2 = mEdit2.getText().toString();
                double Result = mcalculator.div(Double.valueOf(num1), Double.valueOf(num2));
                mResult.setText(String.valueOf(Result));
            }
        });
    }

}