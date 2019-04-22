package com.example.GonkDroids.StudyU;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class SectionsPageAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>(); //Keeps track of fragments
    private final List<String> mFragmentTitleList = new ArrayList<>(); //Keeps track of names of fragments

    //Adds fragments to fragment list
    public void addFragment(Fragment frag, String title){
        mFragmentList.add(frag);
        mFragmentTitleList.add(title);
    }

    //Default constructor
    public SectionsPageAdapter (FragmentManager fm){
        super(fm);
    }

    //get fragment title
    @Override
    public CharSequence getPageTitle (int position){
        return mFragmentTitleList.get(position);
    }

    //get item from fragment list
    @Override
    public Fragment getItem (int position){
        return mFragmentList.get(position);
    }

    //get size of fragment list
    @Override
    public int getCount(){
        return mFragmentList.size();
    }
}
