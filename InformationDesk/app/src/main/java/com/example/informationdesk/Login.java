package com.example.informationdesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView btn;
    EditText Email, password;
    Button Btn;
    private FirebaseAuth mAuth;
    private ProgressDialog MLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.signup);
        Email = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName2);
        Btn = findViewById(R.id.button2);
        mAuth = FirebaseAuth.getInstance();
        MLoadingBar = new ProgressDialog(Login.this);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckCredentials();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
    }

    private void CheckCredentials() {
        String email = Email.getText().toString();
        String Password = password.getText().toString();

        if(email.isEmpty() || email.contains("@")){
            showError(Email, "Your Email is not valid!");
        }
        else if(Password.isEmpty() || Password.length()<6){
            showError(password, "Password must be 6 characters!");
        }
        else {
            MLoadingBar.setTitle("Login");
            MLoadingBar.setMessage("Please wait while check your credentials");
            MLoadingBar.setCanceledOnTouchOutside(false);
            MLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                        MLoadingBar.dismiss();
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            });
        }

    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}