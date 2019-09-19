package fragments;

import android.os.Bundle;

import com.example.asus.geeknews.R;

import base.BaseFragment;
import presenter.CollectPresenter;
import view.ColllectView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class ColllectFragment extends BaseFragment<ColllectView, CollectPresenter>
        implements ColllectView {

    public static ColllectFragment newInstance(){
        ColllectFragment fragment = new ColllectFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    protected CollectPresenter initPresenter() {
        return new CollectPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
