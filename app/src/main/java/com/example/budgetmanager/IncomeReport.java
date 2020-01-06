package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class IncomeReport extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    DbHelper myDb;
    ArrayList<Total_Category01> arraylist = new ArrayList<Total_Category01>();
    private Cursor cursor;
    DbHelper DbHelper;
    SQLiteDatabase sqLiteDatabase;
    String i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
    int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_report);

        recyclerView = (RecyclerView) findViewById(R.id.list);

        loadDatabase();

        i2=Integer.toString(a2);
        i1=Integer.toString(a1);
        i3=Integer.toString(a3);
        i4=Integer.toString(a4);
        i5=Integer.toString(a5);
        i6=Integer.toString(a6);
        i7=Integer.toString(a7);


        arraylist = new ArrayList<Total_Category01>();
        arraylist.add(new Total_Category01("Award",i1));
        arraylist.add(new Total_Category01("Gift",i2));
        arraylist.add(new Total_Category01("Investment",i3));
        arraylist.add(new Total_Category01("Lottery",i4));
        arraylist.add(new Total_Category01("Salary",i5));
        arraylist.add(new Total_Category01("Refund",i6));
        arraylist.add(new Total_Category01("Voucher",i7));


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        myAdapter =new TotalAdapter01(getParent(),arraylist);
        ((TotalAdapter01) myAdapter).setOnTapListener(new OnTapListener1() {
            @Override
            public void OnTapViewe(int position) {
                Intent intent=new Intent(IncomeReport.this,com.example.budgetmanager.Category_Info.class);
                if (position==0){
                    intent.putExtra("Category Type","Award");
                }

                else if (position==1){
                    intent.putExtra("Category Type","Gift");
                }

                else if (position==2){
                    intent.putExtra("Category Type","Investment");
                }

                else if (position==3){
                    intent.putExtra("Category Type","Lottery");
                }

                else if (position==4){
                    intent.putExtra("Category Type","Salary");
                }

                else if (position==5){
                    intent.putExtra("Category Type","Refund");
                }
                else if (position==6){
                    intent.putExtra("Category Type","Voucher");
                }
                startActivity(intent);

            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

    }

    public void loadDatabase() {

        myDb = new DbHelper(getApplicationContext());
        try {

            myDb.checkAndCopyDatabase();
            myDb.openDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cursor = myDb.QueryData("select * from BudgetTable");
            if (cursor != null) {

                if (cursor.moveToFirst()) {

                    do {
                        String category, amount, date;
                        category = cursor.getString(2);
                        amount = cursor.getString(3);

                        if (category.equals("Award")) {
                            a1 += Integer.parseInt(amount);
                        } else if (category.equals("Gift")) {
                            a2 += Integer.parseInt(amount);

                        } else if (category.equals("Investment")) {
                            a4 += Integer.parseInt(amount);

                        } else if (category.equals("Lottery")) {
                            a3 += Integer.parseInt(amount);

                        } else if (category.equals("Salary")) {
                            a5 += Integer.parseInt(amount);

                        } else if (category.equals("Refund")) {
                            a6 += Integer.parseInt(amount);

                        } else if (category.equals("Voucher")) {
                            a7 += Integer.parseInt(amount);

                        }

                    } while (cursor.moveToNext());


                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
