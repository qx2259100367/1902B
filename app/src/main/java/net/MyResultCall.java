package net;

/**
 * Created by ASUS on 2019/9/7.
 */

public interface MyResultCall<B> {
    void getDatas(B b);
    void showToast(String str);
}
