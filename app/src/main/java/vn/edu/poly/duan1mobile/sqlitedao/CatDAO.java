package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.Cat;

public class CatDAO {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_CAT = "Cat";
    public static final String SQL_CAT = "CREATE TABLE CAT (namepet text primary key,  characteristics text, price text, link text)";
    public static final String TAG = "CatDAO";

    public CatDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCat(Cat cat) {
        ContentValues values = new ContentValues();
        values.put("namepet", cat.getEdtNamepet());
        values.put("characteristics", cat.getEdtCharacteristics());
        values.put("price", cat.getEdtPrice());
        values.put("link", cat.getEdtLink());
        if (checkPrimaryKey(cat.getEdtNamepet())) {
            int result = db.update(TABLE_CAT, values, "namepet=?", new
                    String[]{cat.getEdtNamepet()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_CAT, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<Cat> getAllCat() {
        List<Cat> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_CAT, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Cat cat = new Cat();
            cat.setEdtNamepet(c.getString(0));
            cat.setEdtCharacteristics(c.getString(1));
            cat.setEdtPrice(c.getString(2));
            cat.setEdtLink(c.getString(3));
            dsSach.add(cat);
            Log.d("//=====", cat.toString());
            c.moveToNext();
        }
        c.close();
        return dsSach;
    }

    //update
    public int updateCat(Cat cat) {
        ContentValues values = new ContentValues();
        values.put("Namepet", cat.getEdtNamepet());
        values.put("Characteristics", cat.getEdtCharacteristics());
        values.put("Price", cat.getEdtPrice());
        values.put("Link", cat.getEdtLink());
        int result = db.update(TABLE_CAT, values, "idBook=?", new
                String[]{cat.getEdtNamepet()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteCatByID(String namepet) {
        int result = db.delete(TABLE_CAT, "Namepet=?", new String[]{namepet});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"namepet"};
        //WHERE clause
        String selection = "namepet=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_CAT, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if (i <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



}
