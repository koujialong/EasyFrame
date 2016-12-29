package com.spy.easyframe.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * @author 徐轲
 * @Version V1.0
 * @Date 2013-7-30 下午4:45:29
 * @Description
 */
public class StorageUtils {

    private static String sdCardPath = null;

    /**
     * 判断SD卡是否存在
     * 
     * @return
     */
    public static boolean isSDCardExists() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * 获取SD卡路径
     * 
     * @return
     */
    public static String getSdCardPath() {
        if (StringUtils.isBlank(sdCardPath)) {
            sdCardPath = Environment.getExternalStorageDirectory().getPath();
            int separatorPos = sdCardPath.length() - 1;
            if (separatorPos > 0) {
                if (sdCardPath.substring(separatorPos).equals(File.separator) == false) {
                    sdCardPath += File.separator;
                }
            }

        }
        return sdCardPath;
    }

    /**
     * 获取可用空间
     * 
     * @return
     */
    public static long getAvailableSpace() {
        long localSpace = -1L;
        if (isSDCardExists()) {
            File file = new File(getSdCardPath());
            file.mkdirs();
            StatFs localStatFs = new StatFs(getSdCardPath());
            localSpace = (long) localStatFs.getAvailableBlocks() * localStatFs.getBlockSize();
        }

        return localSpace;
    }

    /**
     * 获取已用空间
     * 
     * @return
     */
    public static long getUsedSpace() {
        long localSpace = -1L;
        if (isSDCardExists()) {
            File file = new File(getSdCardPath());
            file.mkdirs();
            StatFs localStatFs = new StatFs(getSdCardPath());
            localSpace = (long) localStatFs.getBlockCount() * localStatFs.getBlockSize();
        }

        return (localSpace - getAvailableSpace());
    }

    /**
	 * 
	 */
    public static File getDefaultCacheDir(Context context, String type) {
        return context.getExternalFilesDir(type);
    }

}
