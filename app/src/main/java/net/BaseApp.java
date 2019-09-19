package net;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author xts
 *         Created by asus on 2019/8/27.
 */

public class BaseApp extends Application {
    public static BaseApp sBaseApp;
    public static int mWidthPixels;
    public static int mHeightPixels;
    @Override
    public void onCreate() {
        super.onCreate();

        sBaseApp = this;

        getScreenWH();
    }
    /**
     * 获取屏幕宽高
     */
    private void getScreenWH() {
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        defaultDisplay.getMetrics(metrics);

        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;
    }
    public static Resources getRes(){
        return sBaseApp.getResources();
    }
}
