package com.example.asus.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.BaseApp;

import base.BaseActivity;
import bean.HotSonBean;
import bean.HotnumBean;
import presenter.HotSonPresenter;
import util.MImageGetter;
import util.ToastUtil;
import view.HotSonView;

public class HotSonActivity extends BaseActivity<HotSonPresenter, HotSonView> implements HotSonView {


    private int id=0;


    private String TAG = "HotSonActivity";
    private TabLayout mTabhot;
    private ImageView mIv;
    private Toolbar mToolBar;
    private CollapsingToolbarLayout mCtl;
    private AppBarLayout mAppBar;

    /**
     * 关于
     */
    private TextView mTvContent;
    private NestedScrollView mNsv;
    private HotnumBean bean;


    @Override
    protected HotSonPresenter initPresenter() {
        return new HotSonPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_hot_son;
    }

    @Override
    public void initView() {
        super.initView();

        Intent intent = getIntent();
        String i = intent.getStringExtra("id");
        id = Integer.parseInt(i);


        mTabhot = (TabLayout) findViewById(R.id.tabhot);


        mIv = (ImageView) findViewById(R.id.iv);
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        mCtl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        mAppBar = (AppBarLayout) findViewById(R.id.appBar);
        mTvContent = (TextView) findViewById(R.id.tvContent);
        mNsv = (NestedScrollView) findViewById(R.id.nsv);
    }


    @Override
    public void initData() {
        super.initData();
        presenter.setDadas(id);
        presenter.setnum(id);
    }

    @Override
    public void getDatas(HotSonBean beans) {
        String by = beans.getBody();

        mToolBar.setTitle(beans.getTitle());
        setSupportActionBar(mToolBar);
        Glide.with(this).load(beans.getImage()).into(mIv);
        //1.jsoup,可以解析h5的片段并取出里面的数据
        //2.Html.fromHtml可以将h5种的body片段解析成Spanned,它可以直接设置到Textview
        //Spanned body = Html.fromHtml("body");
        //3.三方解析工具,腾讯的x5浏览器的内核(GeekNews源码使用的方式)
        String body = beans.getBody();
        if (!TextUtils.isEmpty(body)) {
            Spanned spanned = Html.fromHtml(body,
                    new MImageGetter(mTvContent,this, BaseApp.mWidthPixels),null);
            mTvContent.setText(spanned);
        }
    }

    @Override
    public void getNums(HotnumBean bean) {

        this.bean=bean;
        mTabhot.addTab(mTabhot.newTab().setIcon(R.mipmap.ic_daily_like).setText(bean.getPopularity() + "个赞"));
        mTabhot.addTab(mTabhot.newTab().setIcon(R.mipmap.ic_daily_comment).setText(bean.getComments() + "条评论"));
        mTabhot.addTab(mTabhot.newTab().setIcon(R.mipmap.ic_daily_share).setText("分享"));

    }


    @Override
    public void initListener() {
        super.initListener();
        mTabhot.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    //点赞人数
                } else if (position == 1) {
                    Intent intent = new Intent(HotSonActivity.this, CommentActivity.class);

                    int long_comments = bean.getLong_comments();
                    int short_comments = bean.getShort_comments();
                    int comments = bean.getComments();
                    intent.putExtra("short", long_comments + "");
                    intent.putExtra("long", short_comments + "");
                    intent.putExtra("comments", comments + "");
                    intent.putExtra("id", id + "");
                    startActivity(intent);
                } else {
                    //分享三方
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

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }


}

