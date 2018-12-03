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

import vn.edu.poly.duan1mobile.model.Cat;
import vn.edu.poly.duan1mobile.sqlitedao.CatDAO;

public class AddCatActivity extends AppCompatActivity {
    EditText edtNamepet;
    EditText edtcharac;
    EditText edtPrice;
    EditText edtLink;
    CatDAO catDAO;
    List<Cat> cats = new ArrayList<>();

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
            catDAO = new CatDAO(AddCatActivity.this);
            Cat cat = new
                    Cat(edtNamepet.getText().toString(), edtcharac.getText().toString(),
                    edtPrice.getText().toString(), edtLink.getText().toString()
            );
            try {
                if (catDAO.insertCat(cat) > 0) {
                    Toast.makeText(getApplicationContext(), "Hoàn thành",
                            Toast.LENGTH_SHORT).show();

                    finish();
                    startActivity(new Intent(AddCatActivity.this, CatActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                Log.e("Error", ex.toString());
            }
        }

    }
    public int checkPositionTypeBook(String strTypeBook) {
        for (int i = 0; i < cats.size(); i++) {
            if (strTypeBook.equals(cats.get(i).getEdtNamepet())) {
                return i;
            }
        }
        return 0;
    }
}

