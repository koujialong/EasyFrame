package com.spy.easyframe.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.spy.easyframe.R;


/**
 * 标题栏
 */
public class TitleBar extends RelativeLayout {

    private Context mContext;
    private TextView title;
    private TextView leftTitle;
    private TextView centerTitle;
    private TextView rightTitle;
    private TextView rightTitle2;
    private ImageView leftIcon;
    private ImageView rightIcon;
//    private ProgressBar refreshIcon;
    private ImageView refreshIcon;

    private GestureDetector mGesture;
    private OnDoubleClickListener onDoubleClickListener;

    //自定义监听器接口
    public interface OnDoubleClickListener {
        void onDoubleClick();
    }

    //设置双击事件监听器的方法
    public void setOnDoubleClickListener(OnDoubleClickListener onDoubleClickListener) {
        this.onDoubleClickListener = onDoubleClickListener;
    }



    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context) {
        this.mContext = context;

        mGesture = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                if (onDoubleClickListener != null) {
                    onDoubleClickListener.onDoubleClick();
                }
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return super.onFling(e1, e2, velocityX, velocityY);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //touch事件传给onTouchEvent()
        return mGesture.onTouchEvent(event);
    }

    /**
     * 初始化标题栏，适用于标题在中间的情况
     */
    public void setTitleInfo(String title,OnClickListener listener1) {
        LayoutInflater.from(mContext).inflate(R.layout.vw_titlebar_center, this, true);
        leftIcon = (ImageView) findViewById(R.id.titlebar_left_icon);
        centerTitle = (TextView) findViewById(R.id.titlenar_contentext);

        setTitle(centerTitle, title);
        setIcon(leftIcon, listener1);
    }

    private void setTitle(TextView button, String res) {
        button.setText(res);
        button.setVisibility(View.VISIBLE);
    }

    private void setIcon(ImageView button, int resId, OnClickListener listener) {
        if (resId != 0) {
            button.setImageResource(resId);
            //button.setVisibility(View.VISIBLE);
            if (listener != null) {
                button.setOnClickListener(listener);
            }
        }
    }
    private void setIcon(ImageView button,OnClickListener listener) {
            //button.setVisibility(View.VISIBLE);
            if (listener != null) {
                button.setOnClickListener(listener);
        }
    }


}
