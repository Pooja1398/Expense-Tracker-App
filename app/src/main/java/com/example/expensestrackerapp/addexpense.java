package com.example.expensestrackerapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class addexpense extends AppCompatActivity {

    private Databasehelper mydb; // Database helper
    private EditText expenseAmount, expenseNotes;
    private Spinner categorySpinner;
    private TextView textViewDate;
    private Button buttonPickDate, saveExpenseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpense); // Ensure layout name is correct

        // Initialize UI elements
        expenseAmount = findViewById(R.id.expense_amount);
        expenseNotes = findViewById(R.id.expense_notes);
        categorySpinner = findViewById(R.id.category_spinner);
        textViewDate = findViewById(R.id.textViewDate);
        buttonPickDate = findViewById(R.id.buttonPickDate);
        saveExpenseButton = findViewById(R.id.save_expense_button);

        // Initialize the database helper
        mydb = new Databasehelper(this);

        // Set up the Spinner
        setupCategorySpinner();

        // Set up date picker
        setupDatePicker();

        // Set up save button functionality
        setupSaveButton();
    }

    private void setupCategorySpinner() {
        // Populate spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Optional: Add listener to handle item selection
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle selection if needed
                String selectedCategory = (String) parent.getItemAtPosition(position);
                Toast.makeText(addexpense.this, "Selected: " + selectedCategory, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    private void setupDatePicker() {
        buttonPickDate.setOnClickListener(v -> {
            // Get the current date
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Open a DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    addexpense.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Update the date TextView with the selected date
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        textViewDate.setText(selectedDate);
                    },
                    year, month, day
            );
            datePickerDialog.show();
        });
    }

    private void setupSaveButton() {
        saveExpenseButton.setOnClickListener(v -> {
            // Get input values
            String amount = expenseAmount.getText().toString();
            String category = categorySpinner.getSelectedItem().toString();
            String notes = expenseNotes.getText().toString();
            String date = textViewDate.getText().toString();
            // Insert data into the database
            mydb.insertExpenseData(amount, category, notes, date);
                Toast.makeText(addexpense.this, "Expense saved successfully!", Toast.LENGTH_SHORT).show();
                // Navigate back to the home screen
                Intent intent = new Intent(addexpense.this, home.class);
                startActivity(intent);
                finish();
        });
    }
}
