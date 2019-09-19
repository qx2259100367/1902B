package view;


import java.util.ArrayList;

import base.BaseView;
import bean.DailyBean;

/**
 * @author xts
 *         Created by asus on 2019/8/26.
 */

public interface DailyNewsView extends BaseView {
            void  getDatas(DailyBean beans);
            void showToast(String str);
}
