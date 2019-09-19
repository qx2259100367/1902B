package model;

import net.ApiService;
import net.MyResultCall;

import java.util.ArrayList;
import java.util.List;

import base.BaseModel;
import base.BaseObserver;
import bean.DailyBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/8.
 */

public class DailyModel extends BaseModel {


    public void setDatas(final MyResultCall<DailyBean> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.DAILY, ApiService.class);
        apiserver.daily()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DailyBean>(this) {
                    @Override
                    protected void onSucceed(DailyBean dailyBean) {

                        resultCall.getDatas(dailyBean);
                    }

                    @Override
                    protected void error(String err) {
                        resultCall.showToast(err);
                    }
                });
    }
      //非当日时间
    public void getBeforeNews(String time, final MyResultCall<DailyBean> myResultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.OLDDATE, ApiService.class);

        apiserver.olddate(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<DailyBean>(this) {
                    @Override
                    protected void onSucceed(DailyBean dailyBean) {
                                myResultCall.getDatas(dailyBean);
                    }

                    @Override
                    protected void error(String err) {
                    myResultCall.showToast(err);
                    }
                });

    }
}
