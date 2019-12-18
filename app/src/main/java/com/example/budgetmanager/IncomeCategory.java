package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class IncomeCategory extends AppCompatActivity implements IncomeAdapter.ItemClicked
{

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Income> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_category);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<Income>();

        list.add(new Income("Award","Award"));
        list.add(new Income("Salary","Salary"));
        list.add(new Income("Investment","Investment"));
        list.add(new Income("Gift","Gift"));
        list.add(new Income("Voucher","Voucher"));
        list.add(new Income("Lottery","Lottery"));
        list.add(new Income("Refund","Refund"));

        myAdapter = new IncomeAdapter(this,list);
        recyclerView.setAdapter(myAdapter);


    }


    @Override
    public void onItemClicked(int index) {

        String category_name = list.get(index).getItemname();
        String category =list.get(index).getCategory();
        Intent intent = new Intent(IncomeCategory.this,com.example.budgetmanager.SetAmount.class);
        intent.putExtra("Category Name",category_name);
        intent.putExtra("Category",category);
        startActivity(intent);
    }
}
