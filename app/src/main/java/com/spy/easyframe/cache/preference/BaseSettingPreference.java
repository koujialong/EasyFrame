package com.spy.easyframe.cache.preference;

import android.content.Context;

/**
 * Created by Administrator on 2016/12/28.
 */
public class BaseSettingPreference extends PreferencesWriter{
    // 同步目前的preference
    protected static final String FILE_NAME = "setting_Info";

    public BaseSettingPreference(Context context) {
        super(context, FILE_NAME);
    }

    @Override
    protected void initPreferencChange() {

    }
}
