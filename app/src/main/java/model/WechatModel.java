package model;

import net.ApiService;
import net.MyResultCall;

import base.BaseModel;
import base.BaseObserver;
import bean.WxtBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/9.
 */

public class WechatModel extends BaseModel {

    public void setDatas(int data,final MyResultCall<WxtBean>resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.WXT, ApiService.class);

        apiserver.wxt("52b7ec3471ac3bec6846577e79f20e4c",10,data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<WxtBean>(this) {
                    @Override
                    protected void onSucceed(WxtBean wxtBean) {
                            resultCall.getDatas(wxtBean);
                    }

                    @Override
                    protected void error(String err) {
                        resultCall.showToast(err);
                    }
                });
    }

    public void querydata(String str, final MyResultCall<WxtBean> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.WXT, ApiService.class);
        apiserver.quey("52b7ec3471ac3bec6846577e79f20e4c",20,1,str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<WxtBean>(this) {
                    @Override
                    protected void onSucceed(WxtBean wxtBean) {
                    resultCall.getDatas(wxtBean);
                    }

                    @Override
                    protected void error(String err) {
                    resultCall.showToast(err);
                    }
                });
    }
}
