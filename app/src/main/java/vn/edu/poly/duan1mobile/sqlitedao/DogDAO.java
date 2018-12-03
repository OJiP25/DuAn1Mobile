package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.Dog;

public class DogDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_DOG = "Dog";
    public static final String SQL_DOG = "CREATE TABLE DOG (namepet text primary key, characteristics text, price text, link text)";
    public static final String TAG = "DogDAO";

    public DogDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertCat(Dog dog) {
        ContentValues values = new ContentValues();
        values.put("namepet", dog.getEdtNamepet());
        values.put("characteristics", dog.getEdtCharacteristics());
        values.put("price", dog.getEdtPrice());
        values.put("link", dog.getEdtLink());
        if (checkPrimaryKey(dog.getEdtNamepet())) {
            int result = db.update(TABLE_DOG, values, "namepet=?", new
                    String[]{dog.getEdtNamepet()});
            if (result == 0) {
                return -1;
            }
        } else {
            try {
                if (db.insert(TABLE_DOG, null, values) == -1) {
                    return -1;
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.toString());
            }
        }
        return 1;
    }

    //getAll
    public List<Dog> getAllDog() {
        List<Dog> dsDog = new ArrayList<>();
        Cursor cc = db.query(TABLE_DOG, null, null, null, null, null, null);
        cc.moveToFirst();
        while (cc.isAfterLast() == false) {
            Dog dog = new Dog();
            dog.setEdtNamepet(cc.getString(0));
            dog.setEdtCharacteristics(cc.getString(1));
            dog.setEdtPrice(cc.getString(2));
            dog.setEdtLink(cc.getString(3));
            dsDog.add(dog);
            Log.d("//=====", dog.toString());
            cc.moveToNext();
        }
        cc.close();
        return dsDog;
    }

    //update
    public int updateDog(Dog dog) {
        ContentValues values = new ContentValues();
        values.put("Namepet", dog.getEdtNamepet());
        values.put("Characteristics", dog.getEdtCharacteristics());
        values.put("Price", dog.getEdtPrice());
        values.put("Link", dog.getEdtLink());
        int result = db.update(TABLE_DOG, values, "idBook=?", new
                String[]{dog.getEdtNamepet()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteCatByID(String idBook) {
        int result = db.delete(TABLE_DOG, "Namepet=?", new String[]{idBook});
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
            c = db.query(TABLE_DOG, columns, selection, selectionArgs, null, null,
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
    public Dog getCatByID(String idBook) {
        Dog dog = null;
        //WHERE clause
        String selection = "idBook=?";
        //WHERE clause arguments
        String[] selectionArgs = {idBook};
        Cursor c = db.query(TABLE_DOG, null, selection, selectionArgs, null, null, null);
        Log.d("getBookByID", "===>" + c.getCount());
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            dog = new Dog();
            dog.setEdtNamepet(c.getString(0));
            dog.setEdtCharacteristics(c.getString(1));
            dog.setEdtPrice(c.getString(2));
            dog.setEdtLink(c.getString(3));
            break;
        }
        c.close();
        return dog;
    }
}
