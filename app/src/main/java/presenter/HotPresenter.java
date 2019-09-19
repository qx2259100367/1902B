package presenter;


import net.MyResultCall;

import java.util.ArrayList;

import base.BasePresenter;
import bean.HotBean;
import model.HotModel;
import view.HotView;

/**
 * @author xts
 *         Created by asus on 2019/9/6.
 */

public class HotPresenter extends BasePresenter<HotView> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
        addModel(hotModel);
    }

    public void setDatas() {
            hotModel.setDatas(new MyResultCall<ArrayList<HotBean.RecentBean>>() {
                @Override
                public void getDatas(ArrayList<HotBean.RecentBean> recentBeans) {
                    if (mview!=null){
                        mview.hideLoading();
                        if (recentBeans.size()>0&&recentBeans!=null){
                            mview.getDatas(recentBeans);
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
