package com.example.budgetmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelperTarget extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Target.db";
    public static final String TABLE_NAME1 = "TargetTable";
    public static final String C1 = "Target";
    public static final String C2 = "FromDay";
    public static final String C3 = "ToDay";
    public static final String C4 = "FromMonth";
    public static final String C5 = "ToMonth";
    public static final String C6 = "FromYear";
    public static final String C7 = "ToYear";
    public static final int version = 1;
    private final Context mycontext;
    private SQLiteDatabase myDatabase;
    public static final String DB_Path = "/data/data/com.example.budgetmanager/databases/";


    public DbHelperTarget(@Nullable Context context) {
        super(context,DATABASE_NAME, null, version);

        this.mycontext = context;




    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create1 = "CREATE TABLE "+TABLE_NAME1+" ( TARGET TEXT,FROMDAY TEXT,TODAY TEXT,FROMMONTH TEXT,TOMONTH TEXT,FROMYEAR TEXT,TOYEAR TEXT); ";
        db.execSQL(create1);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);

    }


    public void checkAndCopyDatabase(){
        boolean dbExist= checkDatabase();
        if (dbExist){
            Log.d("TAG","database already exist");
        }
        else{
            this.getReadableDatabase();
        }
        try {
            copyDatabase();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("TAG","Error copy database");

        }

    }

    public boolean checkDatabase(){
        String myPath=DB_Path+DATABASE_NAME;
        File databasePath = mycontext.getDatabasePath(myPath);
        return databasePath.exists();

    }


    public void copyDatabase() throws IOException {
        InputStream myInput= mycontext.getAssets().open(DATABASE_NAME);
        String outFileNAme= DB_Path + DATABASE_NAME;
        OutputStream myOutput=new FileOutputStream(outFileNAme);
        byte[] buffer=new byte[1024];
        int length;
        while((length=myInput.read(buffer)) >0){
            myOutput.write(buffer,0,length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDatabase(){
        String myPath=DB_Path+DATABASE_NAME;
        myDatabase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public synchronized void  close(){
        if(myDatabase != null){
            myDatabase.close();
        }
        super.close();
    }



    public Cursor QueryData(String query){
        myDatabase=this.getReadableDatabase();
        return myDatabase.rawQuery(query,null);

    }

    public boolean insertData1(String target_amt, String from_day, String to_day,String from_month,String to_month,String from_year,String to_year){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(C1,target_amt);
        contentValues.put(C2,from_day);
        contentValues.put(C3,to_day);
        contentValues.put(C4,from_month);
        contentValues.put(C5,to_month);
        contentValues.put(C6,from_year);
        contentValues.put(C7,to_year);
        long result =   db.insert(TABLE_NAME1,null,contentValues);

        if (result == -1)
            return false;
        else
            return true;

    }

}
