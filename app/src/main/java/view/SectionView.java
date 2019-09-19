package view;

import java.util.ArrayList;

import base.BaseView;
import bean.SectionBean;

/**
 * @author xts
 *         Created by asus on 2019/8/26.
 */

public interface SectionView extends BaseView {
    void setDatas(ArrayList<SectionBean.DataBean> beans);
    void showToast(String str);

}
