package vn.edu.poly.duan1mobile;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity{

    EditText edtUsername,edtPassword;
    CheckBox chkPass;

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
    }
}

