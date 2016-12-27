package com.spy.easyframe.ui;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.spy.easyframe.R;
import com.spy.easyframe.system.IntentTools;
import com.spy.easyframe.ui.widget.ProgressWebView;
import com.spy.easyframe.ui.widget.TitleBar;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 基于webview的承载H5的页面
 */
public class WebViewActivity extends BaseActivity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    @Bind(R.id.mTitle)
    TitleBar mTitlebar;
    @Bind(R.id.webView)
    ProgressWebView mWebView;

    /**
     * view
     */
    //private TitleBar mTitleBar;
    private View.OnClickListener mTitleBarLeftTxtListener;
    private View.OnClickListener mTitleBarRightImageListener;

    /**
     * params
     */
    private String mTitle;
    private String mUrl;
    private String shareUrl;
    private String mText;
    private String mImgUrl;
    private String mShareTitle;
    private ClipboardManager cmb;
    private InnerHandler mHandler = new InnerHandler(this);
    private static final int MESSAGE_PARAM_INFO = 10001;

    private String mImageUrl;
    private boolean flag = true;
    private boolean imageFlag = false; //判断是重要消息还是专题 true重要消息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        parseIntent(getIntent());
        initView();
        initListener();
        loadUrl();
    }

    private void parseIntent(Intent intent) {
        mTitle = intent.getStringExtra(IntentTools.PARAMS_TITLE);
        mUrl = intent.getStringExtra(IntentTools.PARAMS_URL);
    }

    @Override
    protected void initData() {

    }

    protected void initView() {
        mTitlebar.setTitleInfo(mTitle, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    protected void initListener() {
        //启用支持javascript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("UTF -8");//设置默认为utf-8
        //    添加js对象(必要)
        //mWebView.addJavascriptInterface(new JsToJavaOperation(this), "app_client");

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        //屏幕适配
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }
                // Otherwise allow the OS to handle things like tel, mailto, etc.
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                finish();
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    webLog(request.getUrl().toString());
                }
                return super.shouldInterceptRequest(view, request);

            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    //webLog(url);
                }
                return super.shouldInterceptRequest(view, url);
            }

        });
    }

    @Override
    protected void injectComponent() {

    }

    private void loadUrl() {
        if (mUrl != null) {
            if (mUrl.startsWith("http")) {
                //WebView加载web资源
                mWebView.loadUrl(mUrl);
            } else {
                //WebView加载web资源
                mWebView.loadData(mUrl, "text/html; charset=UTF-8", null);
            }
        }
    }

    @Override
    protected void onPause() {
        mWebView.reload();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // remove handler message
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }

        mTitle = null;
        mUrl = null;
        mText = null;
        mImgUrl = null;
        mShareTitle = null;
        imageFlag = false;
    }

    @Override
    public String getActivityTag() {
        return TAG;
    }

    //改写物理按键——返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    private static class InnerHandler extends Handler {
        private WeakReference<WebViewActivity> fragmentReference;

        public InnerHandler(WebViewActivity fragment) {
            fragmentReference = new WeakReference<WebViewActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final WebViewActivity activity = fragmentReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case MESSAGE_PARAM_INFO:
                    break;
                default:
                    break;
            }
        }
    }

    public static void startActivity(Context context, String title, String url) {
        context.startActivity(IntentTools.getWebViewIntent(context, title, url));
    }

}
