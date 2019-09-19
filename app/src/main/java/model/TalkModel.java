package model;

import android.util.Log;

import net.ApiService;
import net.MyResultCall;

import java.util.ArrayList;
import java.util.List;

import base.BaseModel;
import base.BaseObserver;
import bean.TalkBean;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/7.
 */

public class TalkModel extends BaseModel {


    public void setDatas(int id, final MyResultCall<ArrayList<TalkBean.StoriesBean>> resultCall) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.TALK, ApiService.class);
        apiserver.talk(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TalkBean>(this) {
                    @Override
                    protected void onSucceed(TalkBean talkBean) {
                        List<TalkBean.StoriesBean> stories = talkBean.getStories();

                        resultCall.getDatas((ArrayList<TalkBean.StoriesBean>) stories);
                    }

                    @Override
                    protected void error(String err) {
                            resultCall.showToast(err);
                    }
                });
    }
}
