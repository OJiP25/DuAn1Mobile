package vn.edu.poly.duan1mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.duan1mobile.adapter.DogAdapter;
import vn.edu.poly.duan1mobile.adapter.MouseAdapter;
import vn.edu.poly.duan1mobile.model.Cat;
import vn.edu.poly.duan1mobile.model.Dog;
import vn.edu.poly.duan1mobile.model.Mouse;
import vn.edu.poly.duan1mobile.sqlitedao.DogDAO;
import vn.edu.poly.duan1mobile.sqlitedao.MouseDAO;

public class MouseActivity extends AppCompatActivity {

    ListView lvMouse;
    public static List<Mouse> arrayList = new ArrayList<>();

    MouseAdapter mouseAdapter= null;
    MouseDAO mouseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse);
        lvMouse = findViewById(R.id.lvCat);
        mouseDAO = new MouseDAO(MouseActivity.this);
        arrayList = mouseDAO.getAllCat();
        mouseAdapter = new MouseAdapter(this, arrayList);
        lvMouse.setAdapter(mouseAdapter);
        lvMouse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Cat cat = (Cat) parent.getItemAtPosition(position);
                Intent intent = new Intent(MouseActivity.this, AddDogActivity.class);
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
        lvMouse.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edtSe);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    mouseAdapter.resetData();
                }
                mouseAdapter.getFilter().filter(s.toString());
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


    public void addMouse(View view) {
        startActivity(new Intent(MouseActivity.this, AddMouseActivity.class));
    }
}
