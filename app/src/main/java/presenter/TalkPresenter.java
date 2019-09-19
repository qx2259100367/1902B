package presenter;

import android.util.Log;

import net.MyResultCall;

import java.util.ArrayList;

import base.BasePresenter;
import bean.TalkBean;
import model.TalkModel;
import view.TalkView;

/**
 * Created by ASUS on 2019/9/7.
 */

public class TalkPresenter extends BasePresenter<TalkView> {

    private TalkModel talkModel;

    @Override
    protected void initModel() {
        talkModel = new TalkModel();
        addModel(talkModel);
    }

    public void setDatas(int id) {
        talkModel.setDatas(id, new MyResultCall<ArrayList<TalkBean.StoriesBean>>() {
            @Override
            public void getDatas(ArrayList<TalkBean.StoriesBean> beans) {
                if (mview!=null){
                    mview.hideLoading();
                    if (beans!=null && beans.size()>0){
                        mview.getDatas(beans);
                    }
                }

            }

            @Override
            public void showToast(String str) {
                if (mview!=null){
                    mview.hideLoading();
                    mview.showTaast(str);
                }

            }
        });
    }
}