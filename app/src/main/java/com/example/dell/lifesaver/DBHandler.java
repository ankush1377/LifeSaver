package com.example.dell.lifesaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{

    public DBHandler(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, 1);
        Log.i("DBHandler","Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q="CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+"("+
                TableData.TableInfo.USER_NAME+" TEXT,"+
                TableData.TableInfo.USER_EMAIL+" TEXT,"+
                TableData.TableInfo.USER_ADDRESS+" TEXT,"+
                TableData.TableInfo.USER_PHONE+" TEXT,"+
                TableData.TableInfo.USER_BG+" TEXT,"+
                TableData.TableInfo.EMERGENCY_CONTACT+" TEXT,"+
                TableData.TableInfo.EMERGENCY_NUMBER+" TEXT);";

        db.execSQL(q);
        Log.i("DBHandler","Table Created");
    }

    public void putData(DBHandler dbHandler,String name,String email,String address,String pn,String bg,String ec,String en){

        SQLiteDatabase sqLiteDatabase = dbHandler.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TableData.TableInfo.USER_NAME,name);
        contentValues.put(TableData.TableInfo.USER_EMAIL,email);
        contentValues.put(TableData.TableInfo.USER_ADDRESS,address);
        contentValues.put(TableData.TableInfo.USER_PHONE,pn);
        contentValues.put(TableData.TableInfo.USER_BG,bg);
        contentValues.put(TableData.TableInfo.EMERGENCY_CONTACT,ec);
        contentValues.put(TableData.TableInfo.EMERGENCY_NUMBER,en);

        sqLiteDatabase.insert(TableData.TableInfo.TABLE_NAME,null,contentValues);
        Log.i("DBHandler","Data Inserted");
    }

    public Cursor getEmergency(DBHandler dbHandler){

        SQLiteDatabase sqLiteDatabase = dbHandler.getReadableDatabase();
        String[] columns={TableData.TableInfo.USER_EMAIL,TableData.TableInfo.EMERGENCY_CONTACT, TableData.TableInfo.EMERGENCY_NUMBER};
        Cursor cr = sqLiteDatabase.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return cr;
    }


    public Cursor getDonors(DBHandler dbHandler,String bg) {
        //List<String> donorList = new ArrayList<String>();

        SQLiteDatabase sqLiteDatabase = dbHandler.getReadableDatabase();
        String[] columns={TableData.TableInfo.USER_NAME,TableData.TableInfo.USER_PHONE, TableData.TableInfo.USER_BG};
        Cursor cr = sqLiteDatabase.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return cr;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}