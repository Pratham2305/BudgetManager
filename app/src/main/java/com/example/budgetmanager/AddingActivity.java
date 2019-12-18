package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class AddingActivity extends AppCompatActivity {

    Button btexpense;
    Button btincome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);

        btexpense = findViewById(R.id.btexpense);

        btexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddingActivity.this,com.example.budgetmanager.ExpenseCategory.class);
                startActivity(intent);
            }
        });


        btincome = findViewById(R.id.btincome);

        btincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddingActivity.this,com.example.budgetmanager.IncomeCategory.class);
                startActivity(intent);
            }
        });





    }


}




