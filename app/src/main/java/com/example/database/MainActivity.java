package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, dob;
    Button insert, delete, view, update;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        update = findViewById(R.id.btnUpdate);

        dbHelper = new DBHelper(this, "mydatabase");

        insertClickListener();
        updateClickListener();
        deleteClickListener();
        viewClickListener();
    }

    private void viewClickListener() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c  = dbHelper.getUserData();
                if (c.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "No Entry Exist", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer string_buffer  = new StringBuffer();
                while (c.moveToNext()){
                    string_buffer.append("Name: "+c.getString(0)+"\n");
                    string_buffer.append("Contact: "+c.getString(1)+"\n");
                    string_buffer.append("Date of Birth: "+c.getString(2)+"\n\n");
                }
                new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(true)
                        .setTitle("User Entry")
                        .setMessage(string_buffer.toString())
                        .show();
            }
        });
    }

    private void deleteClickListener() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_txt = name.getText().toString();


                boolean check_delette_data = dbHelper.deleteUserData(name_txt);
                if (check_delette_data) Toast.makeText(MainActivity.this, "Delete Successful",
                        Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this, "Delete Not Successful", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void updateClickListener() {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_txt = name.getText().toString();
                String contact_txt = contact.getText().toString();
                String dob_txt = dob.getText().toString();

                boolean check_update_data = dbHelper.updateUserData(name_txt, contact_txt, dob_txt);
                if (check_update_data) Toast.makeText(MainActivity.this, "Update Successful",
                        Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this, "Update Not Successful", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void insertClickListener() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_txt = name.getText().toString();
                String contact_txt = contact.getText().toString();
                String dob_txt = dob.getText().toString();

                boolean check_insert_data = dbHelper.insertUserData(name_txt, contact_txt, dob_txt);
                if (check_insert_data) Toast.makeText(MainActivity.this, "Insert Successful",
                        Toast.LENGTH_LONG).show();
                else Toast.makeText(MainActivity.this, "Insert Not Successful", Toast.LENGTH_LONG).show();

            }
        });
    }
}