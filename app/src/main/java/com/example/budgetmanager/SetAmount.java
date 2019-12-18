package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
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

import static android.graphics.Color.TRANSPARENT;

public class SetAmount extends AppCompatActivity {

    FloatingActionButton btdone;
    String date;
    String Amount;
    EditText etamt;
    String category;

    TextView tvcategoryname;
    ImageView ivcategory;
    private TextView DisplayDate;
    private DatePickerDialog.OnDateSetListener DateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_amount);

        DisplayDate = findViewById(R.id.DisplayDate);
        etamt = findViewById(R.id.etamt);

        DisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar. YEAR);
                int month = cal.get(Calendar. MONTH);
                int day = cal.get(Calendar. DAY_OF_MONTH);

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
               date = dayOfMonth+" / "+month+" / "+year;
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


        btdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Amount = etamt.getText().toString();

                Intent intent = new Intent(SetAmount.this,com.example.budgetmanager.MainActivity.class);
                intent.putExtra("Date", date);
                intent.putExtra("Category Name",category);
                intent.putExtra("Amount",Amount);
                startActivity(intent);


                }














        });
};

    }


