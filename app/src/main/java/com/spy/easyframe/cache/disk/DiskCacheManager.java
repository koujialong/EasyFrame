package com.spy.easyframe.cache.disk;

import android.content.Context;
import android.os.Environment;

import com.spy.easyframe.util.FileUtils;
import com.spy.easyframe.util.LogUtils;

import java.io.File;
import java.io.IOException;

public class DiskCacheManager extends DiskLruCacheHelper {
    private static final String TAG = DiskCacheManager.class.getSimpleName();

    /**
     * param
     */
    private static String DISKCACHE_FOLDER = "diskCache";// 磁盘缓存数据文件夹
    private static volatile DiskCacheManager instance = null;

    /**
     * key
     */
    public static final String TEST_DATA = "TEST_DATA";
    public static final String TEST_MODEL = "TEST_MODEL";
    public static final String KEY_TICK_CHART = "KEY_TICK_CHART";

    private DiskCacheManager(Context context, String dirName) throws IOException {
        super(context, dirName);
    }

    public static DiskCacheManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DiskCacheManager.class) {
                if (instance == null) {
                    try {
                        initDatabaseDir(context, DISKCACHE_FOLDER);
                        instance = new DiskCacheManager(context, DISKCACHE_FOLDER);
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtils.e(TAG, "DiskCacheManager初始化異常!!!", e);
                    }
                }
            }
        }

        return instance;
    }

    /**
     * 设置数据库路径
     *
     * @param context
     * @param uniqueName create at 2014-7-8 上午11:26:07
     * @author xiangyutian
     */
    private static void initDatabaseDir(Context context, String uniqueName) {
        DISKCACHE_FOLDER = getDiskCachePath(context, uniqueName);
        File dir = new File(DISKCACHE_FOLDER);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    private static String getDiskCachePath(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            if (context.getExternalCacheDir()!=null&&context.getExternalCacheDir().getPath()!=null&&
                    FileUtils.isFileExist(context.getExternalCacheDir().getPath())){
                cachePath = context.getExternalCacheDir().getPath();
            }else{
                cachePath = context.getCacheDir().getPath();
            }
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return (cachePath + File.separator + uniqueName);
    }
}