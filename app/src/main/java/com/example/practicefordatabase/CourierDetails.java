package com.example.practicefordatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CourierDetails extends SQLiteOpenHelper {
    public CourierDetails(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Courier(custNa varchar(30),custPh varchar(12),delCity varchar(30),typeC varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertRecord(String na,String pho,String selectedCity,String typeofC)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("insert into Courier values(?,?,?,?)",new String[]{na,pho,selectedCity,typeofC});
        db.close();
    }


    public String DisplayCity(String selectedCity) {
        String Cdata="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("select * from Courier where delcity='"+selectedCity+"'",null);
        while(cr.moveToNext())
        {
            String sname=cr.getString(0);
            String num=cr.getString(1);
            String city=cr.getString(2);
            String type=cr.getString(3);
            Cdata+="Name : "+sname+"\tContact : "+num+"\nCity : "+city+"\tType : "+type;
        }
        return Cdata;
    }

    public String DisplayLetter(String typeofC) {
        String Ldata="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cr=db.rawQuery("select * from Courier where typeC='"+typeofC+"'",null);
        while(cr.moveToNext())
        {
            String sname=cr.getString(0);
            String num=cr.getString(1);
            String city=cr.getString(2);
            String type=cr.getString(3);
            Ldata+="\n\nName : "+sname+"\tContact : "+num+"\nCity : "+city+"\tType : "+type;
        }
        return Ldata;
    }
}
