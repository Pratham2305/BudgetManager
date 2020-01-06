package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Category_Info extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    DbHelper myDb;
    ArrayList<Cat_Info> arraylist = new ArrayList<Cat_Info>();
    private Cursor cursor;
    String category, amount, date,amt,dte;
    TextView tvCat_Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__info);

        recyclerView = findViewById(R.id.info_list);
        tvCat_Name=findViewById(R.id.tvCat_Name);


        tvCat_Name.setText(getIntent().getStringExtra("Category Type"));

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arraylist = new ArrayList<Cat_Info>();
        arraylist.clear();
        loadDatabase();

        myAdapter = new Cat_InfoAdapter(this,arraylist);
        recyclerView.setAdapter(myAdapter);


    }
    public void loadDatabase(){

        myDb=new DbHelper(getApplicationContext());
        try {

            myDb.checkAndCopyDatabase();
            myDb.openDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            cursor = myDb.QueryData("select * from BudgetTable");
            if (cursor != null) {

                if (cursor.moveToFirst()) {

                    do {
                        category = cursor.getString(2);
                        amount = cursor.getString(3);
                        date=cursor.getString(4);

                        if (category.equals(getIntent().getStringExtra("Category Type"))){
                            amt=amount;
                            dte=date;
                            arraylist.add(new Cat_Info(dte,getIntent().getStringExtra("Category Type"),amt));
                        }
                    } while (cursor.moveToNext());
                }
            }
        }catch (SQLException e)   {
            e.printStackTrace();
        }


    }
}
