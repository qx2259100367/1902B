package model;

import android.util.Log;

import net.ApiService;
import net.MyResultCall;

import java.util.ArrayList;
import java.util.List;

import base.BaseModel;
import base.BaseObserver;
import bean.FuliBean;
import bean.SectionBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import util.HttpUtils;

/**
 * Created by ASUS on 2019/9/7.
 */

public class SectionModel extends BaseModel {

/*    ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.FULI, ApiService.class);
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

            */
    public void setDatas(final MyResultCall<ArrayList<SectionBean.DataBean>> result) {
        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.SCETION, ApiService.class);
        apiserver.section()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SectionBean>(this) {
                    @Override
                    protected void onSucceed(SectionBean sectionBean) {

                        List<SectionBean.DataBean> data = sectionBean.getData();
                        Log.i("tag","90000000"+sectionBean.toString());
                       result.getDatas((ArrayList<SectionBean.DataBean>) data);
                    }

                    @Override
                    protected void error(String err) {
                        result.showToast(err);
                    }
                });
    }
}
