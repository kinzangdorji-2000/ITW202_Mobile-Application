package com.example.todo_14_iii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BTN = findViewById(R.id.button);
        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment date = new DatePickerFragment();
                date.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    public void processDatePickerResult(int year, int month, int dayOfMonth) {
        String Month = Integer.toString(month+1);
        String Day = Integer.toString(dayOfMonth);
        String Year = Integer.toString(year);

        String date_message = (Month + "/" + Day + "/" + Year);
        Toast.makeText(this, "Date"+date_message, Toast.LENGTH_SHORT).show();

    }
}