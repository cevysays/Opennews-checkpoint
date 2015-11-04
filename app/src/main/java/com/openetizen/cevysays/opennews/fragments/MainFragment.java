package com.openetizen.cevysays.opennews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openetizen.cevysays.opennews.R;
import com.openetizen.cevysays.opennews.tabs.SlidingTabLayout;


public class MainFragment extends Fragment {

    public static final int CATEGORY_ONE = 0;

    private ViewPager mPager;
    private SlidingTabLayout mTabs;


    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mPager = (ViewPager) view.findViewById(R.id.pager);
        mTabs = (SlidingTabLayout) view.findViewById(R.id.tabs);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*mPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTabs.setViewPager(mPager);*/

    }

/*    class MyPagerAdapter extends FragmentPagerAdapter {
        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            //tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int num) {

            Fragment fragment = null;
            switch (num) {

                case CATEGORY_ONE:
                    fragment = new CategoryOneFragment();
                    break;

            }
            return fragment;
        }

*//*        @Override
        public CharSequence getPageTitle(int position) {
            return getResources().getStringArray(R.array.tabs)[position];
//            return tabs[position];
        }*//*

        @Override
        public int getCount() {

            return 3;
        }

    }*/
}
