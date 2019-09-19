package presenter;


import android.util.Log;

import net.MyResultCall;

import base.BasePresenter;
import bean.WxtBean;
import fragments.WechatFragment;
import model.WechatModel;
import view.WechatView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class WechatPresenter extends BasePresenter<WechatView> {

    private WechatModel wechatModel;


    @Override
    protected void initModel() {
        wechatModel = new WechatModel();
        addModel(wechatModel);
    }


    public void setData(int data,String str,int type) {

        if (type== WechatFragment.TYPE_WECH){
                    //加载wech初始化数据
            wechatModel.setDatas(data,new MyResultCall<WxtBean>() {
                @Override
                public void getDatas(WxtBean wxtBean) {
                   if (mview!=null){
                       mview.hideLoading();
                       if (wxtBean!=null){
                           mview.getDatas(wxtBean);
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
        }else {
            //加载查询数据
            wechatModel.querydata(str, new MyResultCall<WxtBean>() {
                @Override
                public void getDatas(WxtBean wxtBean) {
                    if (mview!=null){
                        mview.hideLoading();
                        if (wxtBean!=null){
                            mview.getDatas(wxtBean);
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
}
