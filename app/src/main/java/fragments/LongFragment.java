package fragments;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.R;

import java.util.ArrayList;
import java.util.List;

import adapters.HotLongadapter;
import base.BaseFragment;
import bean.LongBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.LongPresenter;
import util.ToastUtil;
import view.LongView;

/**
 * Created by ASUS on 2019/9/9.
 */

public class LongFragment extends BaseFragment<LongView, LongPresenter> implements LongView {

    @BindView(R.id.rlvlong)
    RecyclerView mRlvlong;
    private String id;

    private ArrayList<LongBean.CommentsBean> longlist=new ArrayList<>();
    private HotLongadapter adapter;

    @Override
    protected LongPresenter initPresenter() {
        return new LongPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.long_fragment;
    }

    @Override
    public void initView() {
        super.initView();
        Bundle arguments = getArguments();
        id = (String) arguments.get("id");
        mRlvlong.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlvlong.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        adapter = new HotLongadapter(longlist);
        mRlvlong.setAdapter(adapter);
    }


    @Override
    public void initData() {
        super.initData();
        Log.i("longid",id+"");
        mPresenter.setDatas(id);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
       mUnbinder.unbind();
    }
    @Override
    public void getDatas(LongBean longBean) {
        List<LongBean.CommentsBean> comments = longBean.getComments();
        longlist.addAll(comments);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }
}
