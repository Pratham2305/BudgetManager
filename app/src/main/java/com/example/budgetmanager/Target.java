package com.example.budgetmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.graphics.Color.TRANSPARENT;

public class Target extends AppCompatActivity {


    EditText etAmt;
    TextView tvDuration,DisplayDateFrom,DisplayDateTo,D;
    String Target_amt;
    FloatingActionButton btDone;
    Cursor cursor;
    String ID,category,amount,date,day,month,year,dateFrom,dateTo;
    DbHelper myDb;
    int d,Total_amt=0,year1,month1,day1,year2,month2,day2;
    private DatePickerDialog.OnDateSetListener DateSetListener,DateSetListener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        etAmt=findViewById(R.id.etAmt);
        tvDuration=findViewById(R.id.tvDuration);
        btDone=findViewById(R.id.btDone);

        Target_amt = etAmt.getEditableText().toString();


        DisplayDateFrom = findViewById(R.id.DisplayDateFrom);
        DisplayDateTo = findViewById(R.id.DisplayDateTo);

        String dt = new SimpleDateFormat("DD / MM / YYYY", Locale.getDefault()).format(new Date());

        DisplayDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year1 = cal.get(Calendar. YEAR);
                month1 = cal.get(Calendar. MONTH);
                day1 = cal.get(Calendar. DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Target.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,DateSetListener1,year1,month1,day1);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.show();

            }
        });

        DateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                dateFrom = dayOfMonth+" / "+month+" / "+year;
                DisplayDateFrom.setText(dateFrom);

            }
        };


        DisplayDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year2 = cal.get(Calendar. YEAR);
                month2 = cal.get(Calendar. MONTH);
                day2 = cal.get(Calendar. DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Target.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,DateSetListener,year2,month2,day2);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
                dialog.show();

            }
        });

        DateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                month=month+1;
                dateTo = dayOfMonth+" / "+month+" / "+year;
                DisplayDateTo.setText(dateTo);

            }
        };




            btDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if ((day1<day2 && month1<=month2&& year1<=year2) || (day1>=day2&&month2>month1&&year1<=year2) ) {

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
                                        ID = cursor.getString(0);
                                        category = cursor.getString(2);
                                        amount = cursor.getString(3);
                                        date = cursor.getString(4);
                                        day = cursor.getString(5);
                                        month = cursor.getString(6);
                                        year = cursor.getString(7);
                                        d = Integer.parseInt(amount);

                                        Total_amt+=d;


                                    } while (cursor.moveToNext());
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        Toast.makeText(Target.this,"Kindly Add Correct date Range",Toast.LENGTH_LONG).show();
                    }

                }
            });



    }
}
