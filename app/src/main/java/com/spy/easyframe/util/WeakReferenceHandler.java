/*
 * WeakReferenceHandler.java
 * classes : com.exchange.client.util.WeakReferenceHandler
 * @author xiangyutian
 * V 1.0.0
 * Create at 2014-4-8 下午4:20:01
 */
package com.spy.easyframe.util;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * com.exchange.client.util.WeakReferenceHandler
 * 
 * @author xiangyutian <br/>
 *         create at 2014-4-8 下午4:20:01
 */
public abstract class WeakReferenceHandler<T> extends Handler {

    private static final String TAG = "WeakReferenceHandler";

    private WeakReference<T> mReference;

    public WeakReferenceHandler(T reference) {
        mReference = new WeakReference<T>(reference);
    }

    @Override
    public final void handleMessage(Message msg) {
        T t = mReference.get();
        if (t == null)
            return;
        handleMessage(t, msg);
    }

    protected abstract void handleMessage(T reference, Message msg);
}
