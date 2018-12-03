package vn.edu.poly.duan1mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.adapter.CatAdapter;
import vn.edu.poly.duan1mobile.model.Cat;
import vn.edu.poly.duan1mobile.sqlitedao.CatDAO;

public class CatActivity extends AppCompatActivity {
    ListView lvCat;
    public static List<Cat> arrayList = new ArrayList<>();

    CatAdapter catAdapter= null;
    CatDAO catDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        lvCat = findViewById(R.id.lvCat);
        catDAO = new CatDAO(CatActivity.this);
        arrayList = catDAO.getAllCat();
        catAdapter = new CatAdapter(this, arrayList);
        lvCat.setAdapter(catAdapter);
        lvCat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cat cat = (Cat) parent.getItemAtPosition(position);
                Intent intent = new Intent(CatActivity.this, AddCatActivity.class);
                Bundle b = new Bundle();
                b.putString("NAMEPET", cat.getEdtNamepet());
                b.putString("CHARACTERISTICS", cat.getEdtCharacteristics());
                b.putString("PRICE", cat.getEdtPrice());
                b.putString("LINK", cat.getEdtLink());
                intent.putExtras(b);

                startActivity(intent);

            }
        });
// TextFilter
        lvCat.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edtSe);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    catAdapter.resetData();
                }
                catAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }


    public void addcat(View view) {
        startActivity(new Intent(CatActivity.this, AddCatActivity.class));
    }
}
