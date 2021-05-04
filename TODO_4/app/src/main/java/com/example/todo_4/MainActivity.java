package com.example.todo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.todo_4.MESSAGE";
    private static  final int TEXT_REQUEST= 1;
    private EditText editText;
    private TextView Tv1;
    private  TextView Tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextTextPersonName);
        Tv1 = findViewById(R.id.textView3);
        Tv2 = findViewById(R.id.textView4);
    }

    public void send(View view) {
        Intent obj = new Intent(this,MainActivity2.class);
        String message = editText.getText().toString();
        obj.putExtra(EXTRA_MESSAGE,message);
        startActivityForResult(obj, TEXT_REQUEST);

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent obj){
        super.onActivityResult(requestCode, resultCode, obj);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String message = obj.getStringExtra(MainActivity2.EXTRA_REPLY);
                Tv1.setVisibility(View.VISIBLE);
                Tv2.setText(message);
                Tv2.setVisibility(View.VISIBLE);
            }
        }
    }
}