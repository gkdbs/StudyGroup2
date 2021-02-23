package com.example.studygroup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Searchbtn extends AppCompatActivity {

    EditText et_find;
    ImageView iv_check, iv_backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbtn);

        et_find = findViewById(R.id.et_findclass);
        iv_check = findViewById(R.id.iv_check);
        iv_backarrow = findViewById(R.id.iv_backarrow);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}