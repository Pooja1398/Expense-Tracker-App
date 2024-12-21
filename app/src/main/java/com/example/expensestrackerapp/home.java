package com.example.expensestrackerapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class home extends AppCompatActivity {

    private Button buttonAddExpense;
    private Button incomeButton;
    private Button expensesButton;
    private Button buttonSummaryChart;
    private Button buttonBudgetTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons
        buttonAddExpense = findViewById(R.id.button_add_expense);
        incomeButton = findViewById(R.id.button_income);
        expensesButton = findViewById(R.id.button_expenses);
        buttonSummaryChart = findViewById(R.id.button_summary_chart);
        buttonBudgetTracking = findViewById(R.id.button_budget_tracking);

        // Set onClick listeners for each button
        buttonAddExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add expenses button click
                Intent intent=new Intent(home.this, addexpense.class);
                startActivity(intent);
            }
        });

        incomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show dialog for adding income
                showIncomeDialog();
            }
        });

        expensesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open expenses activity

            }
        });

        buttonSummaryChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to summary chart
                Intent intent = new Intent(home.this, chart.class);
                startActivity(intent);
            }
        });

        buttonBudgetTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to budget tracking
                Intent intent = new Intent(home.this, budgettrack.class);
                startActivity(intent);

            }
        });
    }

    private void showIncomeDialog() {
        // Create a custom dialog layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_income, null);
        builder.setView(dialogView);

        // Get reference to EditText in dialog for income input
        EditText incomeEditText = dialogView.findViewById(R.id.edit_text_income);

        builder.setTitle("Add Income")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String incomeAmount = incomeEditText.getText().toString();
                        if (!incomeAmount.isEmpty()) {
                            // Handle the income input (e.g., save or display it)
                            Toast.makeText(home.this, "Income added: $" + incomeAmount, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(home.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // Show the dialog
        builder.create().show();
    }

}
