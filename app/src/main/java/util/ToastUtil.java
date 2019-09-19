package util;

import android.widget.Toast;


import net.BaseApp;

/**
 * @author xts
 *         Created by asus on 2019/8/27.
 */

public class ToastUtil {
    public static void showShort(String msg){
        Toast.makeText(BaseApp.sBaseApp,msg,Toast.LENGTH_SHORT).show();
    }
    public static void showLong(String msg){
        Toast.makeText(BaseApp.sBaseApp,msg,Toast.LENGTH_LONG).show();
    }
}
