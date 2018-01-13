package com.commodity.purchase.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.commodity.purchase.R;
import com.commodity.purchase.ui.other.FirstShowActivity;
import com.commodity.purchase.ui.other.FirstShowFragment;

public class FirstShowAdapter extends FragmentPagerAdapter {

    FirstShowFragment[] firstShowFragment = new FirstShowFragment[4];

    public FirstShowAdapter(FragmentManager fm,FirstShowActivity activity) {
        super(fm);
        firstShowFragment[0] =new  FirstShowFragment(activity, R.mipmap.one, 0);
        firstShowFragment[0].setRetainInstance(true);
        firstShowFragment[1] = new  FirstShowFragment(activity,R.mipmap.two,0);
        firstShowFragment[1].setRetainInstance(true);
        firstShowFragment[2] = new  FirstShowFragment(activity,R.mipmap.three, 0);
        firstShowFragment[2].setRetainInstance(true);
        firstShowFragment[3] = new  FirstShowFragment(activity,R.mipmap.four, 1);
        firstShowFragment[3].setRetainInstance(true);
    }

    @Override
    public Fragment getItem(int arg0) {
        return firstShowFragment[arg0];
    }

    @Override
    public int getCount() {
        return firstShowFragment.length;
    }

}
