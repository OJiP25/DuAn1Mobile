package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.Andmore;
import vn.edu.poly.duan1mobile.model.Cat;

public class AndMoreDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_ANDMORE = "AndMore";
    public static final String SQL_ANDMORE = "CREATE TABLE ANDMORE (namepet text primary key,  characteristics text, price text, link text)";
    public static final String TAG = "AndMoreDAO";

    public AndMoreDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCat(Andmore andmore) {
        ContentValues values = new ContentValues();
        values.put("namepet", andmore.getEdtNamepet());
        values.put("characteristics", andmore.getEdtCharacteristics());
        values.put("price", andmore.getEdtPrice());
        values.put("link", andmore.getEdtLink());
        if (checkPrimaryKey(andmore.getEdtNamepet())) {
            int result = db.update(TABLE_ANDMORE, values, "namepet=?", new
                    String[]{andmore.getEdtNamepet()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_ANDMORE, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<Andmore> getAllCat() {
        List<Andmore> dsSach = new ArrayList<>();
        Cursor c = db.query(TABLE_ANDMORE, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Andmore andmore = new Andmore();
            andmore.setEdtNamepet(c.getString(0));
            andmore.setEdtCharacteristics(c.getString(1));
            andmore.setEdtPrice(c.getString(2));
            andmore.setEdtLink(c.getString(3));
            dsSach.add(andmore);
            Log.d("//=====", andmore.toString());
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
        int result = db.update(TABLE_ANDMORE, values, "idBook=?", new
                String[]{cat.getEdtNamepet()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteCatByID(String namepet) {
        int result = db.delete(TABLE_ANDMORE, "Namepet=?", new String[]{namepet});
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
            c = db.query(TABLE_ANDMORE, columns, selection, selectionArgs, null, null,
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
