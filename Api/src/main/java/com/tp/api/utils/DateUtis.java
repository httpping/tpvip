package com.tp.api.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtis {



    public static String formatMMDD(){

        return getCurrentMonth() + "-" + getCurrentDay();
    }

    /**
     * 获取当前系统时间的月份
     *
     * @return 月份
     */
    public static int getCurrentMonth() {
        // 取到的月份要加1
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }


    /**
     * 获取当前系统时间的日期
     *
     * @return 日期
     */
    public static int getCurrentDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前系统时间的日期
     *
     * @return 日期
     */
    public static int getCurrentHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }


}
