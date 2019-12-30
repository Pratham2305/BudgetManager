package com.example.budgetmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.IpPrefix;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {


    RecyclerView recyclerView;
    RecyclerView.Adapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    DbHelper myDb;
    ArrayList<History> arraylist = new ArrayList<History>();
    private Cursor cursor;
    DbHelper DbHelper;
    SQLiteDatabase sqLiteDatabase;
    private HistoryAdapter adapter;
    String category, amount, date;


    TextView tvexpense;
    TextView tvincome;
    private DrawerLayout drawer;
    int Total_Income=0;
    int Total_Expense=0;
    String ID,old_id="";
    int d;



    FloatingActionButton btadd;
    Button btview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        tvexpense = findViewById(R.id.tvExpenses);
        tvincome = findViewById(R.id.tvIncome);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close );

        drawer.addDrawerListener(toggle);
        toggle.syncState();






        btadd = findViewById(R.id.btadd);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,com.example.budgetmanager.AddingActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

       switch (menuItem.getItemId()){

           case R.id.expense_report :
                Intent intent = new Intent(MainActivity.this,com.example.budgetmanager.ExpenseReport.class);
                startActivity(intent);

                break;
           case R.id.income_report:

               Intent in = new Intent(MainActivity.this,com.example.budgetmanager.IncomeReport.class);
               startActivity(in);

               break;

           case R.id.target:

               Intent tar = new Intent(MainActivity.this,com.example.budgetmanager.Target.class);
               startActivity(tar);

               break;

           case R.id.report:

               Intent rep= new Intent(MainActivity.this,com.example.budgetmanager.ReportActivity.class);
               startActivity(rep);

               break;



       }

        return true;
    }

    @Override
    public void onBackPressed()
    {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{

            super.onBackPressed();

        }



    }


    public void loadDatabase(){

        myDb=new DbHelper(getApplicationContext());
        try {

            myDb.checkAndCopyDatabase();
            myDb.openDatabase();
        }catch (SQLException e){
            e.printStackTrace();
        }
        loadData();


    }


        public void loadData(){



        try {
            cursor = myDb.QueryData("select * from BudgetTable");
            if (cursor != null) {

                if (cursor.moveToFirst()) {

                    do {
                        ID=cursor.getString(0);
                        category = cursor.getString(2);
                        amount = cursor.getString(3);
                        date = cursor.getString(4);
                        d= Integer.parseInt(amount);

                        if (category.equals("Award") || category.equals("Salary")
                                || category.equals("Investment") || category.equals("Gift") ||
                                category.equals("Voucher") || category.equals("Lottery")
                                || category.equals("Refund")){
                            Total_Income+=d;
                        }else {
                            Total_Expense+=d;
                        }
                        tvincome.setText(Integer.toString(Total_Income));
                        tvexpense.setText(Integer.toString(Total_Expense));


                        arraylist.add(new History(date, category, amount));

                    } while (cursor.moveToNext());


                }
            }
        }catch (SQLException e)   {
            e.printStackTrace();
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        adapter =new HistoryAdapter(getParent(),arraylist);
        adapter.setOnTapListener(new OnTapListener() {
            @Override
            public void OnTapViewe(int position) {
                Toast.makeText(getApplicationContext(),"Click to "+position,Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onResume(){
        super.onResume();
        arraylist.clear();
        loadDatabase();


    }






}
