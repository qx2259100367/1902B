package base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import widget.LoadingDialog;

/**
 * Created by ASUS on 2019/9/6.
 */

public abstract class BaseActivity<P extends BasePresenter,V extends BaseView> extends AppCompatActivity implements BaseView{

    public P presenter;
    private LoadingDialog mLoadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        presenter = initPresenter();
        if (presenter!=null){
            presenter.bindView((V)this);
        }
        initView();
        initData();
        initListener();
    }



    public void initListener() {
    }
    public void initData() {
    }
    public void initView() {
    }
    protected abstract P initPresenter();
    protected abstract int layoutId();
    /**
     * 在Activtiy销毁的时候,需要取消V和P层的联系,取消网络请求,取消Rxjava
     * 观察者和被观察者的订阅关系
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
        presenter=null;
    }

    @Override
    public void showLoading() {
            if (mLoadingDialog==null){
                mLoadingDialog=new LoadingDialog(this);
            }
            mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

}
