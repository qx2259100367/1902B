package base;

import java.util.ArrayList;

/**
 * Created by ASUS on 2019/9/6.
 */

public abstract class BasePresenter<V extends BaseView>{

    public V mview;
    private ArrayList<BaseModel> models=new ArrayList<>();
    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public   void bindView(V v) {
        this.mview = v;
    }

    public void onDestory() {
        mview=null;
        for (int i = 0; i < models.size(); i++) {
            BaseModel baseModel = models.get(i);
            baseModel.destory();
        }
    }

    public void addModel(BaseModel model){
        models.add(model);
    }


}
