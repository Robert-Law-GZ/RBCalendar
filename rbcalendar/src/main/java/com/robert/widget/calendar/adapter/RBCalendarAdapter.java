package com.robert.widget.calendar.adapter;

import android.view.View;

import java.util.Date;

/**
 * Created by robert on 2017/7/18.
 */

public interface RBCalendarAdapter {
    /**
     * 生成日历中每一天的视图
     * @param date 日期
     * @param day  当月几号
     * @param isCurrentMonth 该日期是否是当月
     * @param isToday    该日期是否是为当天
     * @return
     */
    View getView(Date date, int day, boolean isCurrentMonth, boolean isToday);

}
