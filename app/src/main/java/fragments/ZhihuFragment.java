package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.R;

import java.util.ArrayList;

import adapters.VpZhihuadapter;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.ZhihuPresenter;
import view.ZhihuView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class ZhihuFragment extends BaseFragment<ZhihuView, ZhihuPresenter> implements ZhihuView {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<String> mTitles;
    private ArrayList<BaseFragment> mFragments;



    public static ZhihuFragment newInstance() {
        ZhihuFragment fragment = new ZhihuFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected ZhihuPresenter initPresenter() {
        return new ZhihuPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    public void initView() {
        initTitles();
        initFragments();


        VpZhihuadapter vpadapter = new VpZhihuadapter(getChildFragmentManager(), mFragments, mTitles);
        mVp.setAdapter(vpadapter);
        mTabLayout.setupWithViewPager(mVp);
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(DailyNewsFragment.newInstance());
        mFragments.add(SectionFragment.newInstance());
        mFragments.add(HotFragment.newInstance());
    }

    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(getResources().getString(R.string.daily_news));
        mTitles.add(getResources().getString(R.string.section));
        mTitles.add(getResources().getString(R.string.hot));
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }



}
