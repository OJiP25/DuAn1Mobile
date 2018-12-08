package vn.edu.poly.duan1mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.model.Andmore;
import vn.edu.poly.duan1mobile.sqlitedao.AndMoreDAO;

public class AddAndMoreActivity extends AppCompatActivity{
    EditText edtNamepet;
    EditText edtcharac;
    EditText edtPrice;
    EditText edtLink;
    AndMoreDAO moreDAO;
    List<Andmore> mores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            edtNamepet.setText(b.getString("NAMEPET"));
            edtcharac.setText(b.getString("CHARACTERISTICS"));
            edtPrice.setText(b.getString("PRICE"));
            edtLink.setText(b.getString("LINK"));

        }

    }

    public void initView() {
        setContentView(R.layout.share_pet);
        edtNamepet = (EditText) findViewById(R.id.edtNamepets);
        edtcharac = (EditText) findViewById(R.id.edtcharacteristicss);
        edtPrice = (EditText) findViewById(R.id.edtprices);
        edtLink = (EditText) findViewById(R.id.edtlinks);

    }

    public void sharepet(View view) {
        if (edtNamepet.getText().length() == 0 ||
                edtcharac.getText().length() == 0 ||
                edtPrice.getText().length() == 0 ||
                edtcharac.getText().length() == 0) {
            Toast.makeText(this, "Bạn phải nhập đủ thông tin ", Toast.LENGTH_SHORT).show();

        } else {
            moreDAO = new AndMoreDAO(AddAndMoreActivity.this);
            Andmore andmore = new
                    Andmore(edtNamepet.getText().toString(), edtcharac.getText().toString(),
                    edtPrice.getText().toString(), edtLink.getText().toString()
            );
            try {
                if (moreDAO.insertCat(andmore) > 0) {
                    Toast.makeText(getApplicationContext(), "Hoàn thành",
                            Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(AddAndMoreActivity.this, AndMoreActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        }

    }


    public void Catcancel(View view) {
        startActivity(new Intent(AddAndMoreActivity.this,AndMoreActivity.class));
    }
}
