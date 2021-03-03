package com.example.studygroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
public class art extends AppCompatActivity {

    ArrayList<Item> items= new ArrayList<>();
    RecyclerView recyclerView;
    MyNestAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        recyclerView= findViewById(R.id.recycler);
        adapter= new MyNestAdapter(art.this, items);
        recyclerView.setAdapter(adapter);


        items.add( new Item("강서미술학원","강서에서 제일 유명한 미술학원", R.drawable.art01, R.drawable.img01)  );
        items.add( new Item("강남미술학원","역사와 전통의 강남미술학원", R.drawable.art02, R.drawable.img02)  );
        items.add( new Item("강북미술학원","예술대입 전문학원", R.drawable.art03, R.drawable.img03)  );
        items.add( new Item("강동미술학원","예대 전문학원", R.drawable.art04, R.drawable.img04)  );
        items.add( new Item("서울미술학원","인서울 할꺼면 서울미술학원", R.drawable.art05, R.drawable.img05)  );
        items.add( new Item("우리들미술학원","행복하고 즐거운 미술", R.drawable.art06, R.drawable.img06)  );


        //리사이클러뷰는 리스트뷰와 다르게 아이템클릭 리스너가 없음.
        //아답터에서 itemView 에 직접 클릭리스너 설정해 주어야 함.

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
