package com.spy.easyframe.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.spy.easyframe.R;
import com.spy.easyframe.util.LogUtils;

/**
* 对话框管理类
* @date: 2017/3/14 10:11
* @author: KJL
*/
public class DialogBuilder {
    private static final String TAG=DialogBuilder.class.getSimpleName();

    /**
     * parms
     */
    private DialogOneListener mDialogOneListener;
    private DialogDoubleListener mDialogDoubleListener;

    /**
     * 单按钮监听
     */
    public interface DialogOneListener{
        //中间按钮
        void onCenterBtnClick();
    }

    /**
     * 双按钮监听
     */
    public interface DialogDoubleListener{
        //从左往右第一个按钮
        void onFirstBtnClick();
        //从左往右第二个按钮
        void onSecondBtnClick();
    }

    public void setOnDialogOneListener(DialogOneListener mDialogOneListener) {
        this.mDialogOneListener = mDialogOneListener;
    }

    public void setOnDialogDoubleListener(DialogDoubleListener mDialogDoubleListener) {
        this.mDialogDoubleListener = mDialogDoubleListener;
    }

    /**
     * 只有一个按钮,一个title的对话框
     */
    public Dialog buildOneBtnDialogProperty(Context activity, String content, String title) {
        final Dialog dialog = new Dialog(activity, R.style.dialog);
        View view = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.dialog_layout_ok_confirm, null);
        dialog.setContentView(view);

        final Button buttonOK = (Button) dialog.findViewById(R.id.dialog_button_ok);
        buttonOK.setText(title);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogOneListener != null) {
                    mDialogOneListener.onCenterBtnClick();
                }
                dialog.dismiss();
            }
        });
        final TextView tvContent = (TextView) dialog.findViewById(R.id.text_content);
        tvContent.setText(content);

        dialog.setCanceledOnTouchOutside(false);

        try {

            dialog.show();

        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return dialog;
    }

    /**
     * 一个文本两个按钮的对话框
     *
     * @param activity
     * @param content
     */
    public Dialog buildDoubleBtnDialogProperty(Context activity, String content) {
        final Dialog dialog = new Dialog(activity, R.style.dialog);
        View view = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(
                R.layout.dialog_layout_titleandconfirm, null);
        dialog.setContentView(view);

        final Button buttonOK = (Button) dialog.findViewById(R.id.dialog_button_ok);
        final Button buttonCancle = (Button) dialog.findViewById(R.id.dialog_button_cancel);
        final TextView contentText = (TextView) dialog.findViewById(R.id.text_content);
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogDoubleListener != null) {
                    mDialogDoubleListener.onFirstBtnClick();
                }
                dialog.dismiss();
            }
        });
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogDoubleListener != null) {
                    mDialogDoubleListener.onSecondBtnClick();
                }
                dialog.dismiss();
            }
        });
        contentText.setText(content);
        dialog.setCanceledOnTouchOutside(false);

        try {
            dialog.show();
        } catch (Exception e) {
            LogUtils.printStackTrace(e);
        }
        return dialog;
    }

}
