package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.Mouse;

public class MouseDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_MOUSE = "Mouse";
    public static final String SQL_MOUSE = "CREATE TABLE MOUSE (namepet text primary key,  characteristics text, price text, link text)";
    public static final String TAG = "MouseDAO";

    public MouseDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCat(Mouse mouse) {
        ContentValues values = new ContentValues();
        values.put("Namepet", mouse.getEdtNamepet());
        values.put("Characteristics", mouse.getEdtCharacteristics());
        values.put("Price", mouse.getEdtPrice());
        values.put("Link", mouse.getEdtLink());
        if (checkPrimaryKey(mouse.getEdtNamepet())) {
            int result = db.update(TABLE_MOUSE, values, "Namepet=?", new
                    String[]{mouse.getEdtNamepet()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_MOUSE, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<Mouse> getAllCat() {
        List<Mouse> dsMouse = new ArrayList<>();
        Cursor c = db.query(TABLE_MOUSE, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Mouse mouse = new Mouse();
            mouse.setEdtNamepet(c.getString(0));
            mouse.setEdtCharacteristics(c.getString(1));
            mouse.setEdtPrice(c.getString(2));
            mouse.setEdtLink(c.getString(3));
            dsMouse.add(mouse);
            Log.d("//=====", mouse.toString());
            c.moveToNext();
        }
        c.close();
        return dsMouse;
    }

    //update
    public int updateCat(Mouse mouse ) {
        ContentValues values = new ContentValues();
        values.put("Namepet", mouse.getEdtNamepet());
        values.put("Characteristics", mouse.getEdtCharacteristics());
        values.put("Price", mouse.getEdtPrice());
        values.put("Link", mouse.getEdtLink());
        int result = db.update(TABLE_MOUSE, values, "idBook=?", new
                String[]{mouse.getEdtNamepet()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteCatByID(String idBook) {
        int result = db.delete(TABLE_MOUSE, "Namepet=?", new String[]{idBook});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"idBook"};
        //WHERE clause
        String selection = "idBook=?";
        //WHERE clause arguments
        String[] selectionArgs = {strPrimaryKey};
        Cursor c = null;
        try {
            c = db.query(TABLE_MOUSE, columns, selection, selectionArgs, null, null,
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
    //getAll
    public Mouse getCatByID(String idBook) {
        Mouse mouse = null;
        //WHERE clause
        String selection = "idBook=?";
        //WHERE clause arguments
        String[] selectionArgs = {idBook};
        Cursor c = db.query(TABLE_MOUSE, null, selection, selectionArgs, null, null, null);
        Log.d("getBookByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            mouse = new Mouse();
            mouse.setEdtNamepet(c.getString(0));
            mouse.setEdtCharacteristics(c.getString(1));
            mouse.setEdtPrice(c.getString(2));
            mouse.setEdtLink(c.getString(3));
            break;
        }
        c.close();
        return mouse;
    }
}
