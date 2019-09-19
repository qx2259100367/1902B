package view;


import base.BaseView;
import bean.WxtBean;

/**
 * @author xts
 *         Created by asus on 2019/8/26.
 */

public interface WechatView extends BaseView {
        void getDatas(WxtBean wxtBean);
    void showToast(String str);
}
