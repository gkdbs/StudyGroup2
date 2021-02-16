package com.example.studygroup;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText et_Email, et_Password;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.et_Email_id);
        findViewById(R.id.et_input_id);
        findViewById(R.id.et_input_password);

    }

    public void clickbnt_login(View view) {
    }

    public void clickbnt_joinmembership(View view) {
    }

    public void clickbtn_naverlogin(View view) {
    }
}