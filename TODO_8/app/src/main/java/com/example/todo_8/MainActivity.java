package com.example.todo_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editweb, editlocation, edittext;
    private Button btnWeb, btnLocation, btntxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editweb = findViewById(R.id.editText_web);
        editlocation = findViewById(R.id.editText_location);
        edittext = findViewById(R.id.editText_text);

    }
    public void SendWeb(View view) {
        String txt = editweb.getText().toString();
        Uri url = Uri.parse(txt);
        Intent obj = new Intent(Intent.ACTION_VIEW, url);

        if(obj.resolveActivity(getPackageManager()) != null){
            startActivity(obj);
        }
        else{
            Log.d("Implicit Intents", "Error Message");
        }
    }
    public void SendLocation(View view) {
        String txt = editlocation.getText().toString();
        Uri url = Uri.parse("geo:0,0?q=" +txt);
        Intent obj = new Intent(Intent.ACTION_VIEW, url);

        if(obj.resolveActivity(getPackageManager()) != null){
            startActivity(obj);
        }
        else{
            Log.d("Implicit Intents", "Error Message");
        }
    }
    public void ShareTxt(View view) {
        String txt = edittext.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this txt with: ")
                .setText(txt)
                .startChooser();
    }
}