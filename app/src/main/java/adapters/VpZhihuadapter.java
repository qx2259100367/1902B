package adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import base.BaseFragment;

/**
 * Created by ASUS on 2019/9/7.
 */

public class VpZhihuadapter extends FragmentPagerAdapter {
    private ArrayList<BaseFragment> mFragments;
    private ArrayList<String> mTitles;

    public VpZhihuadapter(FragmentManager fm, ArrayList<BaseFragment> mFragments, ArrayList<String> mTitles) {
        super(fm);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
