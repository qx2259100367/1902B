package base;

import com.example.asus.geeknews.R;
import com.google.gson.JsonParseException;

import net.BaseApp;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import model.MainModel;
import model.SectionModel;
import retrofit2.HttpException;
import util.ToastUtil;

/**
 * Created by ASUS on 2019/9/6.
 */

public abstract class BaseObserver<T> implements Observer<T> {

    /**
     * 解析数据失败
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络问题
     */
    public static final int BAD_NETWORK = 1002;
    /**
     * 连接错误
     */
    public static final int CONNECT_ERROR = 1003;
    /**
     * 连接超时
     */
    public static final int CONNECT_TIMEOUT = 1004;
    private final BaseModel mModel;
    private Disposable mDisposable;

    public BaseObserver(BaseModel baseModel) {
        this.mModel = baseModel;
    }

    @Override
    public void onComplete() {}
    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
        mModel.addDisposable(d);
    }

    @Override
    public void onNext(T t) {
            onSucceed(t);
        //请求成功,取消订阅关系
        mDisposable.dispose();
        mModel.removeDisposable(mDisposable);

    }

        //成功数据方法
    protected abstract void onSucceed(T t);

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            //   HTTP错误
            onException(BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            onException(CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            onException(CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            onException(PARSE_ERROR);
        } else {
            if (e != null) {
                error(e.toString());
            } else {
                error(BaseApp.getRes().getString(R.string.unknow_error));
            }
        }
    }
    private void onException(int unknownError) {
        String err = "";
        switch (unknownError) {
            case CONNECT_ERROR:
                err = BaseApp.getRes().getString(R.string.conn_error);
                error(err);
                break;

            case CONNECT_TIMEOUT:
                err = BaseApp.getRes().getString(R.string.conn_timeout);
                error(err);
                break;

            case BAD_NETWORK:
                err = BaseApp.getRes().getString(R.string.net_error);
                error(err);
                break;

            case PARSE_ERROR:
                err = BaseApp.getRes().getString(R.string.parse_error);
                error(err);
                break;

            default:
                err = BaseApp.getRes().getString(R.string.unknow_error);
                break;
        }
        ToastUtil.showShort(err);
    }

    //失败方法
    protected abstract void error(String err);

}
