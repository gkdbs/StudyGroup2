package com.example.studygroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class googlemap extends FragmentActivity {


    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_googlemap);

        editText = findViewById(R.id.et_googlemap);
        button = findViewById(R.id.btn_find_address);
    }
}