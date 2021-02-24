package com.example.studygroup;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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

    public Searchbtn() {
    }

    public Searchbtn(EditText et_find, ImageView iv_check, ImageView iv_backarrow) {
        this.et_find = et_find;
        this.iv_check = iv_check;
        this.iv_backarrow = iv_backarrow;
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}