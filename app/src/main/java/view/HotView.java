package view;

import java.util.ArrayList;

import base.BaseView;
import bean.HotBean;

/**
 * @author xts
 *         Created by asus on 2019/8/26.
 */

public interface HotView extends BaseView {
        void getDatas(ArrayList<HotBean.RecentBean> beans);
        void showToast(String str);
}
