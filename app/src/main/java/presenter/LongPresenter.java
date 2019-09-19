package presenter;

import net.MyResultCall;

import base.BasePresenter;
import bean.LongBean;
import model.LongModel;
import view.LongView;

/**
 * Created by ASUS on 2019/9/9.
 */

public class LongPresenter extends BasePresenter<LongView > {

    private LongModel longModel;

    @Override
    protected void initModel() {
        longModel = new LongModel();
        addModel(longModel);
    }

    public void setDatas(String id) {
        longModel.setDatas(id, new MyResultCall<LongBean>() {
            @Override
            public void getDatas(LongBean longBean) {
                if (mview!=null){
                    mview.hideLoading();
                    if (longBean!=null){
                        mview.getDatas(longBean);
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
