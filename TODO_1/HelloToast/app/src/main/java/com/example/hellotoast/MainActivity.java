package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.btn1);

    }
    public void count(View view){
        count++;
        tv.setText(String.valueOf(count));
    }
    public void toast(View view){
        Toast.makeText(this, "Hello Toast", Toast.LENGTH_SHORT).show();

    }
}