package com.example.asus.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import base.BaseActivity;
import fragments.LongFragment;
import fragments.ShortFragment;
import presenter.CommentPresenter;
import view.CommentView;

public class CommentActivity extends BaseActivity<CommentPresenter, CommentView> implements CommentView {


    private TabLayout mTabnum;
    private FrameLayout mFlt;
    private FragmentManager supportFragmentManager;
    private LongFragment longFragment;
    private String id;
    private ShortFragment shortFragment;

    @Override
    protected CommentPresenter initPresenter() {
        return new CommentPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_comment;
    }

    @Override
    public void initView() {
        super.initView();
        mFlt = (FrameLayout) findViewById(R.id.flt);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String ashort = intent.getStringExtra("short");
        String aLong = intent.getStringExtra("long");
        String comments = intent.getStringExtra("comments");

        mTabnum = (TabLayout) findViewById(R.id.tabnum);
        mTabnum.addTab(mTabnum.newTab().setText("短评论" + "(" + aLong + ")"));
        mTabnum.addTab(mTabnum.newTab().setText("长评论" + "(" + ashort + ")"));

        supportFragmentManager = getSupportFragmentManager();
        shortFragment = new ShortFragment();
        longFragment = new LongFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", id+"");
        longFragment.setArguments(bundle);
        shortFragment.setArguments(bundle);
        supportFragmentManager.beginTransaction().replace(R.id.flt, shortFragment).commit();
    }

    @Override
    public void initListener() {
        super.initListener();
        mTabnum.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        supportFragmentManager.beginTransaction().replace(R.id.flt,shortFragment).commit();
                        break;
                    case 1:
                        supportFragmentManager.beginTransaction().replace(R.id.flt,longFragment).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
