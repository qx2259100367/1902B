package fragments;

import android.os.Bundle;

import com.example.asus.geeknews.R;

import base.BaseFragment;
import presenter.SettingsPresenter;
import view.SettingsView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class SettingsFragment extends BaseFragment<SettingsView, SettingsPresenter>
        implements SettingsView {

    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    protected SettingsPresenter initPresenter() {
        return new SettingsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
