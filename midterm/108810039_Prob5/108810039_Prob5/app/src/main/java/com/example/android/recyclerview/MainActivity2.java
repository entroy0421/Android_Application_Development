package com.example.android.recyclerview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageView imageview = (ImageView)findViewById(R.id.imageView);
        imageview.setImageResource(R.drawable.ic_launcher_background);

        Intent intent = getIntent();
        String str = intent.getStringExtra("element");

        TextView textview = (TextView)findViewById(R.id.textView);
        textview.setText(str);
    }
}