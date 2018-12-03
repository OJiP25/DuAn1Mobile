package vn.edu.poly.duan1mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView imgdog,imgcat,imgmouse,imgandmore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intView();

        imgdog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,DogActivity.class));
            }
        });

        imgcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,CatActivity.class));
            }
        });

        imgmouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MouseActivity.class));
            }
        });

        imgandmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AndMoreActivity.class));
            }
        });

    }

    private void intView() {
        imgdog = findViewById(R.id.imgDog);
        imgcat = findViewById(R.id.imgCat);
        imgmouse = findViewById(R.id.imgMouse);
        imgandmore = findViewById(R.id.imgFish);
    }
}
