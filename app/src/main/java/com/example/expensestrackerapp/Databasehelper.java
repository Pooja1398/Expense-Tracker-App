package com.example.expensestrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Databasehelper extends SQLiteOpenHelper {

    // Database and Table names
    public static final String DATABASE_NAME = "Customer.db";
    public static final int DATABASE_VERSION = 2; // Incremented version
    public static final String TABLE_NAME_SIGNUP = "Customer_table";
    public static final String TABLE_NAME_LOGIN = "Login_table";
    public static final String TABLE_NAME_EXPENSE = "EXPENSE_TABLE";
    public static final String TABLE_NAME_BUDGET = "BUDGET_TABLE";

    // Columns for Sign-Up table
    public static final String COL1 = "ID";
    public static final String COL2 = "Name";
    public static final String COL3 = "Email";
    public static final String COL4 = "Password";

    // Columns for Login table
    public static final String LOG_ID = "ID";
    public static final String LOG_NAME = "log_name"; // Changed for clarity
    public static final String LOG_PASSWORD = "log_password"; // Changed for clarity

    //Columns for expense table
    public static final String EXP_ID = "ID";
    public static final String EXP_AMOUNT = "AMOUNT";
    public static final String EXP_CATEGORY = "CATEGORY";
    public static final String EXP_NOTES = "NOTES" ;
    public static final String EXP_DATE = "DATE";

    //Columns for budget tracking table
    public static final String BUDGET_ID = "ID";
    public static final String BUDGET_AMOUNT = "AMOUNT";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // Ensure version is incremented
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            // Create Sign-Up table
            String createSignupTable = "CREATE TABLE " + TABLE_NAME_SIGNUP + "(" +
                    COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL2 + " TEXT, " +
                    COL3 + " TEXT, " +
                    COL4 + " TEXT)";
            db.execSQL(createSignupTable);
            Log.d("Database", "Sign-Up table created successfully");

            // Create Login table
            String createLoginTable = "CREATE TABLE " + TABLE_NAME_LOGIN + "(" +
                    LOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOG_NAME + " TEXT, " +
                    LOG_PASSWORD + " TEXT)";
            db.execSQL(createLoginTable);
            Log.d("Database", "Login table created successfully");

            //Create expense table
            String createExpenseTable = "CREATE TABLE " + TABLE_NAME_EXPENSE + "(" +
                    EXP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EXP_AMOUNT + " TEXT, " +
                    EXP_CATEGORY + " TEXT, " +
                    EXP_NOTES + " TEXT, " +
                    EXP_DATE + " TEXT)" ;
            db.execSQL(createExpenseTable);
            Log.d("Database", "Expense table created successfully");

            //create budget track table
            String createBudgetTable = "CREATE TABLE " + TABLE_NAME_BUDGET + "(" +
                    BUDGET_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    BUDGET_AMOUNT + "INTEGER)" ;
            db.execSQL(createBudgetTable);
            Log.d("Database", "Budget table create successfully");

        } catch (Exception e) {
            Log.e("Database Error", "Error creating tables: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SIGNUP);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_LOGIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXPENSE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BUDGET);
            onCreate(db);
            Log.d("Database", "Database upgraded successfully");
        } catch (Exception e) {
            Log.e("Database Error", "Error upgrading database: " + e.getMessage());
        }
    }

    // Insert data into Sign-Up table
    public void insertSignUpData(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, name);
        cv.put(COL3, email);
        cv.put(COL4, password);
        db.insert(TABLE_NAME_SIGNUP, null, cv);
    }

    // Insert data into Login table
    public void insertLoginData(String logname, String logpassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LOG_NAME, logname);
        cv.put(LOG_PASSWORD, logpassword);
        db.insert(TABLE_NAME_LOGIN, null, cv);
    }

    //Insert data into expense table
    public boolean insertExpenseData(String amount, String category, String notes, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(EXP_AMOUNT, amount);
        cv.put(EXP_CATEGORY, category);
        cv.put(EXP_NOTES, notes);
        cv.put(EXP_DATE, date);
        db.insert(TABLE_NAME_EXPENSE, null, cv);
        return false;
    }

    //Insert data into budgettracking
    public void insertBudgetData(int id, int amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(BUDGET_ID, id);
        cv.put(BUDGET_AMOUNT, amount);
        db.insert(TABLE_NAME_BUDGET, null, cv);
    }


}
