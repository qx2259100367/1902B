package model;

import net.ApiService;
import net.MyResultCall;

import java.util.ArrayList;
import java.util.List;

import base.BaseModel;
import base.BaseObserver;
import bean.HotBean;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/7.
 */

public class HotModel extends BaseModel {
    public void setDatas(final MyResultCall<ArrayList<HotBean.RecentBean>> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.HOT, ApiService.class);
        apiserver.hot()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<HotBean>(this) {
                    @Override
                    protected void onSucceed(HotBean hotBean) {
                        List<HotBean.RecentBean> recent = hotBean.getRecent();
                        resultCall.getDatas((ArrayList<HotBean.RecentBean>) recent);
                    }

                    @Override
                    protected void error(String err) {
                        resultCall.showToast(err);
                    }
                });
    }
}
