package com.example.a108810039_prob1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    public String spinner_string;
    public String name_string;
    public String gender_string;
    public String hobby_string = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,    //對應的Context
                        R.array.spinner,                             //資料選項內容
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        setListener();

        EditText name_edittext = (EditText)findViewById(R.id.editTextTextPersonName);
        name_string = name_edittext.getText().toString();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                // 2131296595
                if (checkedId == 2131296595) {
                    Toast toast = Toast.makeText(MainActivity.this, "Male",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    gender_string = "Male";
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Female",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    gender_string = "Female";
                }
            }
        });

        CheckBox sport_checkbox = (CheckBox)findViewById(R.id.checkBox);
        CheckBox movie_checkbox = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox reading_checkbox = (CheckBox)findViewById(R.id.checkBox3);

        sport_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked)
                    onclickSport();
                hobby_string += "Sport";
            }
        }
        );

        movie_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked)
                    onclickMovie();
                hobby_string += "Movie";
            }
        }
        );

        reading_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (isChecked)
                    onclickReading();
                hobby_string += "Reading";
            }
        }
        );
    }

    private void setListener() {

        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override

            public void onItemSelected
                    (AdapterView<?> adapterView, View view, int position, long l) {
                String msg = adapterView.getItemAtPosition(position).toString();
                spinner_string = msg;
                Toast toast = Toast.makeText(MainActivity.this, msg,
                        Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onclickSport() {
        Toast toast = Toast.makeText(MainActivity.this, "Sport",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onclickMovie() {
        Toast toast = Toast.makeText(MainActivity.this, "Movie",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onclickReading() {
        Toast toast = Toast.makeText(MainActivity.this, "Reading",
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onclickSubmit(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, MainActivity2.class);

        Bundle bundle = new Bundle();
        bundle.putString("name", name_string);
        bundle.putString("gender", gender_string);
        bundle.putString("department", spinner_string);
        bundle.putString("hobby", hobby_string);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}