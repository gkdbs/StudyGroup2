package com.example.studygroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class bottomAdapter extends FragmentPagerAdapter {
    public bottomAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int postion) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
