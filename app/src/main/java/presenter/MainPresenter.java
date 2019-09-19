package presenter;


import base.BasePresenter;
import model.MainModel;
import view.MainView;

/**
 * @author xts
 *         Created by asus on 2019/8/26.
 */

public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mMainModel;


    @Override
    protected void initModel() {
        mMainModel = new MainModel();
        addModel(mMainModel);
    }

  public void setDatas(){
        mMainModel.setDatas();
  }
}
