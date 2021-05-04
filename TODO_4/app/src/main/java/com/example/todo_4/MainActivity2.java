package com.example.todo_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    public static final String EXTRA_REPLY ="com.example.todo_4.reply";
    private TextView textView;
    private  EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextTextPersonName2);

        Intent obj = getIntent();
        String msg = obj.getStringExtra(MainActivity.EXTRA_MESSAGE);
        textView.setText(msg);
    }

    public void reply(View view) {
        String message = editText.getText().toString();
        Intent obj1 = new Intent();
        obj1.putExtra(EXTRA_REPLY, message);
        setResult(RESULT_OK, obj1);
        finish();
    }
}