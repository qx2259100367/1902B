package fragments;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.R;

import java.util.ArrayList;

import adapters.HotLongadapter;
import base.BaseFragment;
import base.BasePresenter;
import bean.LongBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.ShortPresenter;
import util.ToastUtil;
import view.ShortView;

/**
 * Created by ASUS on 2019/9/9.
 */

public class ShortFragment extends BaseFragment<ShortView,ShortPresenter> implements ShortView{
    @BindView(R.id.rlvshort)
    RecyclerView mRlvshort;
    private String id;
    private ArrayList<LongBean.CommentsBean> shortlist=new ArrayList<>();
    private HotLongadapter adapter;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_short;
    }

    @Override
    protected ShortPresenter initPresenter() {
        return new ShortPresenter();
    }



    @Override
    public void initView() {
        super.initView();
        Bundle arguments = getArguments();
        id = (String) arguments.get("id");
        mRlvshort.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlvshort.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));
        adapter = new HotLongadapter(shortlist);
        mRlvshort.setAdapter(adapter);

    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.setDatas(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void getDatas(LongBean longBean) {
                shortlist.addAll(longBean.getComments());
                adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }
}
