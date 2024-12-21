package com.example.expensestrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class budgettrack extends AppCompatActivity {

    Databasehelper mydb;
    Button[] buttons;
    Button savebudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgettrack);

        // Initialize database helper
        mydb = new Databasehelper(this);

        // Initialize buttons
        buttons = new Button[]{
                findViewById(R.id.chb1),
                findViewById(R.id.chb2),
                findViewById(R.id.chb3),
                findViewById(R.id.chb4),
                findViewById(R.id.chb5),
                findViewById(R.id.chb6),
                findViewById(R.id.chb7),
                findViewById(R.id.chb8),
                findViewById(R.id.chb9),
                findViewById(R.id.chb10),
                findViewById(R.id.chb11),
                findViewById(R.id.chb12)
        };
        savebudget = findViewById(R.id.savebudget);

        // Set click listeners for all buttons
        for (Button button : buttons) {
            setButtonClickListener(button);
        }
    }

    private void setButtonClickListener(Button button) {
        button.setOnClickListener(v -> {
            showBudgetDialog(button);
        });
    }

    private void showBudgetDialog(Button button) {
        // Create a custom dialog layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(dialogView);

        // Get reference to EditText in dialog for budget input
        EditText budgetInput = dialogView.findViewById(R.id.editTextbudget);

        builder.setTitle("Add Budget")
                .setPositiveButton("Add", (dialog, which) -> {
                    String input = budgetInput.getText().toString();
                    if (!input.isEmpty()) {
                        try {
                            int budgetAmount = Integer.parseInt(input);
                            button.setText(String.valueOf(budgetAmount));
                            mydb.insertBudgetData(button.getId(), budgetAmount);
                            Toast.makeText(budgettrack.this, "Budget Added: " + budgetAmount, Toast.LENGTH_SHORT).show();
                        } catch (NumberFormatException e) {
                            Toast.makeText(budgettrack.this, "Invalid input. Enter a number.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(budgettrack.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());

        // Show the dialog
        builder.create().show();

        //save budget and navigate to home
        savebudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBudgetsAndNavigate();
                Intent intent = new Intent(budgettrack.this, home.class);
                startActivity(intent);

            }
        });

    }

    // Example method to save data for all buttons and navigate
    public void saveBudgetsAndNavigate() {
        savebudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydb.insertBudgetData(buttons[0].getId(), Integer.parseInt(buttons[0].getText().toString()));
                Toast.makeText(budgettrack.this, "Budgets Saved Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(budgettrack.this, home.class));
            }
        });
    }
}
