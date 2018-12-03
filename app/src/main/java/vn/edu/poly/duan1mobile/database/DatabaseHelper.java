package vn.edu.poly.duan1mobile.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vn.edu.poly.duan1mobile.Constant;
import vn.edu.poly.duan1mobile.model.Cat;
import vn.edu.poly.duan1mobile.model.User;
import vn.edu.poly.duan1mobile.sqlitedao.CatDAO;
import vn.edu.poly.duan1mobile.sqlitedao.DogDAO;
import vn.edu.poly.duan1mobile.sqlitedao.MouseDAO;
import vn.edu.poly.duan1mobile.sqlitedao.UserDAO;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "learnaboutpest";
    public DatabaseHelper(Context context) {super(context, " DATABASE_NAME ", null,1);}
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserDAO.SQL_USER);
        sqLiteDatabase.execSQL(CatDAO.SQL_CAT);
        sqLiteDatabase.execSQL(DogDAO.SQL_DOG);
        sqLiteDatabase.execSQL(MouseDAO.SQL_MOUSE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + UserDAO.TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CatDAO.TABLE_CAT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DogDAO.TABLE_DOG);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MouseDAO.TABLE_MOUSE);

        onCreate(sqLiteDatabase);
    }
}
