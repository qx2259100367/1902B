package model;

import android.util.Log;

import net.ApiService;
import util.HttpUtils;

import base.BaseModel;
import base.BaseObserver;
import bean.FuliBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ASUS on 2019/9/6.
 */

public class MainModel extends BaseModel{

    public void setDatas() {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.FULI, ApiService.class);
        apiserver.fulis().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<FuliBean>(this) {
                    @Override
                    protected void onSucceed(FuliBean fuliBean) {

                    }

                    @Override
                    protected void error(String err) {
                    }
                });

    }
}
