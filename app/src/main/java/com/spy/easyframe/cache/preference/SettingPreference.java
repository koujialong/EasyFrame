package com.spy.easyframe.cache.preference;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/28.
 */
public class SettingPreference extends BaseSettingPreference{
    //版本号
    private static final int VERSION = 1;

    //宣传语
    private static final String SETTING_SLOGEN="SETTING_SLOGEN";

    public SettingPreference(Context context) {
        super(context);
    }

    @Override
    protected void initPreferencChange() {
        int version=getVersion();

        if (version==0){
            //版本改变的更改
        }

        if (version!=VERSION){
            updateVersion(VERSION);
        }
    }

    public boolean updateSlogen(String slogen){
        return updateValue(SETTING_SLOGEN,slogen);
    }

    public String getSlogen(){
        return getStirng(SETTING_SLOGEN,"");
    }

}
