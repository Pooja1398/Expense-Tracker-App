package com.example.expensestrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class logged extends AppCompatActivity {

    Databasehelper mydb;
    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged); // Ensure the XML file matches

        // Initialize UI components
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        signUpLink = findViewById(R.id.signUpLink);

        //database helper
        mydb = new Databasehelper(this);
        addLoginData();

        // Set click listener for the sign-up link
        signUpLink.setOnClickListener(v -> {
            // Redirect to Sign-Up activity
            Intent intent = new Intent(logged.this, home.class);
            startActivity(intent);
        });
    }
    // Method to add login data to the database
    private void addLoginData() {
        loginButton.setOnClickListener(v -> {
            mydb.insertLoginData(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString());

            Toast.makeText(logged.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(logged.this, home.class);
            startActivity(intent);
        });
    }
}
