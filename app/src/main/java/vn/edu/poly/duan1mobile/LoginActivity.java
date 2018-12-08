package vn.edu.poly.duan1mobile;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.annotation.Nullable;


import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity{



    EditText edtUsername,edtPassword;
    CheckBox chkPass;

    public String ssUser,ssPass;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intView();


}



    public void intView(){
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkPass = findViewById(R.id.chkPass);

    }




    public void CreateAccount(View view) {
        startActivity(new Intent(LoginActivity.this,AddUserActivity.class));
    }

    public void SingIn(View view) {
        ssUser = edtUsername.getText().toString().trim();
        ssPass = edtPassword.getText().toString().trim();
        boolean check = chkPass.isChecked();
        if (ssUser.isEmpty() || ssPass.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        } else {
            if(ssUser.equalsIgnoreCase("admin") && ssPass.equalsIgnoreCase("admin")) {
                rememberUser(ssUser, ssPass,check);
                finish();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }
    public void restore() {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        boolean check = pref.getBoolean("REMEMBER",false);
        if (check) {
            String user = pref.getString("USERNAME","");
            String pass = pref.getString("PASSWORD","");
            edtUsername.setText(user);
            edtPassword.setText(pass);

        }
        chkPass.setChecked(check);
    }


}

