package com.spy.easyframe.system;

import android.content.Context;
import android.content.Intent;

import com.spy.easyframe.ui.WebViewActivity;

public class IntentTools {

    /**
     * params
     */
    public static final String PARAMS_TITLE = "TITLE";
    public static final String PARAMS_URL="PARAMS_URL";


    /**
    *跳转webview
    */
    public static Intent getWebViewIntent(Context context,String title,String url){
        Intent intent = new Intent();
        intent.putExtra(PARAMS_TITLE,title);
        intent.putExtra(PARAMS_URL,url);
        intent.setClass(context, WebViewActivity.class);
        return intent;
    }
}
