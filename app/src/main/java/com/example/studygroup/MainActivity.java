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



    ArrayList<Integer> imgIds = new ArrayList<Integer>();
    MyAdapter adapter;
    ViewPager Pager;

    BottomNavigationView bnv;
    FragmentManager fragmentManager;
    Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fragmentManager.beginTransaction();

                switch (item.getItemId()) {
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

    public void clickplace(View view) {
        startActivity(new Intent(this, KakaoMap.class));
    }
}
