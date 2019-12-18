package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class ExpenseCategory extends AppCompatActivity implements PersonAdapter.ItemClicked {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {

            RecyclerView recyclerView;
            RecyclerView.Adapter myAdapter;
            setContentView(R.layout.activity_expense_category);

            recyclerView = findViewById(R.id.list);
            recyclerView.setHasFixedSize(true);

            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            list = new ArrayList<Person>();
            list.add(new Person("Auto", "auto"));
            list.add(new Person("Bills", "bill"));
            list.add(new Person("Entertainment", "entertainment"));
            list.add(new Person("Food", "food"));
            list.add(new Person("General", "general"));
            list.add(new Person("Fuel", "fuel"));
            list.add(new Person("Gift", "gift"));
            list.add(new Person("Gym", "gym"));
            list.add(new Person("Health", "health"));
            list.add(new Person("Holidays", "holidays"));
            list.add(new Person("House", "house"));
            list.add(new Person("Clothes", "clothes"));
            list.add(new Person("Rent", "rent"));
            list.add(new Person("Sport", "sport"));

            myAdapter = new PersonAdapter(this, list);

            recyclerView.setAdapter(myAdapter);


        }

    }

    @Override
    public void onItemClicked(int index) {

        String category_name = list.get(index).getItemname();
        String category =list.get(index).getCategory();
        Intent intent = new Intent(ExpenseCategory.this,com.example.budgetmanager.SetAmount.class);
        intent.putExtra("Category Name",category_name);
        intent.putExtra("Category",category);
        startActivity(intent);

    }
}