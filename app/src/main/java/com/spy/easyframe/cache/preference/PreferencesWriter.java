package com.spy.easyframe.cache.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/12/28.
 */
public abstract class PreferencesWriter {
    protected Context mContext;
    private String mName;

    public static final String KEY_PREFERENCES_VERSION="preferences_version";
    public static final int DEFAULT_PREFERENCES_VERSION=0;

    public PreferencesWriter(Context context, String mName) {
        this.mContext = context;
        this.mName = mName;
        initPreferencChange();
    }

    /**
    * 初始化，通过判断当前版本型号，做出相应修改
    */
    protected abstract void initPreferencChange();

    public Context getContext(){
        return mContext;
    }

    /**
    * 获取当前设置的版本型号
    */
    protected int getVersion(){
        return getPreference().getInt(KEY_PREFERENCES_VERSION,DEFAULT_PREFERENCES_VERSION);
    }

    /**
    * 更新当前版本型号
    */
    protected boolean updateVersion(int verison){
        if (verison>0){
            return updateValue(KEY_PREFERENCES_VERSION,verison);
        }
        return false;
    }

    public boolean clear(){
        SharedPreferences.Editor editor=getPreference().edit();
        editor.clear();
        return editor.commit();
    }

    public boolean removeKey(String key){
        SharedPreferences.Editor editor=getPreference().edit();
        editor.remove(key);
        return editor.commit();
    }

    protected boolean updateValue(String key,int value){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putInt(key,value);
        return editor.commit();
    }

    protected boolean updateValue(String key,String value){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putString(key,value);
        return editor.commit();
    }

    protected boolean updateValue(String key,boolean value){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putBoolean(key,value);
        return editor.commit();
    }

    protected boolean updateValue(String key,float value){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putFloat(key,value);
        return editor.commit();
    }

    protected boolean updateValue(String key,long value){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putLong(key,value);
        return editor.commit();
    }

    protected long getLong(String key,long defaultValue){
        return getPreference().getLong(key,defaultValue);
    }

    protected String getStirng(String key,String defaultValue){
        return getPreference().getString(key,defaultValue);
    }
    protected int getInt(String key,int defaultValue){
        return getPreference().getInt(key,defaultValue);
    }
    protected float getFloat(String key,float defaultValue){
        return getPreference().getFloat(key,defaultValue);
    }
    protected boolean getBoolean(String key,boolean defaultValue){
        return getPreference().getBoolean(key,defaultValue);
    }

    protected SharedPreferences getPreference(){
        if (mContext.getSharedPreferences(mName,Context.MODE_PRIVATE)!=null){
            return mContext.getSharedPreferences(mName,Context.MODE_PRIVATE);
        }
        return null;
    }
}
