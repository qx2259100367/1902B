package com.example.asus.geeknews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import adapters.RlvTalk;
import base.BaseActivity;
import bean.TalkBean;
import butterknife.ButterKnife;
import presenter.TalkPresenter;
import util.ToastUtil;
import view.TalkView;

public class TalkActivity extends BaseActivity<TalkPresenter, TalkView> implements TalkView {


    private int id;
    private ArrayList<TalkBean.StoriesBean> list = new ArrayList<>();
    private RlvTalk adapter;
    private RecyclerView mRlvtalk;

    @Override
    protected TalkPresenter initPresenter() {
        return new TalkPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_talk;
    }

    @Override
    public void initView() {
        super.initView();
        mRlvtalk = (RecyclerView) findViewById(R.id.rlvtalk);
        Intent intent = getIntent();
        String i = intent.getStringExtra("id");
        id = Integer.parseInt(i);
        mRlvtalk.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RlvTalk(list);
        mRlvtalk.setAdapter(adapter);

        adapter.setTalkOnclic(new RlvTalk.TalkOnclic() {
            @Override
            public void MyTalkOnclic(int i) {

                int id=0;
                if (list!=null && list.size()>0){
                    id = list.get(i).getId();
                }
                Intent intent = new Intent(TalkActivity.this, HotSonActivity.class);
                intent.putExtra("id", id + "");
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        presenter.setDatas(id);
    }

    @Override
    public void getDatas(ArrayList<TalkBean.StoriesBean> beans) {

        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showTaast(String str) {
        ToastUtil.showShort(str);
    }


}
