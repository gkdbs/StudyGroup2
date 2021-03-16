package com.example.studygroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    ArrayList<Integer> imgIds= new ArrayList<Integer>();
    MyAdapter adapter;
    ViewPager Pager;

    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments= new Fragment[4];

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        imgIds.add(R.drawable.studygroup01);
        imgIds.add(R.drawable.studygroup02);
        imgIds.add(R.drawable.studygroup03);
        imgIds.add(R.drawable.studygroup04);
        imgIds.add(R.drawable.studygroup05);



        Pager= findViewById(R.id.pager);
        adapter= new MyAdapter(this, imgIds);
        Pager.setAdapter(adapter);

        fragments[0]= new Tab1Fragment();
        fragments[1]= new Tab2Fragment();
        fragments[2]= new Tab3Fragment();
        fragments[3]= new Tab3Fragment();

        fragmentManager= getSupportFragmentManager();

        FragmentTransaction tran= fragmentManager.beginTransaction();
        tran.add(R.id.map_container, fragments[0]);
        tran.add(R.id.map_container, fragments[1]);
        tran.add(R.id.map_container, fragments[2]);
        tran.add(R.id.map_container, fragments[3]);
        tran.show(fragments[0]);
        tran.hide(fragments[1]);
        tran.hide(fragments[2]);
        tran.hide(fragments[3]);
        tran.commit();

        bnv= findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran= fragmentManager.beginTransaction();

                switch (item.getItemId()){
                    case R.id.bnv_home:
                        tran.show(fragments[0]);
                        tran.hide(fragments[1]);
                        tran.hide(fragments[2]);
                        tran.hide(fragments[3]);
                        break;

                    case R.id.bnv_like:
                        tran.hide(fragments[0]);
                        tran.show(fragments[1]);
                        tran.hide(fragments[2]);
                        tran.hide(fragments[3]);
                        break;

                    case R.id.bnv_input:
                        tran.hide(fragments[0]);
                        tran.hide(fragments[1]);
                        tran.show(fragments[2]);
                        tran.hide(fragments[3]);
                        break;

                    case R.id.bnv_notification:
                        tran.hide(fragments[0]);
                        tran.hide(fragments[1]);
                        tran.hide(fragments[2]);
                        tran.show(fragments[3]);
                        break;
                }

                tran.commit();

                return true;
            }
        });



        drawerLayout = findViewById(R.id.drawer_layout);
        }
        public void ClickMenu(View view){
            openDrawer(drawerLayout);
        }

        public static void openDrawer(DrawerLayout drawerLayout){
            drawerLayout.openDrawer(GravityCompat.START);
        }

        public void ClickLogo(View view){
            closeDrawer(drawerLayout);
        }

        public static void closeDrawer(DrawerLayout drawerLayout){
            if (drawerLayout.isDrawerOpen(GravityCompat.START)){

                drawerLayout.closeDrawer(GravityCompat.START);
            }
        }
        public void ClickHome(View view){
            recreate();
        }

        public void ClickDashboard(View view){
            redirectActivity(this,Dashboard.class);
        }

        public void ClickAboutUs(View view){
            redirectActivity(this,AboutUs.class);
        }

        public void ClickLogout(View view){
            logout(this);
        }

        //public void ClickLogout(View view){
        //  ClickLogout(drawerLayout);
        //}


    public void clickLogin(View view) {
        //카카오 계정으로 로그인하기
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {//로그인 정보객체가 있다면
                    Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
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
                                Toast.makeText(MainActivity.this, "사용자 정보 요청 실패 : ", Toast.LENGTH_SHORT).show();
                            }

                            return null;
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                }

                return null;
            }
        });
    }


    public static void logout(Activity activity){
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Logout");
            builder.setMessage("정말 로그아웃 하시겠습니까?");
            builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    activity.finishAffinity();
                    System.exit(0);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.show();

        }

        public static void redirectActivity(Activity activity, Class aClass){
            Intent intent = new Intent(activity,aClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        }

        @Override
        protected void onPause() {
            super.onPause();
            closeDrawer(drawerLayout);
        }

    public void clickPrev(View view) {
        int index= Pager.getCurrentItem()-1;
        Pager.setCurrentItem(index,true);
    }

    public void clickNext(View view) {
        int index= Pager.getCurrentItem()+1;
        Pager.setCurrentItem(index, true);
    }

    public void clickbtn_art(View view) {
        startActivity(new Intent(this,art.class));
    }

    public void clickLinear(View view) {
        LinearLayoutManager layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clickGrid(View view) {
        GridLayoutManager layoutManager= new GridLayoutManager(this, 2);//2칸짜리 격자배치
        recyclerView.setLayoutManager(layoutManager);
    }
}
