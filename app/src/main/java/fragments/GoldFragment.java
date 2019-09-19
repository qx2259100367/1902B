package fragments;

import android.os.Bundle;

import com.example.asus.geeknews.R;

import base.BaseFragment;
import presenter.GoldPresenter;
import view.GoldView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class GoldFragment extends BaseFragment<GoldView, GoldPresenter>
        implements GoldView {

    public static GoldFragment newInstance(){
        GoldFragment fragment = new GoldFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    protected GoldPresenter initPresenter() {
        return new GoldPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
