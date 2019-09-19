package presenter;


import android.util.Log;

import net.MyResultCall;

import java.util.ArrayList;

import base.BasePresenter;
import bean.SectionBean;
import model.SectionModel;
import view.SectionView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public   class SectionPresenter extends BasePresenter<SectionView> {

    private SectionModel sectionModel;

    @Override
    protected void initModel() {
        sectionModel = new SectionModel();
        addModel(sectionModel);
    }

    public void setDatas() {
            sectionModel.setDatas(new MyResultCall<ArrayList<SectionBean.DataBean>>() {

                @Override
                public void getDatas(ArrayList<SectionBean.DataBean> dataBeans) {
                    if (mview!=null){
                        mview.hideLoading();
                        if (dataBeans!=null && dataBeans.size()>0){

                            mview.setDatas(dataBeans);
                        }
                    }
                }

                @Override
                public void showToast(String str) {
                    if (mview!=null){
                        mview.hideLoading();
                        mview.showToast(str);
                    }

                }
            });
    }
}
