package vn.edu.poly.duan1mobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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

import vn.edu.poly.duan1mobile.adapter.AndMoreAdapter;
import vn.edu.poly.duan1mobile.model.Andmore;
import vn.edu.poly.duan1mobile.sqlitedao.AndMoreDAO;

public class AndMoreActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView btnav;
    ListView lvAndMore;
    public static List<Andmore> arrayList = new ArrayList<>();

    AndMoreAdapter moreAdapter= null;
    AndMoreDAO moreDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_more);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawblelayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.change, R.string.cancel);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        btnav = findViewById(R.id.btNav);
        btnav.setNavigationItemSelectedListener(this);
        lvAndMore = findViewById(R.id.lvCat);
        moreDAO = new AndMoreDAO(AndMoreActivity.this);
        arrayList = moreDAO.getAllCat();
        moreAdapter = new AndMoreAdapter(this, arrayList);
        lvAndMore.setAdapter(moreAdapter);
        lvAndMore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Andmore andmore = (Andmore) parent.getItemAtPosition(position);
                Intent intent = new Intent(AndMoreActivity.this, AddAndMoreActivity.class);
                Bundle b = new Bundle();
                b.putString("NAMEPET", andmore.getEdtNamepet());
                b.putString("CHARACTERISTICS", andmore.getEdtCharacteristics());
                b.putString("PRICE", andmore.getEdtPrice());
                b.putString("LINK", andmore.getEdtLink());
                intent.putExtras(b);

                startActivity(intent);

            }
        });
// TextFilter
        lvAndMore.setTextFilterEnabled(true);
        EditText edSeach = (EditText) findViewById(R.id.edtSe);
        edSeach.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {
                System.out.println("Text [" + s + "] - Start [" + start + "] - Before [" + before + "] - Count [" + count + "]");
                if (count < before) {
                    moreAdapter.resetData();
                }
                moreAdapter.getFilter().filter(s.toString());
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
        startActivity(new Intent(AndMoreActivity.this, AddAndMoreActivity.class));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawblelayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_menu) {
            startActivity(new Intent(AndMoreActivity.this, HomeActivity.class));
        }
        else if (id == R.id.nav_changepass) {
                startActivity(new Intent(AndMoreActivity.this, ChangepassActivity.class));
        }
        else if (id == R.id.nav_logout) {
                startActivity(new Intent(AndMoreActivity.this, LoginActivity.class));
        }
        else if (id == R.id.nav_edit) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AndMoreActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
                builder.setTitle("Bạn có chắc chắn muốn thoát khỏi App");
                builder.setMessage("Hãy lựa chọn bên dưới để xác nhận");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
        }


        DrawerLayout drawer = findViewById(R.id.drawblelayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    }

