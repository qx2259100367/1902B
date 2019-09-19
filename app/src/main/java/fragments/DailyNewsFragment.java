package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.geeknews.CalendarActivity;
import com.example.asus.geeknews.CommentActivity;
import com.example.asus.geeknews.HotSonActivity;
import com.example.asus.geeknews.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import adapters.RlvDailyadapter;
import base.BaseFragment;
import bean.DailyBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import presenter.DailyNewsPresenter;
import util.DateUtil;
import util.ToastUtil;
import view.DailyNewsView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class DailyNewsFragment extends BaseFragment<DailyNewsView, DailyNewsPresenter>
        implements DailyNewsView {


    @BindView(R.id.rlvdaily)
    RecyclerView mRlv;

    @BindView(R.id.fab)
    FloatingActionButton mFab;
    private ArrayList<DailyBean.TopStoriesBean> Bannerlist = new ArrayList<>();
    private ArrayList<DailyBean.StoriesBean> Newslist = new ArrayList<>();
    private String date = "";
    private RlvDailyadapter adapter;
    public static final int TYPE_LAST = 0;
    public static final int TYPE_OLD = 1;



    public static DailyNewsFragment newInstance() {
        DailyNewsFragment fragment = new DailyNewsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected DailyNewsPresenter initPresenter() {
        return new DailyNewsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_daily_news;
    }


    @Override
    public void initView() {
        super.initView();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);   //没有绑定再绑定
        }


        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RlvDailyadapter(getContext(), Bannerlist, Newslist, date);
        mRlv.setAdapter(adapter);

        adapter.setDailyOnclic(new RlvDailyadapter.DailyOnclic() {
            @Override
            public void MyDailyOnclic(int i) {
                int id=0;
                if (Bannerlist!=null &&Bannerlist.size()>0){
                      id = Newslist.get(i-2).getId();
                }else {
                    id=Newslist.get(i-1).getId();
                }

                Log.i("iiiddd",id+"");
                if (id!=0){
                    Intent intent = new Intent(getActivity(), HotSonActivity.class);
                    intent.putExtra("id", id + "");
                    startActivity(intent);
                }
            }
        });



        adapter.setBrOnclic(new RlvDailyadapter.BrOnclic() {
            @Override
            public void MyBrOnclic(int i) {
                int id=0;
                if (Bannerlist!=null && Bannerlist.size()>0){
                      id = Bannerlist.get(i).getId();
                }
                Intent intent = new Intent(getActivity(), HotSonActivity.class);
                intent.putExtra("id", id + "");
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter.setDatas(TYPE_LAST,"");
    }


    @Override
    public void getDatas(DailyBean beans) {
        Bannerlist.clear();  //在每次加载数据时将原来的清空  只显示最新加载的
        Newslist.clear();
        String dt = beans.getDate();
        date = dt;

        List<DailyBean.TopStoriesBean> top_stories1 = beans.getTop_stories();
        if (top_stories1!=null&&top_stories1.size()>0){
            Bannerlist.addAll(top_stories1);
        }

        List<DailyBean.StoriesBean> stories = beans.getStories();
        if (stories!=null&&stories.size()>0){
            Newslist.addAll(stories);
        }


        adapter.notifyDataSetChanged();
    }

    @Override
    public void showToast(String str) {
        ToastUtil.showShort(str);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @OnClick(R.id.fab)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.fab:
        startActivity(new Intent(getContext(), CalendarActivity.class));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getTime(String time){
        getNews(time);
    }

    private void getNews(String time) {
        //根据日期去选择要请求数据的接口
        if (time.equalsIgnoreCase(DateUtil.getCurrentTime())){   //选中的是今天
            mPresenter.setDatas(TYPE_LAST,"");
        }else {
            //因为接口如果给的日期不是今天的日期,比如给了20190906,返回的数据是20190905
            //所以需要将日期+1
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            try {
                Date parse = format.parse(time);
                //不做设置是当前的日期
                Calendar instance = Calendar.getInstance();
                //设置日期
                instance.setTime(parse);
                //在设置的日期基础上+1
                instance.add(Calendar.DAY_OF_YEAR,1);

                String s = DateUtil.formatDate(instance.getTime());

            mPresenter.setDatas(TYPE_OLD,s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
