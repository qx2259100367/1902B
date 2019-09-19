package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class DateUtil {
    public static String getCurrentTime(){
        Date date = new Date();
        //20190908 09-08,2019-09-08 18:20:34
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    /**
     * 格式化date 返回yyyyMMdd 比如20190908
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        //20190908 09-08,2019-09-08 18:20:34
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    public static int getYear(){
        //日历
        Calendar instance = Calendar.getInstance();
        //获取当前的年份
        return instance.get(Calendar.YEAR);
    }
    public static int getMonth(){
        //日历
        Calendar instance = Calendar.getInstance();
        //获取当前的年份
        return instance.get(Calendar.MONTH);
    }
    public static int getDay(){
        //日历
        Calendar instance = Calendar.getInstance();
        //获取当前的年份
        return instance.get(Calendar.DAY_OF_MONTH);
    }
}
