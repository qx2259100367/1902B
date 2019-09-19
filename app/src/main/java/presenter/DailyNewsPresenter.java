package presenter;


import android.util.Log;

import net.MyResultCall;

import java.util.ArrayList;

import base.BasePresenter;
import bean.DailyBean;
import fragments.DailyNewsFragment;
import model.DailyModel;
import view.DailyNewsView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {

    private DailyModel dailyModel;

    @Override
    protected void initModel() {
        dailyModel = new DailyModel();
        addModel(dailyModel);
    }



    public void setDatas(int typeLast, String time) {
                if (typeLast== DailyNewsFragment.TYPE_LAST){
                    //最新的
                    dailyModel.setDatas(new MyResultCall<DailyBean>() {
                        @Override
                        public void getDatas(DailyBean dailyBean) {
                            if (mview!=null){
                                mview.hideLoading();
                                if (dailyBean!=null){
                                    mview.getDatas(dailyBean);
                                }
                            }

                        }
                        @Override
                        public void showToast(String str) {
                            if (mview != null){
                                mview.hideLoading();
                                mview.showToast(str);
                            }

                        }
                    });
                }else {
            dailyModel.getBeforeNews(time, new MyResultCall<DailyBean>() {
                @Override
                public void getDatas(DailyBean dailyBean) {
                    if (mview!=null){
                        mview.hideLoading();
                        if (dailyBean!=null){
                            mview.getDatas(dailyBean);
                        }
                    }

                }
                @Override
                public void showToast(String str) {
                    if (mview != null){
                        mview.hideLoading();
                        mview.showToast(str);
                    }
                }
            });


                }
    }


    //选中日期的

}
