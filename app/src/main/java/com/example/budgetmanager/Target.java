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
    DbHelperTarget targetDb;
    int d,Total_amt=0,year1,month1,day1,year2,month2,day2;
    private DatePickerDialog.OnDateSetListener DateSetListener,DateSetListener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        targetDb= new DbHelperTarget(this);

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

                    boolean in =targetDb.insertData1(Target_amt,Integer.toString(day1),Integer.toString(day2),Integer.toString(month1),Integer.toString(month2),Integer.toString(year1),Integer.toString(year2));


                    if (in == true)
                        Toast.makeText(Target.this, "Target Added, BEST OF LUCK!!", Toast.LENGTH_LONG).show();
                    else {
                        Toast.makeText(Target.this, "Target not Added", Toast.LENGTH_LONG).show();
                    }


                }
            });



    }
}
