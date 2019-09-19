package view;

import base.BaseView;
import bean.HotSonBean;
import bean.HotnumBean;

/**
 * Created by ASUS on 2019/9/7.
 */

public interface HotSonView extends BaseView {
    void getDatas(HotSonBean beans);
    void getNums(HotnumBean bean);
    void showToast(String str);
}
