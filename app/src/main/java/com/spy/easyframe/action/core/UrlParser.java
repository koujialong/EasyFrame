package com.spy.easyframe.action.core;

import android.text.TextUtils;

import com.spy.easyframe.util.MapUtils;
import com.spy.easyframe.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/30.
 */
public class UrlParser {

    private String url;
    private String path;
    private Map<String, String> paramMap = new HashMap<>();

    public UrlParser(String url) {
        this.url = url;
        initUrl();
    }

    private void initUrl() {
        if (StringUtils.isBlank(url)) {
            return;
        }

        paramMap.clear();

        int index = url.indexOf("?");
        if (index != -1) {
            //包含参数的url
            path = url.substring(0, index);
            String paramString = url.substring(index + 1);
            if (StringUtils.isNotBlank(paramString)) {
                String[] paramArray = paramString.split("&");
                for (int i = 0; i < paramArray.length; i++) {
                    if (StringUtils.isNotBlank(paramArray[i])) {
                        String[] params = paramArray[i].split("=");
                        if (params.length > 1) {
                            paramMap.put(params[0], params[1]);
                        }
                    }
                }
            }
        } else {
            //不包含参数的url
            path = url;
        }
    }

    private String getUrlWithQueryString(){
        String tmpUrl=path;
        //此处必须用isEempty
        if (StringUtils.isEmpty(tmpUrl)){
            return "";
        }
        if (!MapUtils.isEmpty(paramMap)) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                String paramString=entry.getKey()+"="+entry.getValue();
                if (tmpUrl.indexOf("?")==-1) {
                    tmpUrl+="?"+paramString;
                }else {
                    if (tmpUrl.endsWith("?")) {
                        tmpUrl+=paramString;
                    }else {
                        tmpUrl+="&"+paramString;
                    }
                }
            }
        }
        return tmpUrl;
    }

    public void addParamMap(Map<String,String> map){
        for (Map.Entry<String, String> entry : this.paramMap.entrySet()) {
            addParam(entry.getKey(),entry.getValue());
        }
    }

    public void addParam(String key,long value){
        String stringValue=String.valueOf(value);
        addParam(key,stringValue);
    }

    public void addParam(String key,float value){
        String stringValue=String.valueOf(value);
        addParam(key,stringValue);
    }

    public void addParam(String key,double value){
        String stringValue=String.valueOf(value);
        addParam(key,stringValue);
    }

    public void addParam(String key,int value){
        String stringValue=String.valueOf(value);
        addParam(key,stringValue);
    }

    public void addParam(String key,boolean value){
        String stringValue=String.valueOf(value);
        addParam(key,stringValue);
    }

    public void addParam(String key,String value){
        if (StringUtils.isEmpty(key)) {
            return;
        }
        paramMap.put(key, TextUtils.isEmpty(value)?"":value);
    }

    public void removeKey(String key){
        if (StringUtils.isBlank(key)) {
            return;
        }

        if (MapUtils.isEmpty(paramMap)) {
            return;
        }

        paramMap.remove(key);
    }

    public String getParamValue(String key){
        if (StringUtils.isBlank(key)) {
            return null;
        }

        if (MapUtils.isEmpty(paramMap)){
            return null;
        }

        return paramMap.get(key);
    }

    public String getOriginalUrl(){
        return url;
    }

    /**
    * 获取path和当前参数拼接的url
    */
    public String getUrl(){
        return getUrlWithQueryString();
    }

    /**
    * 获取path
    */
    public String getPath(){
        return path;
    }

    public Map<String,String> getParamMap(){
        return paramMap;
    }
}
