package com.pasofe.deadmanwarning.logic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DataManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "deadmanwarningdatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USERDATA =
            "CREATE TABLE " + "USER_DATA" + " (" +
                    "UserId" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name" + " TEXT, " +
                    "Phone" + " INTEGER, " +
                    "E_Mail" + " TEXT)";

    private static final String CREATE_TABLE_USERCONTACTS =
            "CREATE TABLE " + "USER_CONTACTS" + " (" +
                    "UserId" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Name" + " TEXT, " +
                    "Phone" + " INTEGER, " +
                    "E_Mail" + " TEXT)";

    public DataManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_USERDATA);
            db.execSQL(CREATE_TABLE_USERCONTACTS);
        }catch (Exception e){
            System.out.println("Error  en onCreate");
        }finally {
                db.close();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
