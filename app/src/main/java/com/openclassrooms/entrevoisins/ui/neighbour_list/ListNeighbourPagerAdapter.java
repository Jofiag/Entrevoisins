package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragmentList = new ArrayList<>();
    List<String> mFragmentTitles = new ArrayList<>();

    public ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    /**
     * get the number of pages
     * @return
     */
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void addFragment(Fragment fragment, String name) {
        mFragmentList.add(fragment);
        mFragmentTitles.add(name);
    }
}