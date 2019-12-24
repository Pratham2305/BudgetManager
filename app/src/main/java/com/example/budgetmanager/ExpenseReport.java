package com.example.budgetmanager;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpenseReport extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    DbHelper myDb;
    ArrayList<Total_Category> arraylist = new ArrayList<Total_Category>();
    private Cursor cursor;
    DbHelper DbHelper;
    SQLiteDatabase sqLiteDatabase;
    String i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13;
    int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_report);

        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        loadDatabase();

        i1=Integer.toString(a1);
        i2=Integer.toString(a2);
        i3=Integer.toString(a3);
        i4=Integer.toString(a4);
        i5=Integer.toString(a5);
        i6=Integer.toString(a6);
        i7=Integer.toString(a7);
        i8=Integer.toString(a8);
        i9=Integer.toString(a9);
        i10=Integer.toString(a10);
        i11=Integer.toString(a11);
        i12=Integer.toString(a12);
        i13=Integer.toString(a13);


        arraylist = new ArrayList<Total_Category>();
        arraylist.add(new Total_Category("Auto",i1));
        arraylist.add(new Total_Category("Bill",i2));
        arraylist.add(new Total_Category("Entertainment",i3));
        arraylist.add(new Total_Category("Food",i4));
        arraylist.add(new Total_Category("Fuel",i5));
        arraylist.add(new Total_Category("General",i6));
        arraylist.add(new Total_Category("Gift",i7));
        arraylist.add(new Total_Category("Gym",i8));
        arraylist.add(new Total_Category("Health",i9));
        arraylist.add(new Total_Category("Holidays",i10));
        arraylist.add(new Total_Category("Clothes",i11));
        arraylist.add(new Total_Category("Rent",i12));
        arraylist.add(new Total_Category("Sport",i13));


        myAdapter = new TotalAdapter(this,arraylist);
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
                        String category, amount, date;
                        category = cursor.getString(2);
                        amount = cursor.getString(3);

                        if (category.equals("auto")){
                            a1+=Integer.parseInt(amount);
                        }
                        else if (category.equals("bill")){
                            a2+=Integer.parseInt(amount);

                        }
                        else if (category.equals("food")){
                            a4+=Integer.parseInt(amount);

                        }
                        else if (category.equals("entertainment")){
                            a3+=Integer.parseInt(amount);

                        }
                        else if (category.equals("fuel")){
                            a5+=Integer.parseInt(amount);

                        }
                        else if (category.equals("general")){
                            a6+=Integer.parseInt(amount);

                        }
                        else if (category.equals("gift")){
                            a7+=Integer.parseInt(amount);

                        }
                        else if (category.equals("gym")){
                            a8+=Integer.parseInt(amount);

                        }
                        else if (category.equals("health")){
                            a9+=Integer.parseInt(amount);

                        }
                        else if (category.equals("hoidays")){
                            a10=Integer.parseInt(amount);
                        }
                        else if (category.equals("clothes")){
                            a11+=Integer.parseInt(amount);

                        }
                        else if (category.equals("rent")){
                            a12+=Integer.parseInt(amount);

                        }
                        else if (category.equals("sport")){
                            a13+=Integer.parseInt(amount);

                        }





                    } while (cursor.moveToNext());


                }
            }
        }catch (SQLException e)   {
            e.printStackTrace();
        }


    }






    }

