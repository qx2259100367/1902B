package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.HotSonActivity;
import com.example.asus.geeknews.R;

import java.util.ArrayList;

import adapters.RlvHot;
import base.BaseFragment;
import bean.HotBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.HotPresenter;
import util.ToastUtil;
import view.HotView;


public class HotFragment extends BaseFragment<HotView, HotPresenter>
        implements HotView {


    @BindView(R.id.rlvhot)
    RecyclerView mRlvhot;
   private ArrayList<HotBean.RecentBean> list=new ArrayList<>();
    private RlvHot adapter;

    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected HotPresenter initPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initView() {
        super.initView();
        mRlvhot.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RlvHot(list);
        mRlvhot.setAdapter(adapter);
        adapter.setTalkOnclic(new RlvHot.TalkOnclic() {
            @Override
            public void MyTalkOnclic(int i) {
                ToastUtil.showShort("哈哈");
                Intent intent = new Intent(getActivity(), HotSonActivity.class);
                int news_id = list.get(i).getNews_id();
                intent.putExtra("id",news_id+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.setDatas();
    }

    @Override
    public void getDatas(ArrayList<HotBean.RecentBean> beans) {
        list.addAll(beans);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }
}
