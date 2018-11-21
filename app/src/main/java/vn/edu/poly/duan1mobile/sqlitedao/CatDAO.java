package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.Constant;
import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.Cat;

public class CatDAO implements Constant{

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TAG = "CatDAO";

    public CatDAO(DatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public void InsertCat(Cat cat){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("Namecat",cat.getEdtNamepet());
        values.put("Characteristics",cat.getEdtCharacteristics());
        values.put("Price",cat.getEdtPrice());
        values.put("Link",cat.getEdtLink());

        long id = sqLiteDatabase.insert(TABLE_CAT,null,values);
        Log.e("insertUser",""+id);
        sqLiteDatabase.close();
    }

    public Cat getCat(String username){
        Cat cat = null;

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query
                (TABLE_CAT,new String[]{COLUMN_NAME_CAT,COLUMN_CHARACTERISTIC_CAT,COLUMN_PRICE_CAT,COLUMN_LINK_CAT},
                        COLUMN_NAME_CAT + "=?",new String[]{username},null,null,null);

        if (cursor!= null && cursor.moveToFirst()){
            String usernamecat = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_CAT));
            String chatacteristiccat = cursor.getString(cursor.getColumnIndex(COLUMN_CHARACTERISTIC_CAT));
            String pricecat = cursor.getString(cursor.getColumnIndex(COLUMN_PRICE_CAT));
            String linkcat = cursor.getString(cursor.getColumnIndex(COLUMN_LINK_CAT));
            cat = new Cat(usernamecat,chatacteristiccat,pricecat,linkcat);
        }
        return cat;
    }

    public List<Cat> getAllCat(){
        List<Cat> cats = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

       Cursor c = db.query(TABLE_CAT,null,null,null,null,null,null);
       c.moveToFirst();
       while (c.isAfterLast()==false){
           Cat cat = new Cat();
           cat.setEdtNamepet(c.getString(0));
           cat.setEdtCharacteristics(c.getString(1));
           cat.setEdtPrice(c.getString(2));
           cat.setEdtLink(c.getString(3));
           cats.add(cat);
           Log.d("+++++",cat.toString());
           c.moveToNext();
       }
       c.close();
       return cats;
    }

}
