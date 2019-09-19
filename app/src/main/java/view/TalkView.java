package view;

import java.util.ArrayList;

import base.BaseView;
import bean.TalkBean;

/**
 * Created by ASUS on 2019/9/7.
 */

public interface TalkView extends BaseView {
void getDatas(ArrayList<TalkBean.StoriesBean> beans);
void showTaast(String str);

}
