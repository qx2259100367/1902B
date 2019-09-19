package model;

import net.ApiService;
import net.MyResultCall;

import base.BaseModel;
import base.BaseObserver;
import bean.LongBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/9.
 */

public class LongModel extends BaseModel {
    public void setDatas(String id, final MyResultCall<LongBean> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.OLDDATE, ApiService.class);
        apiserver.longbean(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<LongBean>(this) {
                    @Override
                    protected void onSucceed(LongBean longBean) {
                        resultCall.getDatas(longBean);
                    }

                    @Override
                    protected void error(String err) {
                        resultCall.showToast(err);
                    }
                });
    }
}
