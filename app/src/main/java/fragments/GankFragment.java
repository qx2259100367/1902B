package fragments;

import android.os.Bundle;


import com.example.asus.geeknews.R;

import base.BaseFragment;
import presenter.GankPresenter;
import view.GankView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class GankFragment extends BaseFragment<GankView, GankPresenter>
        implements GankView {

    public static GankFragment newInstance(){
        GankFragment fragment = new GankFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    protected GankPresenter initPresenter() {
        return new GankPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
