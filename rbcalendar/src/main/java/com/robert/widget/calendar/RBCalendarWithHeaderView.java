package com.robert.widget.calendar;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robert.util.ViewUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by robert on 2017/8/10.
 */

public class RBCalendarWithHeaderView extends LinearLayout {

    private RBCalendarView calendarView;

    private CalendarHeaderAdapter calendarHeaderAdapter;
    private View separatorView;

    private int separatorLineColor =Color.GRAY;

    private float separatorLineHeight=0.5f;
    private float titileHeight=30;
    private GridView header;

    public GridView getHeader() {
        return header;
    }

    public void setTitileHeight(float titileHeight) {
        this.titileHeight = titileHeight;
        int headerHeight= ViewUtil.dip2px(getContext(),titileHeight);
        LayoutParams params = (LayoutParams) header.getLayoutParams();
        params.height=headerHeight;
        header.setLayoutParams(params);
    }

    public void setSeparatorLineHeight(float separatorLineHeight) {
        this.separatorLineHeight = separatorLineHeight;
        int slh=ViewUtil.dip2px(getContext(),separatorLineHeight);
        LayoutParams params = (LayoutParams) separatorView.getLayoutParams();
        params.height=slh;
        separatorView.setLayoutParams(params);
    }

    public void setSeparatorLineColor(int separatorLineColor) {
        this.separatorLineColor = separatorLineColor;
        separatorView.setBackgroundColor(separatorLineColor);
    }

    public void setSeparatorLineColorResId(int resId){
        separatorView.setBackgroundResource(resId);
    }

    public RBCalendarView getCalendarView() {
        return calendarView;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
        this.getCalendarView().setOnItemClickListener(listener);
    }

    public void setCalendarHeaderAdapter(CalendarHeaderAdapter calendarHeaderAdapter) {
        this.calendarHeaderAdapter = calendarHeaderAdapter;
    }

    public RBCalendarWithHeaderView(Context context) {
        super(context);
        initView();
    }

    public RBCalendarWithHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RBCalendarWithHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        setOrientation(LinearLayout.VERTICAL);

        int headerHeight=ViewUtil.dip2px(getContext(),titileHeight);
        LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,headerHeight);
        header=new GridView(getContext());
        header.setLayoutParams(params);
        header.setNumColumns(7);

        header.setAdapter(new TitleAdapter());

        addView(header);
        separatorView=new View(getContext());
        separatorView.setBackgroundColor(separatorLineColor);

        int slh=ViewUtil.dip2px(getContext(),separatorLineHeight);
        params=new LayoutParams(LayoutParams.MATCH_PARENT,slh);
        int bottomMargin=ViewUtil.dip2px(getContext(),6);
        params.bottomMargin=bottomMargin;
        separatorView.setLayoutParams(params);
        addView(separatorView);

        calendarView=new RBCalendarView(getContext());
        params=new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        calendarView.setLayoutParams(params);

        calendarView.setVerticalScrollBarEnabled(false);
        addView(calendarView);
    }


    class TitleAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            if (calendarHeaderAdapter !=null){
                View v=buildHeaderView(i,view);
                ViewHolder vh= (ViewHolder) v.getTag();
                return calendarHeaderAdapter.getHeaderItemView(i,vh.tvTitle);
            }

            return buildHeaderView(i,view);
        }
    }

    public List<Date> getDateList(){
        return getCalendarView().getDateList();
    }


    public Date getCurrentDate(){
        return getCalendarView().getCurrentDate();
    }


    public void setCurrentDate(Date date){
        getCalendarView().setCurrentDate(date);
    }


    protected View buildHeaderView(int i,View view){
        if (view==null){
            ViewHolder viewHolder;

            if (view == null) {
                viewHolder = new ViewHolder();
                TextView textView=new TextView(getContext());
                int height=ViewUtil.dip2px(getContext(), titileHeight);
                int width=LayoutParams.MATCH_PARENT;
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(width,height);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER);
                view=textView;
                viewHolder.tvTitle=textView;
                view.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder) view.getTag();
            }

            String[] titles={"日","一","二","三","四","五","六"};
            viewHolder.tvTitle.setText(titles[i]);

            if (i==0||i==6){
                viewHolder.tvTitle.setTextColor(Color.parseColor("#FF5000"));
            }else{
                viewHolder.tvTitle.setTextColor(Color.GRAY);
            }
        }

        return view;
    }

    static class ViewHolder {
        public TextView tvTitle;
    }

    public interface CalendarHeaderAdapter {
        View getHeaderItemView(int position,TextView textView);
    }

}
