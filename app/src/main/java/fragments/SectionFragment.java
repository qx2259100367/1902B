package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.R;
import com.example.asus.geeknews.TalkActivity;

import java.util.ArrayList;

import adapters.RlvSection;
import base.BaseFragment;
import bean.SectionBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.SectionPresenter;
import util.ToastUtil;
import view.SectionView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class SectionFragment extends BaseFragment<SectionView, SectionPresenter>
        implements SectionView {

    @BindView(R.id.rvspecial)
    RecyclerView mRvspecial;
    private Unbinder unbinder;
    private ArrayList<SectionBean.DataBean> beans=new ArrayList<>();
    private RlvSection rlvadapter;

    public static SectionFragment newInstance() {
        SectionFragment fragment = new SectionFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected SectionPresenter initPresenter() {
        return new SectionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_section;
    }


    @Override
    public void initView() {
        super.initView();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRvspecial.setLayoutManager(gridLayoutManager);
        rlvadapter = new RlvSection(beans);
        mRvspecial.setAdapter(rlvadapter);

        rlvadapter.setOnclic(new RlvSection.SectionOnclic() {
            @Override
            public void MyOnclic(int i) {
                Intent intent = new Intent(getContext(), TalkActivity.class);
                int id = beans.get(i).getId();
                intent.putExtra("id",id+"");
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
    public void setDatas(ArrayList<SectionBean.DataBean> beans) {
        //接收数据
        this.beans.addAll(beans);
        rlvadapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }
}
