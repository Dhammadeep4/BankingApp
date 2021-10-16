package com.example.bankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9901929000,'Salman Khan',10000.00,'salman@gmail.com','XXXXXXXXXXXX9028','SBIN0000371')");
        db.execSQL("insert into user_table values(8291889090,'Sharukh Khan',8000.00,'srk@gmail.com','XXXXXXXXXXXX1232','HDFC0000685')");
        db.execSQL("insert into user_table values(8456789090,'Amir Khan',7000.00,'amir@gmail.com','XXXXXXXXXXXX8297','IBKL0000367')");
        db.execSQL("insert into user_table values(9567890123,'Akshay Kumar',7500.00,'akshay@gmail.com','XXXXXXXXXXXX9000','BARB0VJAIRO')");
        db.execSQL("insert into user_table values(9678901234,'Ajay Devgan',11000.00,'ajay@gmail.com','XXXXXXXXXXXX1290','SBIN0000371')");
        db.execSQL("insert into user_table values(8789012345,'Aishwarya Rai',12000.00,'aish@gmail.com','XXXXXXXXXXXX2222','BARB0VJAIRO')");
        db.execSQL("insert into user_table values(7890123456,'Katrina Kaif',5000.00,'kat@gmail.com','XXXXXXXXXXXX8298','SBIN0000371')");
        db.execSQL("insert into user_table values(9901234567,'Anushka Sharma',9000.00,'anushka@gmail.com','XXXXXXXXXXXX1728','HDFC0000685')");
        db.execSQL("insert into user_table values(9012345678,'Kriti Sanon',15000.00,'kriti@gmail.com','XXXXXXXXXXXX0192','BARB0VJAIRO')");
        db.execSQL("insert into user_table values(8234567809,'Deepika Padukone',12000.00,'dp@gmail.com','XXXXXXXXXXXX1234','SBIN0000371')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
