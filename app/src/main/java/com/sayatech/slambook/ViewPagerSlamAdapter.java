package com.sayatech.slambook;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ViewPagerSlamAdapter extends FragmentPagerAdapter {

    Fragment currentFragment;

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public ViewPagerSlamAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new KnowMe();
        } else if (position == 1) {
            return new TalkFaves();
        } else { //2
            return new BrowsingMore();
        }
    }

    @Override
    public int getCount() {
        return 3; //no of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Know Me!";
        }else if (position == 1){
            return "Let's talk Faves!";
        }else {
            return "Browsing More!";
        }
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            currentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }
}