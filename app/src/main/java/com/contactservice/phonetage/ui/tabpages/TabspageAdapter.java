package com.contactservice.phonetage.ui.tabpages;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.contactservice.phonetage.R;
import com.contactservice.phonetage.ui.Mainview;

public class TabspageAdapter extends FragmentPagerAdapter {

    String [] tablist;
    private Context mContext;

    public TabspageAdapter( Context context,FragmentManager fm) {
        super(fm);
        mContext = context;
        Resources res = mContext.getResources();
        tablist =  res.getStringArray(R.array.tablist);
    }




    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:
                return Task.newInstance();
            case 1:
                return QuickTag.newInstance();
            case 2:
                return Filter.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tablist[position];
    }

    @Override
    public int getCount() {
        return tablist.length ;
    }
}
