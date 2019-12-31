package com.example.budgetmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Budget.db";
    public static final String TABLE_NAME = "BudgetTable";
    public static final String COL1 = "ID";
    public static final String COL2= "Category";
    public static final String COL3 = "SubCategory";
    public static final String COL4 = "Amount";
    public static final String COL5 = "Date";
    public static final int version = 1;
    private final Context mycontext;
    private SQLiteDatabase myDatabase;
    public static final String DB_Path = "/data/data/com.example.budgetmanager/databases/";


    public DbHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, version);

        this.mycontext = context;




    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE BudgetTable (_id INTEGER PRIMARY KEY AUTOINCREMENT, CATEGORY TEXT, SUBCATEGORY TEXT, AMOUNT TEXT, DATE TEXT) ";
        db.execSQL(create);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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


    public boolean insertData(String cat, String sub_cat, String amt, String date){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL2,cat);
        contentValues.put(COL3,sub_cat);
        contentValues.put(COL4,amt);
        contentValues.put(COL5,date);
       long result =   db.insert(TABLE_NAME,null,contentValues);

       if (result == -1)
           return false;
       else
           return true;

    }






    }

