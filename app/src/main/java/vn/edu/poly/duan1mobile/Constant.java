package vn.edu.poly.duan1mobile;

public interface Constant {

    //User
    String TABLE_USER = "USER";
    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";

    String CREATE_TABLE_USER = "CREATE TABLE" + TABLE_USER + "(" +
            COLUMN_USERNAME + "VARCHAR PRIMARI KEY ," +
            COLUMN_PASSWORD + "VARCHAR"+ ")";


    //Cat
    String TABLE_CAT = "CAT";
    String COLUMN_NAME_CAT = "Name";
    String COLUMN_CHARACTERISTIC_CAT = "Characteristics";
    String COLUMN_PRICE_CAT = "Price";
    String COLUMN_LINK_CAT = "Link";

    String CREATE_TABLE_CAT = "CREATE TABLE" + TABLE_CAT + "(" +
            COLUMN_NAME_CAT + "VARCHAR PRIMARY KEY ," +
            COLUMN_CHARACTERISTIC_CAT + "VARCHAR ,"+
            COLUMN_PRICE_CAT + "VARCHAR ,"+
            COLUMN_LINK_CAT + "VARCHAR " + ")";

//Dog
    String TABLE_DOG = "DOG";
    String COLUMN_NAME_DOG = "Name";
    String COLUMN_CHARACTERISTIC_DOG = "Characteristics";
    String COLUMN_PRICE_DOG = "Price";
    String COLUMN_LINK_DOG = "Link";

    String CREATE_TABLE_DOG = "CREATE TABLE" + TABLE_DOG + "(" +
            COLUMN_NAME_DOG + "VARCHAR PRIMARY KEY ," +
            COLUMN_CHARACTERISTIC_DOG + "VARCHAR ,"+
            COLUMN_PRICE_DOG + "VARCHAR ,"+
            COLUMN_LINK_DOG + "VARCHAR " + ")";


//Mouse
    String TABLE_MOUSE = "MOUSE";
    String COLUMN_NAME_MOUSE = "Name";
    String COLUMN_CHARACTERISTIC_MOUSE = "Characteristics";
    String COLUMN_PRICE_MOUSE = "Price";
    String COLUMN_LINK_MOUSE = "Link";

    String CREATE_TABLE_MOUSE = "CREATE TABLE" + TABLE_MOUSE + "(" +
            COLUMN_NAME_MOUSE + "VARCHAR PRIMARY KEY ," +
            COLUMN_CHARACTERISTIC_MOUSE + "VARCHAR ,"+
            COLUMN_PRICE_MOUSE + "VARCHAR ,"+
            COLUMN_LINK_MOUSE + "VARCHAR " + ")";


//Andmore
    String TABLE_ANDMORE = "ANDMORE";
    String COLUMN_NAME_ANDMORE  = "Name";
    String COLUMN_CHARACTERISTIC_ANDMORE = "Characteristics";
    String COLUMN_PRICE_ANDMORE = "Price";
    String COLUMN_LINK_ANDMORE = "Link";

    String CREATE_TABLE_ANDMORE = "CREATE TABLE" + TABLE_ANDMORE + "(" +
            COLUMN_NAME_ANDMORE + "VARCHAR PRIMARY KEY ," +
            COLUMN_CHARACTERISTIC_ANDMORE + "VARCHAR ,"+
            COLUMN_PRICE_ANDMORE + "VARCHAR ,"+
            COLUMN_LINK_ANDMORE + "VARCHAR " + ")";

}


