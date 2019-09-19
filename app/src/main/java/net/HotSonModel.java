package net;

import android.util.Log;

import base.BaseModel;
import base.BaseObserver;
import bean.HotSonBean;
import bean.HotnumBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/8.
 */

public class HotSonModel extends BaseModel {
    public void setDatas(int id, final MyResultCall<HotSonBean> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.HOTSON, ApiService.class);
        apiserver.hotson(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HotSonBean>(this) {
                    @Override
                    protected void onSucceed(HotSonBean hotSonBean) {

                                resultCall.getDatas(hotSonBean);
                    }

                    @Override
                    protected void error(String err) {
                    resultCall.showToast(err);
                    }
                });
    }

    public void setNum(int num, final MyResultCall<HotnumBean> myResultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.DAILY, ApiService.class);
        apiserver.hotnum(num)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HotnumBean>(this) {
                    @Override
                    protected void onSucceed(HotnumBean hotnumBean) {
                                    myResultCall.getDatas(hotnumBean);
                    }

                    @Override
                    protected void error(String err) {
                        myResultCall.showToast(err);
                    }
                });
    }
}
