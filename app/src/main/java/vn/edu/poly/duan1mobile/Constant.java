package vn.edu.poly.duan1mobile;

public interface Constant {
    String TABLE_USER = "USER";
    String COLUMN_USERNAME = "Username";
    String COLUMN_PASSWORD = "Password";

    String CREATE_TABLE_USER = "CREATE TABLE" + TABLE_USER + "(" +
            COLUMN_USERNAME + "VARCHAR PRIMARI KEY ," +
            COLUMN_PASSWORD + "VARCHAR"+ ")";

}
