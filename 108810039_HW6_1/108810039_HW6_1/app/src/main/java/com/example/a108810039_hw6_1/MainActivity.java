package com.example.a108810039_hw6_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        CheckBox checkbox_1 = (CheckBox) findViewById(R.id.checkBox);
        CheckBox checkbox_2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox checkbox_3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox checkbox_4 = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox checkbox_5 = (CheckBox)findViewById(R.id.checkBox5);

        String message = "Toppings: ";

        if (checkbox_1.isChecked()) {
            message += "Chocolate syrup ";
        }
        if (checkbox_2.isChecked()) {
            message += "Sprinkles ";
        }
        if (checkbox_3.isChecked()) {
            message += "Crushed nuts ";
        }
        if (checkbox_4.isChecked()) {
            message += "Cherries ";
        }
        if (checkbox_5.isChecked()) {
            message += "Orio cookie crumbles";
        }

        Toast toast = Toast.makeText(this, message,
                Toast.LENGTH_SHORT);
        toast.show();
    }
}