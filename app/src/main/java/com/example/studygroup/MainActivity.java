package com.example.studygroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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


    public void clickPrev(View view) {
        int index = Pager.getCurrentItem() - 1;
        Pager.setCurrentItem(index, true);
    }

    public void clickNext(View view) {
        int index = Pager.getCurrentItem() + 1;
        Pager.setCurrentItem(index, true);
    }

    public void click_dehaze(View view) {

    }

    public void click_dropdown(View view) {

    }

    public void click_search(View view) {
        startActivity(new Intent(this,Searchbtn.class));
    }

    public void click_place_btn(View view) {
        startActivity(new Intent(this,KakaoMap.class));
    }
}
