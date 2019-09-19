package presenter;

import net.MyResultCall;

import base.BasePresenter;
import bean.LongBean;
import model.ShortModel;
import view.ShortView;

/**
 * Created by ASUS on 2019/9/9.
 */

public class ShortPresenter extends BasePresenter<ShortView> {

    private ShortModel shortModel;

    @Override
    protected void initModel() {
        shortModel = new ShortModel();
        addModel(shortModel);
    }

    public void setDatas(String id) {
        shortModel.setDatas(id, new MyResultCall<LongBean>() {
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
