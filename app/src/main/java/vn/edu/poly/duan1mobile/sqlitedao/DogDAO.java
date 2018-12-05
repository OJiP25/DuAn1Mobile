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
    public static final String SQL_DOG = "CREATE TABLE DOG (namepetd text primary key, characteristicsd text, priced text, linkd text)";
    public static final String TAG = "DogDAO";

    public DogDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int insertDog(Dog dog) {
        ContentValues values = new ContentValues();
        values.put("namepetd", dog.getEdtNamepet());
        values.put("characteristicsd", dog.getEdtCharacteristics());
        values.put("priced", dog.getEdtPrice());
        values.put("linkd", dog.getEdtLink());
        if (checkPrimaryKey(dog.getEdtNamepet())) {
            int result = db.update(TABLE_DOG, values, "namepetd=?", new
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

    public List<Dog> getAllDog() {
        List<Dog> dsDog = new ArrayList<>();
        Cursor cc= db.query(TABLE_DOG,null,null,null,null,null,null);
        cc.moveToFirst();
        while (cc.isAfterLast() == false) {
            Dog dogd = new Dog();
            dogd.setEdtNamepet(cc.getString(0));
            dogd.setEdtCharacteristics(cc.getString(1));
            dogd.setEdtPrice(cc.getString(2));
            dogd.setEdtLink(cc.getString(3));
            dsDog.add(dogd);
            Log.d("//===", dogd.toString());
            cc.moveToNext();
        }
        cc.close();
        return dsDog;
    }

    //update
    public int updateDog(Dog dog) {
        ContentValues values = new ContentValues();
        values.put("namepetd", dog.getEdtNamepet());
        values.put("characteristicsd", dog.getEdtCharacteristics());
        values.put("priced", dog.getEdtPrice());
        values.put("linkd", dog.getEdtLink());
        int result = db.update(TABLE_DOG, values, "namepetd=?", new
                String[]{dog.getEdtNamepet()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteCatByID(String namepetd) {
        int result = db.delete(TABLE_DOG, "namepetd=?", new String[]{namepetd});
        if (result == 0)
            return -1;
        return 1;
    }

    public boolean checkPrimaryKey(String strPrimaryKey) {
        String[] columns = {"namepetd"};
        String selection = "namepetd=?";
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

}
