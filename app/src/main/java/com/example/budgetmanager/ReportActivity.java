package com.example.budgetmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.CYAN;
import static android.graphics.Color.DKGRAY;
import static android.graphics.Color.GRAY;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.MAGENTA;
import static android.graphics.Color.RED;
import static android.graphics.Color.WHITE;
import static android.graphics.Color.YELLOW;

public class ReportActivity extends AppCompatActivity {

    private static String TAG ="ReportActivity";
    ArrayList<String> type= new ArrayList<>();
    Cursor cursor;
    DbHelper myDb;
    String ID,amount,category,date;
    int d;
    PieChart pieChart;
    BarChart barChart;
    int a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0,a10=0,a11=0,a12=0,a13=0,a14=0,i=0;
    int c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        Log.d(TAG,"onCreate: starting to create chart");

        pieChart= (PieChart) findViewById(R.id.pieChart);
        barChart= (BarChart) findViewById(R.id.barChart);

        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);

        pieChart.setRotationEnabled(true);
        pieChart.setHoleRadius(20f);
        pieChart.setHoleColor(WHITE);
        pieChart.setCenterText("Expenses(%)");
        pieChart.setCenterTextSize(8);
        pieChart.setTransparentCircleRadius(61f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setDragDecelerationEnabled(true);
        pieChart.setUsePercentValues(true);
        ArrayList<PieEntry> yEntrys =new ArrayList<>();

        ArrayList<BarEntry> barEntries = new ArrayList<>();


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
                        ID=cursor.getString(0);
                        category = cursor.getString(2);
                        amount = cursor.getString(3);
                        date = cursor.getString(4);
                        d= Integer.parseInt(amount);


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
                        else if (category.equals("house")){
                            a11+=Integer.parseInt(amount);
                        }
                        else if (category.equals("clothes")){
                            a12+=Integer.parseInt(amount);

                        }
                        else if (category.equals("rent")){
                            a13+=Integer.parseInt(amount);

                        }
                        else if (category.equals("sport")){
                            a14+=Integer.parseInt(amount);

                        }

                    } while (cursor.moveToNext());
                }
            }
        }catch (SQLException e)   {
            e.printStackTrace();
        }

        barEntries.add(new BarEntry(1,a1));
        barEntries.add(new BarEntry(2,a2));
        barEntries.add(new BarEntry(3,a3));
        barEntries.add(new BarEntry(4,a4));
        barEntries.add(new BarEntry(5,a5));
        barEntries.add(new BarEntry(6,a6));
        barEntries.add(new BarEntry(7,a7));
        barEntries.add(new BarEntry(8,a8));
        barEntries.add(new BarEntry(9,a9));
        barEntries.add(new BarEntry(10,a10));
        barEntries.add(new BarEntry(11,a11));
        barEntries.add(new BarEntry(12,a12));
        barEntries.add(new BarEntry(13,a13));
        barEntries.add(new BarEntry(14,a14));


        yEntrys.add(new PieEntry(a1,"Auto"));
        yEntrys.add(new PieEntry(a2,"Bill"));
        yEntrys.add(new PieEntry(a4,"Food"));
        yEntrys.add(new PieEntry(a3,"Entertainment"));
        yEntrys.add(new PieEntry(a5,"Fuel"));
        yEntrys.add(new PieEntry(a6,"General"));
        yEntrys.add(new PieEntry(a7,"Gift"));
        yEntrys.add(new PieEntry(a8,"Gym"));
        yEntrys.add(new PieEntry(a9,"Health"));
        yEntrys.add(new PieEntry(a10,"Holidays"));
        yEntrys.add(new PieEntry(a11,"House"));
        yEntrys.add(new PieEntry(a12,"Clothes"));
        yEntrys.add(new PieEntry(a13,"Rent"));;
        yEntrys.add(new PieEntry(a14,"Sport"));



        PieDataSet dataSet = new PieDataSet(yEntrys,"  Expenses");
        BarDataSet dataSet1=new BarDataSet(barEntries,"   Expenses");

        dataSet1.setBarBorderWidth(0.9f);
        dataSet1.setColors(ColorTemplate.JOYFUL_COLORS);
        BarData barData=new BarData((dataSet1));
        barChart.setData(barData);


        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setValueTextColor(BLUE);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(BLACK);

        pieChart.setData(data);

        String[] Type=new String[]{"Auto","Bill","Entertainment","Food","Fuel","General","Gift","Gym","Health","Holidays","House","Clothes","Rent","Sports"};
        XAxis xAxis=barChart.getXAxis();

        xAxis.setLabelRotationAngle(-65);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(Type));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setLabelCount(Type.length-1);
        xAxis.setGranularityEnabled(true);

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setLabelCount(10, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend l = barChart.getLegend();
        l.setEnabled(false);


        }



        
    }



