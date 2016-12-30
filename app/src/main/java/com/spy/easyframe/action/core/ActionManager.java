package com.spy.easyframe.action.core;

import android.content.Context;
import android.text.TextUtils;

import com.spy.easyframe.ui.WebViewActivity;
import com.spy.easyframe.util.LogUtils;
import com.spy.easyframe.util.StringUtils;

/**
 * action管理类，支持H5、其他app、app内部页面跳转
 */
public class ActionManager {

    private String mActionUrl;//私有协议qla://www.qilin99.com?action=1&url=&id=&ext1=&ext2=&ext3=
    private Context mContext;
    private UrlParser mURLParser;
    private boolean mIsValidAction;//action协议是否有效
    private boolean isUrl;//是否是url连接
    private boolean checkIsContainInAction = false;

    public ActionManager(Context mContext, String mActionUrl) {
        this.mContext = mContext;
        this.mActionUrl = mActionUrl;
        mIsValidAction = checkIfActionCorrect();
        isUrl = checkIsHttpUrl();
    }

    private boolean checkIsHttpUrl() {
        if (!TextUtils.isEmpty(mActionUrl)) {
            if (mActionUrl.startsWith(ActionDefineUtils.HEADER_PROTOCOL_SYSTEM_HTTP)
                    || mActionUrl.startsWith(ActionDefineUtils.HEADER_PROTOCOL_SYSTEM_HTTPS)) {
                checkIsContainInAction = checkIsContainInAction();
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    private boolean checkIsContainInAction() {
        if (StringUtils.isBlank(mActionUrl)) {
            return false;
        }

        if (mActionUrl.contains(ActionDefineUtils.HEADER_PROTOCOL_ACTION)) {
            return true;
        }

        return false;
    }

    public void resetActionURL(String actionUrl) {
        this.mActionUrl = actionUrl;
        mIsValidAction = checkIfActionCorrect();
    }

    public boolean isVailidAction() {
        return mIsValidAction;
    }

    public boolean isUrl() {
        return isUrl;
    }

    public boolean isCheckContainAction() {
        return checkIsContainInAction;
    }

    /**
     * 获取参数数值
     */
    public String getParameter(String key) {
        if (!isVailidAction()) {
            return null;
        }
        if (mURLParser == null) {
            mURLParser = new UrlParser(mActionUrl);
        }

        return mURLParser.getParamValue(key);
    }

    /**
     * 处理当前action跳转
     */
    public boolean processAction() {
        if (!isVailidAction()) {
            return false;
        }

        String actionParam = getParameter("action");
        if (StringUtils.isNotBlank(actionParam)) {
            return processCurrentAction(actionParam);
        }

        return false;
    }

    /**
     * 处理逻辑
     * qla://www.qilin99.com?action=101&url=&id=&ext1=&ext2=&ext3=
     */
    private boolean processCurrentAction(String actionId) {
        int action = StringUtils.stringToInt(actionId);
        long id = StringUtils.stringToInt(getParameter("id"));
        String name=getParameter("name");
        LogUtils.e("TAG",action+"----"+name);
        switch (action) {
            case 101:
                //TODO 处理逻辑
                LogUtils.e("TAG","action协议收到内部信息,lalalal");
                return true;
            case 102:
                //TODO 处理逻辑
                LogUtils.e("TAG","action协议收到外部信息,lalalal"+"名字是"+name);
                WebViewActivity.startActivity(mContext,"","http://www.baidu.com");
                return true;
            default:
                //TODO 处理逻辑
                return false;
        }
    }

    private boolean checkIfActionCorrect() {
        if (StringUtils.isBlank(mActionUrl)) {
            return false;
        }
        LogUtils.e("TAG",mActionUrl);
        if (mActionUrl.startsWith(ActionDefineUtils.HEADER_PROTOCOL_ACTION)) {
            return true;
        }

        return false;
    }

    /**
     * 处理WebView中每次跳转的URL
     */
    public boolean processH5Action() {
        if (!isVailidAction()) {
            return false;
        }

        if (mActionUrl.startsWith(ActionDefineUtils.HEADER_PROTOCOL_ACTION)) {
            return processAction();
        }
        return false;
    }
}
