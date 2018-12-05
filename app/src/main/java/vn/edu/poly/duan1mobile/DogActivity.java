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
import vn.edu.poly.duan1mobile.model.Dog;
import vn.edu.poly.duan1mobile.sqlitedao.DogDAO;

public class DogActivity extends AppCompatActivity {

    ListView lvDog;
    public static List<Dog> arrayListDog = new ArrayList<>();

    DogAdapter dogAdapter= null;
    DogDAO dogDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);
        lvDog = findViewById(R.id.lvcat);
        dogDAO = new DogDAO(DogActivity.this);
        arrayListDog = dogDAO.getAllDog();
        dogAdapter = new DogAdapter(this, arrayListDog);
        lvDog.setAdapter(dogAdapter);
        lvDog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Dog dog = (Dog) parent.getItemAtPosition(position);
                Intent intent = new Intent(DogActivity.this, AddDogActivity.class);
                Bundle b = new Bundle();
                b.putString("NAMEPET", dog.getEdtNamepet());
                b.putString("CHARACTERISTICS", dog.getEdtCharacteristics());
                b.putString("PRICE", dog.getEdtPrice());
                b.putString("LINK", dog.getEdtLink());
                intent.putExtras(b);

                startActivity(intent);

            }
        });
// TextFilter
        lvDog.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edtSe);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    dogAdapter.resetData();
                }
                dogAdapter.getFilter().filter(s.toString());
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


    public void addDog(View view) {
        startActivity(new Intent(DogActivity.this, AddDogActivity.class));
    }
}
