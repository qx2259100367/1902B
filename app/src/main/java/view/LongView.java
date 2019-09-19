package view;

import base.BaseView;
import bean.LongBean;

/**
 * Created by ASUS on 2019/9/9.
 */

public interface LongView extends BaseView {
    void getDatas(LongBean longBean);
    void showToast(String str);
}
