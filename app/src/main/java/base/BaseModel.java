package base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by ASUS on 2019/9/6.
 */

public class BaseModel {
    ////这也是一个容器,Rxjava专门写的用来放Disposable,调用dispose()可以把容器所有的mc
    //Disposable 的网络请求和订阅关系取消
    private CompositeDisposable mCompositeDisposable = null;
    ////由M层自己把网络和订阅关系取消
    public void destory() {
            if (mCompositeDisposable!=null){
                mCompositeDisposable.dispose();
            }
       }
    public void addDisposable(Disposable d) {
        if (mCompositeDisposable== null){
            mCompositeDisposable=  new CompositeDisposable();
        }
        mCompositeDisposable.add(d);
    }

    public void removeDisposable(Disposable mDisposable) {
            if (mCompositeDisposable!=null){
                mCompositeDisposable.remove(mDisposable);
            }
    }
}
