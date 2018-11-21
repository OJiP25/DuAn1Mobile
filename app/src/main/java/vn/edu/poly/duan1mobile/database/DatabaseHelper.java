package vn.edu.poly.duan1mobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.poly.duan1mobile.Constant;

public class DatabaseHelper extends SQLiteOpenHelper implements Constant{
    public static final String DATABASE_NAME = "learnaboutpest";
    public DatabaseHelper(Context context) {super(context, "DATABASE_NAME", null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAT);
        sqLiteDatabase.execSQL(CREATE_TABLE_DOG);
        sqLiteDatabase.execSQL(CREATE_TABLE_MOUSE);
        sqLiteDatabase.execSQL(CREATE_TABLE_ANDMORE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_DOG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOUSE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ANDMORE);
        onCreate(sqLiteDatabase);
    }
}
