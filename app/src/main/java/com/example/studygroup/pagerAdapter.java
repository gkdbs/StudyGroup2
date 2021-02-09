package com.example.studygroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;



class Adapter extends PagerAdapter {

    private int[] images = {R.drawable.studygroup02, R.drawable.studygroup03, R.drawable.studygroup04, R.drawable.studygroup05, R.drawable.studygroup06};
    LayoutInflater inflater;
    Context context;


    public Adapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View)object);
    }


    @Override
    public Object instantiateItem( ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_pager_adapter, container, false);
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        imageView.setImageResource(images[position]);
        container.addView(v);
        return v;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.invalidate();

    }
}
