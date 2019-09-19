package fragments;

import android.os.Bundle;


import com.example.asus.geeknews.R;



import base.BaseFragment;
import presenter.AboutPresenter;
import view.AboutView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class AboutFragment extends BaseFragment<AboutView, AboutPresenter>
        implements AboutView {

    public static AboutFragment newInstance(){
        AboutFragment aboutFragment = new AboutFragment();
        Bundle bundle = new Bundle();
        aboutFragment.setArguments(bundle);

        return aboutFragment;
    }

    @Override
    protected AboutPresenter initPresenter() {
        return new AboutPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
