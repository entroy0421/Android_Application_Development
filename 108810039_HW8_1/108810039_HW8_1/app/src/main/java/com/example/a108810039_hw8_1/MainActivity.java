package com.example.a108810039_hw8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Integer level = 0;
    ImageView battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        battery = (ImageView)findViewById(R.id.imageView2);
    }

    public void add(View view) {
        if (level >= 0 && level < 4)
            level += 1;

        battery.setImageLevel(level);
    }

    public void minus(View view) {
        if (level > 0 && level <= 4)
            level -= 1;

        battery.setImageLevel(level);
    }
}