package com.example.studygroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intro extends AppCompatActivity {
    Button btn_login,btn_mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

    }

    public void introLogin(View view) {
        startActivity( new Intent(this, LoginActivity.class) );
        finish();
    }
}