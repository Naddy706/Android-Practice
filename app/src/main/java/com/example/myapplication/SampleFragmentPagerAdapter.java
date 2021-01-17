package com.example.myapplication;


import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {



    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ChatFragment chatFragment= new ChatFragment();
                return  chatFragment;
            case 1:
                StatusFragment statusFragment= new StatusFragment();
                return  statusFragment;
            case 2:
               CallFragment callFragment= new CallFragment();
                return  callFragment;
            default:
                return  null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return  "Chat";
            case 1:
                return  "Status";
            case 2:
                return  "Call";
            default:
                return  null;
        }
    }

}
