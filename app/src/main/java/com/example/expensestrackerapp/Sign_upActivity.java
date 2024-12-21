package com.example.expensestrackerapp;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class Sign_upActivity extends AppCompatActivity {

    Databasehelper mydb;
    private EditText nameEditText, mobileEditText, emailEditText;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up); // Ensure your XML file name matches

        // Initialize UI components
        CircleImageView profileImage = findViewById(R.id.profileImage);
        nameEditText = findViewById(R.id.nameedit); // Assign correct IDs here
        mobileEditText = findViewById(R.id.mobileedit);
        emailEditText = findViewById(R.id.emailedit);
        registerButton = findViewById(R.id.register);
        Button facebookButton = findViewById(R.id.fb);
        Button googleButton = findViewById(R.id.google);
        Adddata();


        //Calling constructor to create database
        mydb = new Databasehelper(this);


        // Set click listener for profile image
        profileImage.setOnClickListener(v -> {
            // Code to upload or select an image (implement your own logic)
            Toast.makeText(Sign_upActivity.this, "Upload Image Clicked", Toast.LENGTH_SHORT).show();
        });

        // Set click listener for Facebook button
        facebookButton.setOnClickListener(v -> {
            // Implement Facebook registration logic

            FBButton();
        });

        // Set click listener for Google button
        googleButton.setOnClickListener(v -> {
            // Implement Google registration logic

            GoogleBtn();
        });
    }

    //set click listener for register button
    public void Adddata(){
        registerButton.setOnClickListener(v -> {
            mydb.insertSignUpData(nameEditText.getText().toString(),
                    mobileEditText.getText().toString(),
                    emailEditText.getText().toString());
            Toast.makeText(Sign_upActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(Sign_upActivity.this, home.class);
            startActivity(intent);
        });
    }

    private void GoogleBtn() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www/google.com"));
        startActivity(intent);
    }

    private void FBButton() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
        startActivity(intent);
    }
}
