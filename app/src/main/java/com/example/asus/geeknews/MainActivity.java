package com.example.asus.geeknews;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import base.BaseActivity;
import base.BaseFragment;
import fragments.AboutFragment;
import fragments.ColllectFragment;
import fragments.GankFragment;
import fragments.GoldFragment;
import fragments.SettingsFragment;
import fragments.V2exFragment;
import fragments.WechatFragment;
import fragments.ZhihuFragment;
import presenter.MainPresenter;
import util.ToastUtil;
import view.MainView;

public class MainActivity extends BaseActivity<MainPresenter, MainView> implements MainView {


    private Toolbar mTb;
    private FrameLayout mFlt;
    private NavigationView mNv;
    private DrawerLayout mDlt;
    private MenuItem zhihuitem;
    private static final int TYPE_ZHIHU = 0;
    private static final int TYPE_WECHAT = 1;
    private static final int TYPE_GANK = 2;
    private static final int TYPE_GOLD = 3;
    private static final int TYPE_V2EX = 4;
    private static final int TYPE_COLLECT = 5;
    private static final int TYPE_SETTINGS = 6;
    private static final int TYPE_ABOUT = 7;
    private FragmentManager manager;
    private ArrayList<BaseFragment> mFragments;
    private int mLastPosition;
    private ArrayList<Integer> mTitles;
    private int mLastMenuId = R.id.zhihu;
    private MaterialSearchView mSearchView;
    private FrameLayout mToolbarContainer;
    private MenuItem mItem;

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.setDatas();
    }

    public void initView() {
        mTb = (Toolbar) findViewById(R.id.tb);
        mFlt = (FrameLayout) findViewById(R.id.flt);
        mNv = (NavigationView) findViewById(R.id.nv);
        mDlt = (DrawerLayout) findViewById(R.id.dlt);
        mTb.setTitle(R.string.zhihu_daily_news);
        mTb.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mTb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDlt, mTb, R.string.app_name, R.string.app_name);
        mDlt.addDrawerListener(toggle);
        toggle.syncState();
        zhihuitem = mNv.getMenu().findItem(R.id.zhihu);
        //menu的监听
        initTitles();
        //初始化标题
        initTitles();
        //初始化碎片
        initFragments();

        manager = getSupportFragmentManager();
        //一上来显示知乎日报碎片
        addZhiHuFragment();
        mSearchView = (MaterialSearchView) findViewById(R.id.search_view);
        mToolbarContainer = (FrameLayout) findViewById(R.id.toolbar_container);
    }

    private void addZhiHuFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.flt, mFragments.get(0));
        transaction.commit();
    }

    //fragment集合
    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(ZhihuFragment.newInstance());
        mFragments.add(WechatFragment.newInstance());
        mFragments.add(GankFragment.newInstance());
        mFragments.add(GoldFragment.newInstance());
        mFragments.add(V2exFragment.newInstance());
        mFragments.add(ColllectFragment.newInstance());
        mFragments.add(SettingsFragment.newInstance());
        mFragments.add(AboutFragment.newInstance());
    }

    //Toolbar标题
    private void initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(R.string.zhihu_daily_news);
        mTitles.add(R.string.wechat);
        mTitles.add(R.string.gank);
        mTitles.add(R.string.gold);
        mTitles.add(R.string.v2ex);
        mTitles.add(R.string.collect);
        mTitles.add(R.string.settings);
        mTitles.add(R.string.about);
    }

    @Override
    public void initListener() {

        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                //把旧的menu设置为未选中
                mNv.getMenu().findItem(mLastMenuId).setChecked(false);
                //哪个menu点击了就将状态选中
                item.setChecked(true);
                ///关闭drawer
                mDlt.closeDrawer(Gravity.LEFT);
                mLastMenuId = itemId;
                switch (itemId) {
                    case R.id.zhihu:
                        addFragment(TYPE_ZHIHU);
                        break;
                    case R.id.wechat:
                        addFragment(TYPE_WECHAT);
                        break;
                    case R.id.gank:
                        addFragment(TYPE_GANK);
                        break;
                    case R.id.gold:
                        addFragment(TYPE_GOLD);
                        break;
                    case R.id.v2ex:
                        addFragment(TYPE_V2EX);
                        break;
                    case R.id.collect:
                        addFragment(TYPE_COLLECT);
                        break;
                    case R.id.settings:
                        addFragment(TYPE_SETTINGS);
                        break;
                    case R.id.about:
                        addFragment(TYPE_ABOUT);
                        break;
                }
                return false;
            }
        });








        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交查询文本的监听
                //ToastUtil.showShort("提交查询文本:"+query);
                //提交的时候将要查询的内容传递到微信精选Fragment,再调用查询的接口进行查询
                //Activity 和 Fragment 通信,
                //A -->F  setArgumemnts()
                //A < --- > getActivity()
                //对象.方法(query)
                /*WechatFragment fragment = (WechatFragment) mFragments.get(TYPE_WECHAT);
                fragment.setData(query);*/
                //EventBus
                EventBus.getDefault().postSticky(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索框 里面文本发生改变的监听
                //ToastUtil.showShort("文本发生改变:"+newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展示
                //ToastUtil.showShort("show:");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
                //ToastUtil.showShort("close:");
            }
        });
    }

    private void addFragment(int type) {
        //1. 同一个碎片只能添加一次,除非被移除
        //2.需要将旧的碎片隐藏
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = mFragments.get(type);
        if (!fragment.isAdded()) {
            transaction.add(R.id.flt, fragment);
        }
        //隐藏旧的碎片
        Fragment oldFragment = mFragments.get(mLastPosition);
        transaction.hide(oldFragment);
        //添加过就显示就ok了
        transaction.show(fragment);
        transaction.commit();

        //切换title
        mTb.setTitle(mTitles.get(type));
        //记录当前显示的碎片,它下次就是要隐藏的对象
        mLastPosition = type;


        //隐藏或者显示搜索框
        if (type == TYPE_WECHAT || type == TYPE_GANK){
            mItem.setVisible(true);
        }else {
            mItem.setVisible(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        mItem = menu.findItem(R.id.action_search);
        //隐藏搜索框
        mItem.setVisible(false);
        mSearchView.setMenuItem(mItem);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()){
            //搜索框是展开的就关闭搜索框
            mSearchView.closeSearch();
        }else {
            //搜索框关着,交给系统处理返回键事件
            super.onBackPressed();
        }

    }
}
