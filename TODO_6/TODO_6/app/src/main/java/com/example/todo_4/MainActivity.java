package com.example.todo_4; 

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.todo_4.MESSAGE";
    private static  final int TEXT_REQUEST= 1;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private EditText editText;
    private TextView Tv1;
    private  TextView Tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate");

        editText = findViewById(R.id.editTextTextPersonName);
        Tv1 = findViewById(R.id.textView3);
        Tv2 = findViewById(R.id.textView4);
    }

    @Override
    public  void onStart(){
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(LOG_TAG,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG,"onPause");
    }
     @Override
     public void onStop(){
        super.onStop();
         Log.d(LOG_TAG,"onStop");
     }

     @Override
     public void onDestroy(){
        super.onDestroy();
         Log.d(LOG_TAG,"onDestroy");
     }

     @Override
     public void onRestart(){
        super.onRestart();
         Log.d(LOG_TAG,"onRestart");
     }


    public void send(View view) {
        Log.d(LOG_TAG, "Button Clicked");
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(Tv1.getVisibility() == View.VISIBLE){
            outState.putBoolean("Reply Received", true);
            outState.putString("Message Received", Tv2.getText().toString());
        }

    }
}
