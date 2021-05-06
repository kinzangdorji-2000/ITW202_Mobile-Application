package com.example.informationdesk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    TextView btn;

    private EditText username, password, confirmpassword, email;
    Button Btn;
    private FirebaseAuth mAuth;
    private ProgressDialog MLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn = findViewById(R.id.textView2);
        username = findViewById(R.id.editTextTextPersonName);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextTextPersonName2);
        confirmpassword = findViewById(R.id.editTextTextPersonName3);
        Btn = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();
        MLoadingBar = new ProgressDialog(Register.this);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                CheckCredentials();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }

    private void CheckCredentials() {
        String Username = username.getText().toString();
        String Email = email.getText().toString();
        String Password = password.getText().toString();
        String ConfirmPassword = confirmpassword.getText().toString();

        if(Username.isEmpty() || Username.length()<7){
            showError(username, "Your Username is not valid!");
        }
        else if(Email.isEmpty() || !Email.contains("@")){
            showError(email, "Your Email is not valid!");
        }
        else if(Password.isEmpty() || Password.length()<6){
            showError(password, "Password must be 6 characters!");
        }
        else  if(ConfirmPassword.isEmpty() || !ConfirmPassword.equals(confirmpassword)){
            showError(confirmpassword, "Password does not match");
        }
        else {
            MLoadingBar.setTitle("Registration");
            MLoadingBar.setMessage("Please wait while check your credentials");
            MLoadingBar.setCanceledOnTouchOutside(false);
            MLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();

                        MLoadingBar.dismiss();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Register.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
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