package com.robert.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by robert on 2017/7/18.
 */
public class DateUtil {

    public static boolean isToday(Date date){
        if (date==null){
            return false;
        }

        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);

        int year1=calendar.get(Calendar.YEAR);
        int dayOfYear1=calendar.get(Calendar.DAY_OF_YEAR);

        Date today=new Date();
        calendar.setTime(today);
        int year2=calendar.get(Calendar.YEAR);
        int dayOfYear2=calendar.get(Calendar.DAY_OF_YEAR);

        if (year1==year2&&dayOfYear1==dayOfYear2){
            return true;
        }

        return false;
    }

    public static boolean inSameDay(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int day1 = calendar.get(Calendar.DAY_OF_YEAR);

        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int day2 = calendar.get(Calendar.DAY_OF_YEAR);

        return (year1 == year2) && (day1 == day2);
    }


    public static boolean inSameWeek(Date date1, Date Date2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int weekOfMonth1 = calendar.get(Calendar.WEEK_OF_MONTH);
        calendar.setTime(Date2);
        int year2 = calendar.get(Calendar.YEAR);
        int weekOfMonth2 = calendar.get(Calendar.WEEK_OF_MONTH);

        return (year1 == year2) && (weekOfMonth1 == weekOfMonth2);
    }

    public static String weekString(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek=calendar.get(Calendar.DAY_OF_WEEK);
        String[] weekStrs=new String[]{"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        return  weekStrs[dayOfWeek-1];
    }

}
