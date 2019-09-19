package presenter;

import android.util.Log;

import net.HotSonModel;
import net.MyResultCall;

import base.BasePresenter;
import bean.HotSonBean;
import bean.HotnumBean;
import view.HotSonView;

/**
 * Created by ASUS on 2019/9/7.
 */

public class HotSonPresenter extends BasePresenter<HotSonView> {

    private HotSonModel hotSonModel;


    @Override
    protected void initModel() {
        hotSonModel = new HotSonModel();
        addModel(hotSonModel);
    }

    public void setDadas(int id) {
         hotSonModel.setDatas(id, new MyResultCall<HotSonBean>() {
             @Override
             public void getDatas(HotSonBean hotSonBean) {
                 if (mview!=null){
                            mview.hideLoading();
                            if (hotSonBean!=null){
                                mview.getDatas(hotSonBean);
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

    public void setnum(int num) {
        hotSonModel.setNum(num, new MyResultCall<HotnumBean>() {
            @Override
            public void getDatas(HotnumBean hotnumBean) {
                if (mview!=null){
                    mview.hideLoading();
                    if (hotnumBean!=null){
                        mview.getNums(hotnumBean);
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
