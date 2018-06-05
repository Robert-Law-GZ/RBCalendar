package com.robert.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 视图工具类
 */
public class ViewUtil {

	/**
	 * 给某些adapter调用用以给子视图添加标签重用及其他
	 * 
	 * @param convertView
	 * @param ids
	 */
	public static void storeToTag(View convertView, int... ids) {
		for (int id : ids) {
			convertView.setTag(id, convertView.findViewById(id));
		}
	}

	/**
	 * 使某些视图可用
	 * 
	 * @param views
	 */
	public static void enableView(View... views) {
		View view;
		for (int i = 0; i < views.length; i++) {
			view = views[i];
			view.setEnabled(true);
		}
	}

	/**
	 * 使某些视图不可用
	 * 
	 * @param views
	 */
	public static void unenableView(View... views) {
		View view;
		for (int i = 0; i < views.length; i++) {
			view = views[i];
			view.setEnabled(false);
		}
	}

	/**
	 * 将px转为dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static final int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip转为px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static final int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取屏幕的长宽属性
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm;
	}

	/**
	 * 为给定的TextView中部分字符串添加高亮及点击响应
	 * 
	 * @param tv
	 *            目标TextView
	 * @param dest_str
	 *            目标字符串
	 * @param span
	 *            响应
	 * @param flags
	 *            Spannable.SPAN_EXCLUSIVE_INCLUSIVE等类似Span标志
	 */
	/**
	 * @param tv
	 * @param dest_str
	 * @param span
	 * @param flags
	 */
	public static void setSpanForTextview(TextView tv, String dest_str,
                                          ClickableSpan span, int flags) {
		String text = tv.getText().toString();
		int index = text.indexOf(dest_str);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(text);
		spanBuilder.setSpan(span, index, index + dest_str.length(), flags);
		spanBuilder.setSpan(new ForegroundColorSpan(Color.WHITE), index, index
				+ dest_str.length(), flags);
		tv.setText(spanBuilder);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public static void hideSoftInput(Activity context, EditText edt) {
		try {
			((InputMethodManager) context
					.getSystemService(Activity.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(edt.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public static void hideSoftInput(Activity context) {
		try {
			((InputMethodManager) context
					.getSystemService(Activity.INPUT_METHOD_SERVICE))
					.hideSoftInputFromWindow(context.getCurrentFocus()
							.getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断软键盘状态
	 * 
	 * @param context
	 */
	public static boolean isSoftInputShowed(Activity context) {
		int softInputMode = context.getWindow().getAttributes().softInputMode;
		// System.out.println("softInputMode =" + softInputMode);
		return softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
	}

	/**
	 * 显示软键盘,控件ID可以是EditText,TextView
	 * 
	 * @param context
	 * @param edt
	 */
	public static void showSoftInput(Activity context, EditText edt) {
		((InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE))
				.showSoftInput(edt, 0);
	}

	/**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

}
