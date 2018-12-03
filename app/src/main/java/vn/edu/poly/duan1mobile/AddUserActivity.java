package vn.edu.poly.duan1mobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.poly.duan1mobile.model.User;
import vn.edu.poly.duan1mobile.sqlitedao.UserDAO;

public class AddUserActivity extends AppCompatActivity{

     EditText edtUsername,edtPassword,edtRetypepass;
     Button btnCancel,btnCreate;
     UserDAO userDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        intView();
    }

    public void intView(){
        edtUsername = (EditText)findViewById(R.id.edtUser);
        edtPassword = (EditText)findViewById(R.id.edtPass);
        edtRetypepass = (EditText)findViewById(R.id.edtRetypePass);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCreate = (Button)findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddUserActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void addUser(View view){
        userDAO = new UserDAO(this);
        User user = new User(edtUsername.getText().toString(),
                edtPassword.getText().toString());
        try{
            if (validateFrom()>0){
                if (userDAO.insertUser(user)>0){
                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception ex){
            Log.e("Error",ex.toString());
        }
    }

    public int validateFrom(){
        int check = 1;
        if (edtUsername.getText().length() == 0 ||
                edtPassword.getText().length() == 0 ||
                edtRetypepass.getText().length() == 0){
            Toast.makeText(this,"You must enter enough information",Toast.LENGTH_SHORT).show();
            check = -1;
        }else{
            String pass = edtPassword.getText().toString();
            String retypepass = edtRetypepass.getText().toString();
            if (!pass.equals(retypepass)){
                Toast.makeText(this,"Passwords are not the same",Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
