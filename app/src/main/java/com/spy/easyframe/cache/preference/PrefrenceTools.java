package com.spy.easyframe.cache.preference;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/28.
 */
public final class PrefrenceTools{
    public static String getSlogen(Context context){
        return new SettingPreference(context).getSlogen();
    }

    public static boolean updateSlogen(Context context,String slogen){
        return new SettingPreference(context).updateSlogen(slogen);
    }
}
