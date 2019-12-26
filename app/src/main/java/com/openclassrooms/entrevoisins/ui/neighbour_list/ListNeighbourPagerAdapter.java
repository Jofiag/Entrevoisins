package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class ListNeighbourPagerAdapter extends FragmentPagerAdapter {

    static final String FAVORITE_PAGE = "favorite";
    private static final String NEIGHBOUR_PAGE = "neighbours";

    ListNeighbourPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * getItem is called to instantiate the fragment for the given page.
     */
    @Override
    public Fragment getItem(int page) {
        if (page == 1) {
            return NeighbourFragment.newInstance(FAVORITE_PAGE);
        }
        return NeighbourFragment.newInstance(NEIGHBOUR_PAGE);
    }


    @Override
    public int getCount() {
        return 2;
    }
}