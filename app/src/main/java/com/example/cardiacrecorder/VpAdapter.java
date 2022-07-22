package com.example.cardiacrecorder;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import java.util.ArrayList;

/**
 * VpAdapter will do some operation on ListData
 */
public class VpAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment>fragmentArrayList = new ArrayList<>();
    private final ArrayList<String>fragmentTitle = new ArrayList<>();
    public VpAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * this will show the fragment on basis of a particular value
     * @param i
     * @return
     * a fragmentArrayList on position i
     */
    @Override
    public Fragment getItem(int i) {
        return fragmentArrayList.get(i);
    }

    /**
     * give the size of fragmentArrayList
     * @return
     * number of count
     */
    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    /**
     * add fragment, string of fragmentArrayList, fragmentTitle
     * @param fragment Fragment type parameter
     * @param title String type parameter
     */
    public void addFragment(Fragment fragment,String title)
    {
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);
    }

    /**
     * this method gives us the fragmentTitle of a particular position
     * which is passed by parameter
     * @param position
     * @return CharSequence datatype
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragmentTitle.get(position);
    }
}