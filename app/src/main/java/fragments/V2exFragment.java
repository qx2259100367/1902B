package fragments;

import android.os.Bundle;

import com.example.asus.geeknews.R;

import base.BaseFragment;
import presenter.V2exPresenter;
import view.V2exView;


/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class V2exFragment extends BaseFragment<V2exView, V2exPresenter>
        implements V2exView {

    public static V2exFragment newInstance(){
        V2exFragment fragment = new V2exFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);

        return fragment;
    }
    @Override
    protected V2exPresenter initPresenter() {
        return new V2exPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
