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
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    NavigationView.OnNavigationItemSelectedListener,FragmentCallback {

        // 프레임레이아웃 안에다가 프래그먼트 추가할려고
        Fragmentdrawer_1 f1;
        Fragment7_2 f2;
        Fragment7_3 f3;

        Toolbar toolbar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity7_main);

            toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);  // 화면에 툴바를 설정 할려면! 꼭 넣주기


            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);




            // 프래그먼트 생성
            f1 = new Fragment7_1();
            f2 = new Fragment7_2();
            f3 = new Fragment7_3();
            // 프래임레이아웃에 프래그먼트 추가하는 방법
            getSupportFragmentManager().beginTransaction().add(R.id.container, f1).commit();


        }

        // 이거 인터페이스 만들어서 사용함
        @Override
        public void onFragmentSelected(int position, Bundle bundle) {
            Fragment curenFragment =null;
            if(position == 0){
                curenFragment = f1;
                toolbar.setTitle("첫번째 화면");
            }else if(position ==1){
                curenFragment =f2;
                toolbar.setTitle("두번째 화면");
            }else if(position ==2){
                curenFragment = f3;
                toolbar.setTitle("셋번째 화면");
            }
            // 지정한 걸로 화면 보여줄려고 한다면!!
            getSupportFragmentManager().beginTransaction().replace(R.id.container, curenFragment).commit();
        }

        @Override
        public void onBackPressed() {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.activity7, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.nav_0) {
                Toast.makeText(this,"첫번째11",Toast.LENGTH_LONG).show();
                onFragmentSelected(0,null);
            } else if (id == R.id.nav_1) {
                Toast.makeText(this,"두번째22",Toast.LENGTH_LONG).show();
                onFragmentSelected(1,null);
            } else if (id == R.id.nav_2) {
                Toast.makeText(this,"셋번째33",Toast.LENGTH_LONG).show();
                onFragmentSelected(2,null);
            } else if (id == R.id.xxxx1) {
                // 확인 할려고( 어떻게 나오는지 )
            } else if (id == R.id.xxxx2) {
                // 확인 할려고( 어떻게 나오는지 )
            }

            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }
    }

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
        startActivity(new Intent(this,DrawerLayout.class));
    }

    public void click_dropdown(View view) {

    }

    public void click_search(View view) {
        startActivity(new Intent(this,Searchbtn.class));
    }

    public void clickplace(View view) {
        startActivity(new Intent(this,KakaoMap.class));
    }
}
