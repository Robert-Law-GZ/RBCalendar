package com.robert.widget.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.robert.util.DateUtil;
import com.robert.widget.calendar.adapter.RBCalendarAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by robert on 2017/7/17.
 */

public class RBCalendarView extends GridView {
    private Date currentDate = new Date();
    private int dayCountOfMonth;
    private int dayOfWeekFirst;
    private int dayOfWeekLast;

    public RBCalendarAdapter getCalendarAdapter() {
        return calendarAdapter;
    }

    public void setCalendarAdapter(RBCalendarAdapter calendarAdapter) {
        this.calendarAdapter = calendarAdapter;
        initData();
    }

    private RBCalendarAdapter calendarAdapter;


    public List<Date> getDateList() {
        return dateList;
    }

    private List<Date> dateList = new ArrayList();

    /**
     * 获得当前日期
     * @return
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * 设置当前日期
     * @param currentDate
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
        initData();
    }

    public RBCalendarView(Context context) {
        super(context);
        initView();
    }

    public RBCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RBCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        initData();
    }

    private void initData() {
        dateList.clear();

        setNumColumns(7);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 0, 0, 0);

        dayCountOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayOfWeekFirst = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dayCountOfMonth);
        dayOfWeekLast = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.set(Calendar.DATE, 1);
        calendar.add(Calendar.DATE, -dayOfWeekFirst);

        for (int i = 0; i < (dayOfWeekFirst - 1) + dayCountOfMonth + (7 - dayOfWeekLast); i++) {
            calendar.add(Calendar.DATE, 1);
            dateList.add(calendar.getTime());
        }

        setAdapter(new Adapter());
    }

    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            int size = dateList.size();
            return size;
        }

        @Override
        public Object getItem(int i) {
            return dateList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(dateList.get(i));

            if (calendarAdapter != null) {
                int day = calendar.get(Calendar.DATE);
                int month = calendar.get(Calendar.MONTH);
                calendar.setTime(currentDate);

                boolean isCurrentMonth = month == calendar.get(Calendar.MONTH);
                boolean isToday = DateUtil.isToday(dateList.get(i));
                view = calendarAdapter.getView(dateList.get(i), day, isCurrentMonth, isToday);
            } else {
                view = buildView(i, view);
            }


            return view;
        }

    }

    /**
     * 构建视图
     * @param i
     * @param view
     * @return
     */
    protected View buildView(int i, View view) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateList.get(i));

        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            Context context = getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.robert_calendar_day_layout, null);
            viewHolder.tvDay = view.findViewById(R.id.tvDay);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvDay.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");

        int month = calendar.get(Calendar.MONTH);
        calendar.setTime(currentDate);

        if (month == calendar.get(Calendar.MONTH)) {
            viewHolder.tvDay.setTextColor(Color.GRAY);
        } else {
            viewHolder.tvDay.setTextColor(Color.LTGRAY);
        }

        return view;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    static class ViewHolder {
        public TextView tvDay;
    }

}
