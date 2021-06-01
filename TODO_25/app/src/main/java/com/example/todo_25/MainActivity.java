package com.example.todo_25;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    private EditText First_Name, Last_Name, ITW202_Marks, Enrollment_Number;
    private Button BTN_1, BTN_2, BTN_3, BTN_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        First_Name = (EditText)findViewById(R.id.editText1);
        Last_Name = (EditText)findViewById(R.id.editText2);
        ITW202_Marks = (EditText)findViewById(R.id.editText3);
        Enrollment_Number = (EditText)findViewById(R.id.editText4);
        BTN_1 = (Button)findViewById(R.id.button1);
        BTN_2 = (Button)findViewById(R.id.button2);
        BTN_3 = (Button)findViewById(R.id.button3);
        BTN_4 = (Button)findViewById(R.id.button4);
        myDB = new DatabaseHelper(this);


    }

    public void addData(View view) {
        boolean isInserted = myDB.insertData(Enrollment_Number.getText().toString(),
                First_Name.getText().toString(),
                Last_Name.getText().toString(),
                ITW202_Marks.getText().toString());

        if (isInserted == true)
            Toast.makeText(this, "Data Inserted", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "Data Not Inserted", Toast.LENGTH_SHORT).show();


    }

    public void viewData(View view) {
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0){
            showMessage("Error", "Nothing Found!");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Student Id :" +res.getString(0)+"\n");
            buffer.append("First Name :" +res.getString(1)+"\n");
            buffer.append("Last Name :" +res.getString(2)+"\n");
            buffer.append("ITW202 Marks :" +res.getString(3)+"\n\n");
        }

        showMessage("List of Students", buffer.toString());
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public void updateData(View view) {
        boolean isUpdate = myDB.updateData(Enrollment_Number.getText().toString(),
                First_Name.getText().toString(),
                Last_Name.getText().toString(),
                ITW202_Marks.getText().toString());

        if (isUpdate == true)
            Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show();
    }

    public void deleteData(View view) {
        Integer deleteRow = myDB.deleteData(Enrollment_Number.getText().toString());

        if (deleteRow > 0)
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
    }
}