package com.thanatos.custom_toast;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * by thanatos on 2016/8/12.
 */

public class CustomToast {
    private WindowManager wm;
    private WindowManager.LayoutParams params;
    @SuppressLint("StaticFieldLeak")
    private static CustomToast customToast;
    private static final String TAG = "thanatos";
    //默认布局所在位置的x轴偏移量
    private static int mX = 0;
    //默认布局所在位置的y轴偏移量
    private static int mY = 200;
    private View mView = null;
    //默认的延时时间
    private int duration = 1000;
    /**
     * 对外开放的两个延时时间
     */
    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    private Context context;
    //控件的点击事件
    private CustomT customT;

    /**
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public CustomToast(Context context) {
        this.context = context;
        init(context);
    }

    public CustomToast(Context context, View view) {
        initView(context, view);
    }

    public CustomToast(Context context, CharSequence text) {
        initText(context, text);
    }

    private CustomToast(Context context, View view, int duration) {
        this.mView = view;
        this.duration = duration;
        init(context);
    }

    private CustomToast(Context context, CharSequence text, int duration) {
        this.duration = duration;
        initText(context, text);
    }

    public static CustomToast setText(Context context, CharSequence text, int duration) {
        if (customToast == null) {
            synchronized (CustomToast.class) {
                if (customToast == null) {
                    customToast = new CustomToast(context, text, duration);
                }
            }
        }
        return customToast;
    }

    public static CustomToast setView(Context context, View view, int duration) {
        if (customToast == null) {
            synchronized (CustomToast.class) {
                if (customToast == null) {
                    customToast = new CustomToast(context, view, duration);
                }
            }
        }
        return customToast;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initText(Context context, final CharSequence text) {
        init(context);
        mView = new TextView(context);
        TextView tv = (TextView) mView;
        tv.setText(text);

    }

    private void initView(Context context, View view) {
        init(context);
        mView = view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init(Context context) {
        wm = (WindowManager) context.getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();

        // 设置悬浮窗的长得宽
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.format = PixelFormat.TRANSLUCENT;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;

        params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        params.gravity = Gravity.BOTTOM;

    }

    public void show() {
        if (mView == null) {
            Log.e(TAG, "CustomToast: you have not a view");
            return;
        }
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //对布局文件点击事件的回调
                if (customT==null)return;//如果点击事件为空则返回
                customT.OnClick();
            }
        });
        //x y 相对于Gravity的位置坐标
        params.x = mX;
        params.y = mY;
        wm.addView(mView, params);
        Log.w(TAG, "init: " + duration);
        if (duration < 1000) {
            if (duration == LENGTH_SHORT) {
                Log.w(TAG, "show:   LENGTH_SHORT");
                duration = 1000;
            } else if (duration == LENGTH_LONG) {
                Log.w(TAG, "show:   LENGTH_LONG");
                duration = 3000;
            } else {
                duration = 1000;
            }

            mHandler.sendEmptyMessageDelayed(0x0, duration);
        } else {
            mHandler.sendEmptyMessageDelayed(0x0, duration);
        }
    }


    public void setGravity(int gravity, int xOffself, int yOffself) {
        params.gravity = gravity;
        mX = xOffself;
        mY = yOffself;
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setText(CharSequence text) {
        this.mView = new TextView(this.context);
        TextView tv = (TextView) this.mView;
        tv.setText(text);
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @SuppressLint("all")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x0) {
                wm.removeView(mView);
            }
        }
    };

    public void setOnCustomToastClickListener(CustomT customT) {
        this.customT = customT;
    }


    public interface CustomT {
        void OnClick();
    }


}
