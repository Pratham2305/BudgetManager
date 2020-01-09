package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Collections;

import static android.graphics.Color.TRANSPARENT;

public class SetAmount extends AppCompatActivity {

    DbHelper myDb;
    DbHelperTarget targetDb;
    FloatingActionButton btdone;
    String date;
    String Amount,amount;
    EditText etamt;
    String category,target_amt;
    String cat_type;
    int year,month,day,month1,from_day,from_month,from_year,to_day,to_month,to_year,d,Total_Income,Total_Expense;
    Cursor cursor;

    TextView tvcategoryname;
    ImageView ivcategory;
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_amount);
        myDb= new DbHelper(this);


        DisplayDate = findViewById(R.id.DisplayDate);
        etamt = findViewById(R.id.etamt);

        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar. YEAR);
                month = cal.get(Calendar. MONTH);
                day = cal.get(Calendar. DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(SetAmount.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,DateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.show();

            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                month1=month;
               date = dayOfMonth+" / "+month1+" / "+year;
                DisplayDate.setText(date);

            }
        };
        tvcategoryname = findViewById(R.id.tvcategoryname);
        ivcategory = findViewById(R.id.ivcategory);

        String category_name = getIntent().getStringExtra("Category Name");
         category =getIntent().getStringExtra("Category");

        tvcategoryname.setText(category_name);

        if(category.equals("auto"))
        {
            ivcategory.setImageResource(R.drawable.auto);
        }
       else if(category.equals("bill"))
        {
            ivcategory.setImageResource(R.drawable.bill);
        }
        else if(category.equals("entertainment"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.entertainment));
        }
        else if(category.equals("food"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.food));
        }
        else if(category.equals("general"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.general));
        }
        else if(category.equals("fuel"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.fuel));
        }
        else if(category.equals("gift"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.gift));
        }
        else if(category.equals("gym"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.gym));
        }
        else if(category.equals("health"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.health));
        }
        else if(category.equals("holidays"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.holidays));
        }
        else if(category.equals("house"))
        {
            ivcategory.setImageDrawable(getResources().getDrawable(R.drawable.house));
        }
        else if(category.equals("clothes"))
        {
            ivcategory.setImageResource(R.drawable.ic_clothes);
        }
        else if(category.equals("rent"))
        {
            ivcategory.setImageResource(R.drawable.rent);

        }
        else if(category.equals("sport"))
        {
            ivcategory.setImageResource(R.drawable.sport);
        }
        else if(category.equals("Award"))
        {
            ivcategory.setImageResource(R.drawable.award);
        }
        else if(category.equals("Salary"))
        {
            ivcategory.setImageResource(R.drawable.salary);
        }
        else if(category.equals("Investment"))
        {
            ivcategory.setImageResource(R.drawable.investment);
        }
        else if(category.equals("Gift"))
        {
            ivcategory.setImageResource(R.drawable.gift_income);
        }
        else if(category.equals("Voucher"))
        {
            ivcategory.setImageResource(R.drawable.voucher);
        }
        else if(category.equals("Lottery"))
        {
            ivcategory.setImageResource(R.drawable.lottery);
        }
        else if(category.equals("Refund"))
        {
            ivcategory.setImageResource(R.drawable.refund);
        }


        btdone = findViewById(R.id.btdone);

        AddData();
    }
    public void AddData() {


        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Amount = etamt.getText().toString();

                if (Amount.isEmpty() || year==0) {
                    Toast.makeText(SetAmount.this, "Kindly Enter All The Fields!!", Toast.LENGTH_LONG).show();
                } else {

                    if (category.equals("Award") || category.equals("Salary")
                            || category.equals("Investment") || category.equals("Gift") ||
                            category.equals("Voucher") || category.equals("Lottery")
                            || category.equals("Refund")) {
                        cat_type = "Income";
                    } else
                    {
                        cat_type = "Expense";

                    }

                    String Dday = Integer.toString(day);
                    String Mmonth= Integer.toString(month1);
                    String Yyear= Integer.toString(year);




                    targetDb=new DbHelperTarget(getApplicationContext());
                    try {

                        targetDb.checkAndCopyDatabase();
                        targetDb.openDatabase();
                    }catch (SQLException e){
                        e.printStackTrace();
                    }
                    try {
                        cursor = targetDb.QueryData("select * from TargetTable");
                        if (cursor != null) {

                            if (cursor.moveToFirst()) {


                                    target_amt = cursor.getString(0);
                                    from_day= Integer.parseInt(cursor.getString(1));
                                    to_day=Integer.parseInt(cursor.getString(2));
                                    from_month = Integer.parseInt(cursor.getString(3));
                                    to_month = Integer.parseInt(cursor.getString(4));
                                    from_year=Integer.parseInt(cursor.getString(5));
                                    to_year=Integer.parseInt(cursor.getString(6));
                            }
                        }
                    }catch (SQLException e)   {
                        e.printStackTrace();
                    }

                    if (((from_day<=day)&&(day<=to_day))&&((from_month<=month1)&&(month1<=to_month))&&((from_year<=year)&&(year<=to_year))){

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
                                    Total_Income =0;
                                    Total_Expense=0;

                                    do {
                                        category = cursor.getString(2);
                                        amount = cursor.getString(3);
                                        date = cursor.getString(4);
                                        d = Integer.parseInt(amount);

                                        if (category.equals("Award") || category.equals("Salary")
                                                || category.equals("Investment") || category.equals("Gift") ||
                                                category.equals("Voucher") || category.equals("Lottery")
                                                || category.equals("Refund")) {
                                            Total_Income += d;
                                        } else {
                                            Total_Expense += d;
                                        }
                                    } while (cursor.moveToNext());
                                }
                            }
                        }catch (SQLException e)   {
                            e.printStackTrace();
                        }

                        if (target_amt==null){

                            boolean in = myDb.insertData(cat_type,category,Amount,date,Dday,Mmonth,Yyear);

                            if (in == true)
                                Toast.makeText(SetAmount.this, "Data Added", Toast.LENGTH_LONG).show();
                            else {
                                Toast.makeText(SetAmount.this, "Data not Added", Toast.LENGTH_LONG).show();
                            }

                            Intent intent=new Intent(SetAmount.this,com.example.budgetmanager.MainActivity.class);
                            startActivity(intent);

                        }
                        else {

                            if (Total_Expense >= Integer.parseInt(target_amt)) {
                                Toast.makeText(SetAmount.this, "Sorry, You are exceeding your Target Expense", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                boolean in = myDb.insertData(cat_type,category,Amount,date,Dday,Mmonth,Yyear);

                                if (in == true)
                                    Toast.makeText(SetAmount.this, "Data Added", Toast.LENGTH_LONG).show();
                                else {
                                    Toast.makeText(SetAmount.this, "Data not Added", Toast.LENGTH_LONG).show();
                                }

                                Intent intent=new Intent(SetAmount.this,com.example.budgetmanager.MainActivity.class);
                                startActivity(intent);
                            }
                        }

                    }

                    boolean in = myDb.insertData(cat_type,category,Amount,date,Dday,Mmonth,Yyear);

                    if (in == true)
                        Toast.makeText(SetAmount.this, "Data Added", Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(SetAmount.this, "Data not Added", Toast.LENGTH_LONG).show();
                    }

                    Intent intent=new Intent(SetAmount.this,com.example.budgetmanager.MainActivity.class);
                    startActivity(intent);
                }
            }

        });


    }
}