package com.example.a108810039_hw7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img_donut = (ImageView)findViewById(R.id.imageView);
        ImageView img_froyo = (ImageView)findViewById(R.id.imageView2);
        ImageView img_icecream = (ImageView)findViewById(R.id.imageView3);

        img_donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, donutCircle.class));
            }
        });

        img_froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, froyoCircle.class));
            }
        });

        img_icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, icecreamCircle.class));
            }
        });
    }
}