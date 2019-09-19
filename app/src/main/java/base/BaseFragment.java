package base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import widget.LoadingDialog;

/**
 * Created by ASUS on 2019/9/7.
 */

public  abstract  class BaseFragment<V extends BaseView,P extends BasePresenter> extends Fragment implements BaseView{
    public P mPresenter;
    public Unbinder mUnbinder;
    private LoadingDialog mLoadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null, false);
        mUnbinder = ButterKnife.bind(this, inflate);
        mPresenter = initPresenter();
        if (mPresenter != null){
            mPresenter.bindView(this);
        }
        initView();
        initData();
        initListener();
        return inflate;

    }

    protected abstract P initPresenter();

    public void initListener() {

    }

    public void initData() {

    }

    public void initView() {
    }

    protected abstract int getLayoutId();

    @Override
    public void showLoading() {
        if (mLoadingDialog==null){
            mLoadingDialog=new LoadingDialog(getContext());
        }
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }
    }
}
