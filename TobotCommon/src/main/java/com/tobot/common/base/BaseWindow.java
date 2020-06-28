package com.tobot.common.base;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

/**
 * @author houdeming
 * @date 2018/4/13
 */
public abstract class BaseWindow {
    protected Context mContext;
    private WindowManager mWindowManager;
    private View mView;

    public BaseWindow(Context context) {
        mContext = context;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 设置布局参数
        LayoutParams mLayoutParams = new LayoutParams();
        // 设置window TYPE
        mLayoutParams.type = LayoutParams.TYPE_PHONE;
        // 设置图片格式，效果位背景透明
        mLayoutParams.format = PixelFormat.RGBA_8888;
        // 设置Window flag
        mLayoutParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.gravity = getGravity();
        // 以屏幕右下角为原点，设置X y初始值
        mLayoutParams.x = getLayoutOriginalX();
        mLayoutParams.y = getLayoutOriginalY();
        // 设置悬浮窗口长宽数据
        mLayoutParams.width = isWidthMatchParent() ? LayoutParams.MATCH_PARENT : LayoutParams.WRAP_CONTENT;
        mLayoutParams.height = isHeightMatchParent() ? LayoutParams.MATCH_PARENT : LayoutParams.WRAP_CONTENT;

        mView = getView(mInflater);
        mWindowManager.addView(mView, mLayoutParams);
    }

    public void removeView() {
        if (mWindowManager != null && mView != null) {
            mWindowManager.removeView(mView);
            mWindowManager = null;
        }
    }

    /**
     * 获取view
     *
     * @param inflater
     * @return
     */
    public abstract View getView(LayoutInflater inflater);

    /**
     * 获取位置
     *
     * @return
     */
    public abstract int getGravity();

    /**
     * 是否宽全包裹
     *
     * @return
     */
    public abstract boolean isWidthMatchParent();

    /**
     * 是否高全包裹
     *
     * @return
     */
    public abstract boolean isHeightMatchParent();

    /**
     * 获取X轴的初始值
     *
     * @return
     */
    public abstract int getLayoutOriginalX();

    /**
     * 获取Y轴的初始值
     *
     * @return
     */
    public abstract int getLayoutOriginalY();
}
