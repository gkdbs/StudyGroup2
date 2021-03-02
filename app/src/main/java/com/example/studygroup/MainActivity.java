package com.example.studygroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.kakao.util.maps.helper.Utility;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;

    BottomNavigationView bnv;

    ArrayList<Integer> imgIds = new ArrayList<Integer>();
    MyAdapter adapter;
    ViewPager Pager;

    FragmentManager fragmentManager;
    Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);


        String keyHash = Utility.getKeyHash(this);


        bnv = findViewById(R.id.bnv);
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame, new Tab1Fragment()).commit();

        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.item_bnv_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Tab1Fragment()).commit();
                        break;
                    case R.id.item_bnv_like:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Tab2Fragment()).commit();
                        break;
                    case R.id.item_bnv_input:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Tab3Fragment()).commit();
                        break;
                    case R.id.item_bnv_notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Tab4Fragment()).commit();
                        break;
                }
                return true;
            }
        });


        imgIds.add(R.drawable.studygroup01);
        imgIds.add(R.drawable.studygroup02);
        imgIds.add(R.drawable.studygroup03);
        imgIds.add(R.drawable.studygroup04);
        imgIds.add(R.drawable.studygroup05);


        Pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this, imgIds);
        Pager.setAdapter(adapter);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab3Fragment();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);
        tran.add(R.id.container, fragments[1]);
        tran.add(R.id.container, fragments[2]);
        tran.add(R.id.container, fragments[3]);
        tran.show(fragments[0]);
        tran.hide(fragments[1]);
        tran.hide(fragments[2]);
        tran.hide(fragments[3]);
        tran.commit();


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
        ClickLogout(drawerLayout);
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
        int index = Pager.getCurrentItem() - 1;
        Pager.setCurrentItem(index, true);
    }

    public void clickNext(View view) {
        int index = Pager.getCurrentItem() + 1;
        Pager.setCurrentItem(index, true);
    }

}
