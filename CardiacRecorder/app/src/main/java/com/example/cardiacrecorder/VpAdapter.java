package com.example.cardiacrecorder;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment>fragmentArrayList = new ArrayList<>();
    private final ArrayList<String>fragmentTitle = new ArrayList<>();
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment,String title)
    {
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentTitle.get(position);
    }
}
