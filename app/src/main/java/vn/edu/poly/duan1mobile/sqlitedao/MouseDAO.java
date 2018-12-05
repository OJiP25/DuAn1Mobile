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
    public static final String SQL_MOUSE = "CREATE TABLE MOUSE (namepetm text primary key,  characteristicsm text, pricem text, linkm text)";
    public static final String TAG = "MouseDAO";

    public MouseDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCat(Mouse mouse) {
        ContentValues values = new ContentValues();
        values.put("namepetm", mouse.getEdtNamepet());
        values.put("characteristicsm", mouse.getEdtCharacteristics());
        values.put("pricem", mouse.getEdtPrice());
        values.put("linkm", mouse.getEdtLink());
        if (checkPrimaryKey(mouse.getEdtNamepet())) {
            int result = db.update(TABLE_MOUSE, values, "namepetm=?", new
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
    public List<Mouse> getAllMouse() {
        List<Mouse> dsMouse = new ArrayList<>();
        Cursor ccc = db.query(TABLE_MOUSE, null, null, null, null, null, null);
        ccc.moveToFirst();
        while (ccc.isAfterLast() == false) {
            Mouse mouse = new Mouse();
            mouse.setEdtNamepet(ccc.getString(0));
            mouse.setEdtCharacteristics(ccc.getString(1));
            mouse.setEdtPrice(ccc.getString(2));
            mouse.setEdtLink(ccc.getString(3));
            dsMouse.add(mouse);
            Log.d("//=====", mouse.toString());
            ccc.moveToNext();
        }
        ccc.close();
        return dsMouse;
    }



    //delete
    public int deleteCatByID(String namepetm) {
        int result = db.delete(TABLE_MOUSE, "namepetm=?", new String[]{namepetm});
        if (result == 0)
            return -1;
        return 1;
    }

    //check
    public boolean checkPrimaryKey(String strPrimaryKey) {
        //SELECT
        String[] columns = {"namepetm"};
        //WHERE clause
        String selection = "namepetm=?";
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
    public Mouse getMouseByID(String namepetm) {
        Mouse mouse = null;
        //WHERE clause
        String selection = "namepetm=?";
        //WHERE clause arguments
        String[] selectionArgs = {namepetm};
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
