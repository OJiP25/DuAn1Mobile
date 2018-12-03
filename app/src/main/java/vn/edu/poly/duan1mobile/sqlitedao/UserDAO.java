package vn.edu.poly.duan1mobile.sqlitedao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.Constant;
import vn.edu.poly.duan1mobile.database.DatabaseHelper;
import vn.edu.poly.duan1mobile.model.User;

public class UserDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_USER = "User";
    public static final String SQL_USER = "CREATE TABLE User (username text primary key, password text);";
    public static final String TAG = "UserDAO";

    public UserDAO(Context context){
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public UserDAO(DatabaseHelper databaseHelper) {

    }

    public int insertUser(User user){
        ContentValues values = new ContentValues();
        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        try{
            if (db.insert(TABLE_USER,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }

    public List<User> getAllUser(){
        List<User> userList = new ArrayList<>();
        Cursor c = db.query(TABLE_USER,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            User user = new User();
            user.setUsername(c.getString(0));
            user.setPassword(c.getString(1));
            userList.add(user);
            Log.d("++++",user.toString());
            c.moveToNext();
        }
        c.close();
        return userList;
    }


    public User getUser(String username){
        List<User> userList = new ArrayList<>();
        User user = new User();
        Cursor c = db.query(TABLE_USER,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            user.setUsername(c.getString(0));
            user.setPassword(c.getString(1));
            userList.add(user);
            Log.d("++++",user.toString());
            c.moveToNext();
        }
        c.close();
        return user;
    }


    public int changePasswordUser(User user){
        ContentValues values = new ContentValues();
        values.put("username",user.getUsername());
        values.put("password",user.getPassword());
        int relust= db.update(TABLE_USER,values,"Username = ?",new String[]{user.getUsername()});
        if (relust == 0){
            return -1;
        }
        return 1;
    }

    public int deleteUserById(String username,String password){
        int result = db.delete(TABLE_USER,"Username = ? and Password = ?",
                new String[]{username,password});
        if (result == 0){
            return -1;
        }
        return -1;
    }

}
