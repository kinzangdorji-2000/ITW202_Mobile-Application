package com.example.informationdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button BTN;
    private FirebaseAuth Mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTN = findViewById(R.id.BUTTON);
        Mauth = FirebaseAuth.getInstance();

        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mauth.signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
    }
}