package com.example.studygroup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;


public class LoginActivity extends AppCompatActivity {
    EditText et_id, et_pass;
    Button btn_login,btn_register;
    ImageButton kakaologinbtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("keyHash", keyHash);

        et_id=findViewById(R.id.et_id);
        et_pass=findViewById(R.id.et_pass);
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {//로그인 버튼
            @Override
            public void onClick(View v) {
                String userID=et_id.getText().toString();
                String userPass=et_pass.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jasonObject=new JSONObject(response);
                            boolean success=jasonObject.getBoolean("success");
                            if (success) {
                                String userID = jasonObject.getString("userID");
                                String userPass = jasonObject.getString("userPassword");
                                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("log", "User");
                                intent.putExtra("userID", userID);
                                startActivity(intent);
                            }


                            else{
                                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                                return;

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest=new LoginRequest(userID,userPass,responseListener);
                RequestQueue queue= Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });

    }

    public void clickLogin(View view) {
        //카카오 계정으로 로그인하기
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {//로그인 정보객체가 있다면
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    //로그인한 계정 정보 얻어오기
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {

                            if (user != null) {
                                long id = user.getId(); //카카오 회원번호

                                //필수동의 항목의 회원프로필 정보 [닉네임/프로필 이미지 Url]
                                String nickname = user.getKakaoAccount().getProfile().getNickname();
                                String profileImageUrl = user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                //선택항목으로 지정한 Email
                                String email = user.getKakaoAccount().getEmail();


                                //다음에 접속할때 로그인 다시 하지 않으려면 shardPreference에 로그인정보를 저장해두고 불러오도록 코드 추가..


                            } else {
                                Toast.makeText(LoginActivity.this, "사용자 정보 요청 실패 : ", Toast.LENGTH_SHORT).show();
                            }

                            return null;
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        });
    }


    public void clickLogout (View view){
        //로그아웃 요청
        UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
            @Override
            public Unit invoke(Throwable throwable) {
                if (throwable != null)
                    Toast.makeText(LoginActivity.this, "로그아웃 실패", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(LoginActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }


    public void clickmenu(View view) {
        startActivity( new Intent(this, MainActivity.class) );
        finish();
    }
}