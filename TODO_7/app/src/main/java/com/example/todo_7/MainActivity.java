package com.example.todo_7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int count;
    private TextView Tview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tview = (TextView) findViewById(R.id.textView2);
        if(savedInstanceState != null){
            count = savedInstanceState.getInt("COUNT");
            Tview.setText(String.valueOf(count));
        }
    }

    public void count(View view) {
        count++;
        Tview.setText(String.valueOf(count));
    }


}