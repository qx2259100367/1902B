package fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import adapters.WechAdapter;
import base.BaseFragment;
import bean.WxtBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import presenter.WechatPresenter;
import util.ToastUtil;
import view.WechatView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class WechatFragment extends BaseFragment<WechatView, WechatPresenter>
        implements WechatView {


    @BindView(R.id.rlvwx)
    RecyclerView rlv;
    @BindView(R.id.slt)
    SmartRefreshLayout mSlt;
    public static final int TYPE_QUERY = 0;
    public static final int TYPE_WECH = 1;
    private String Search = "";
    private int page = 1;
    private ArrayList<WxtBean.NewslistBean> list = new ArrayList<>();
    private WechAdapter adapter;


    public static WechatFragment newInstance() {
        WechatFragment fragment = new WechatFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected WechatPresenter initPresenter() {
        return new WechatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    public void initView() {
        super.initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WechAdapter(list);
        rlv.setAdapter(adapter);
        //加载
    mSlt.setOnLoadMoreListener(new OnLoadMoreListener() {
        @Override
        public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
        }
    });
    ///刷新
    mSlt.setOnRefreshListener(new OnRefreshListener() {
        @Override
        public void onRefresh(@NonNull RefreshLayout refreshLayout) {
            list.clear();
                page=1;
                initData();
             }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void equart(String str) {
        Search = str;

        if (str!=null){
            mPresenter.setData(0,str,TYPE_QUERY);
        }
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.setData(page,"",TYPE_WECH);
    }


    @Override
    public void getDatas(WxtBean wxtBean) {
        list.clear();
        List<WxtBean.NewslistBean> newslist = wxtBean.getNewslist();
        if (newslist!=null && newslist.size()>0){
            list.addAll(newslist);
            mSlt.finishRefresh();
            mSlt.finishLoadMore();
            adapter.notifyDataSetChanged();
        }else {
            ToastUtil.showShort("你搜索的内容是空的");
        }


    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();

    }

}
