package com.spy.easyframe.util;

import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtils {
    private static final String TAG = "LogUtils";

    private static boolean ENABLE_LOG = true;

    public static boolean isDebug() {
        return ENABLE_LOG;
    }

    /**
     * 开启/关闭调试模式
     * 
     * @param enabled
     */
    public static final void enableDebugMode(boolean enabled) {
        ENABLE_LOG = enabled;
    }

    /**
     * 打印debug日志
     * 
     * @param tag
     * @param msg
     */
    public static final void d(String tag, String msg) {
        if (ENABLE_LOG) {
            if (tag!=null&&msg!=null) {
                Log.d(tag, msg);
            }
        }
    }

    /**
     * 打印debug日志
     * 
     * @param tag
     * @param msg
     * @param tr
     */
    public static final void d(String tag, String msg, Throwable tr) {
        if (ENABLE_LOG) {
            Log.d(tag, msg, tr);
        }
    }

    /**
     * 打印info日志
     * 
     * @param tag
     * @param msg
     */
    public static final void i(String tag, String msg) {
        if (ENABLE_LOG) {
            Log.i(tag, msg);
        }
    }

    /**
     * 打印info日志
     * 
     * @param tag
     * @param msg
     * @param tr
     */
    public static final void i(String tag, String msg, Throwable tr) {
        if (ENABLE_LOG) {
            Log.i(tag, msg, tr);
        }
    }

    /**
     * 打印warning日志
     * 
     * @param tag
     * @param msg
     */
    public static final void w(String tag, String msg) {
        if (ENABLE_LOG) {
            Log.w(tag, msg);
        }
    }

    public static final void w(String tag, Throwable tr) {
        if (ENABLE_LOG) {
            Log.w(tag, tr);
        }
    }

    /**
     * 打印warning日志
     * 
     * @param tag
     * @param msg
     * @param tr
     */
    public static final void w(String tag, String msg, Throwable tr) {
        if (ENABLE_LOG) {
            Log.w(tag, msg, tr);
        }
    }

    /**
     * 打印error日志
     * 
     * @param tag
     * @param msg
     */
    public static final void e(String tag, String msg) {
        if (ENABLE_LOG) {
            Log.e(tag, msg);
        }
    }

    public static final void e(Throwable e) {
        if (ENABLE_LOG) {
            Log.e(TAG, e.toString(), e);
        }
    }

    public static final void e(Exception e) {
        if (ENABLE_LOG) {
            Log.e(TAG, e.toString(), e);
        }
    }

    /**
     * 打印error日志
     * 
     * @param tag
     * @param msg
     * @param tr
     */
    public static final void e(String tag, String msg, Throwable tr) {
        if (ENABLE_LOG) {
            Log.e(tag, msg, tr);
        }
    }

    /**
     * 打印对象
     * 
     * @param obj
     */
    public static final void p(Object obj) {
        if (ENABLE_LOG) {
            System.out.println(obj);
        }
    }

    /**
     * 输出程序运行时信息,在调试现场调用
     * 
     * @return [file:当前运行文件;method:当前运行方法;LineNumber:当前运行代码行]
     */
    public static String getTraceInfo() {
        if (!ENABLE_LOG) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        sb.append("[file:").append(stacks[1].getFileName()).append(",line:").append(stacks[1].getLineNumber())
                .append(",method:").append(stacks[1].getMethodName() + "];");
        return sb.toString();
    }

    /**
     * 保存日志内容到本地，方便本地查看
     * 
     * @param log
     */
    public synchronized static void logToLocal(String log) {
        System.out.println("logToLocal, log:" + log);
        if (TextUtils.isEmpty(log)) {
            return;
        }
        ENABLE_LOG = canShow();
        if (!ENABLE_LOG) {
            return;
        }
        File file = getLogFile();
        if (file == null) {
            return;
        }
        System.out.println("logToLocal, file:" + file.getAbsolutePath());
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            out.write(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void fileLog(String fileName, String log) {
        System.out.println("logToLocal, log:" + log);
        if (fileName == null || log == null) {
            return;
        }
        if (TextUtils.isEmpty(log)) {
            return;
        }
        ENABLE_LOG = canShow();
        if (!ENABLE_LOG) {
            return;
        }
        File file = getLogFile(fileName);
        if (file == null) {
            return;
        }
        System.out.println("logToLocal, file:" + file.getAbsolutePath());
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            // out.write(log + "\n");
            out.append(log + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delLogFile(String fileName) {
        File file = new File(LOG_DIR + File.separator + fileName);
        LogUtils.d(TAG, "Log file to be deleted:" + LOG_DIR + File.separator + fileName);

        boolean ret = false;
        try {
            ret = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private static final String LOG_DIR = Environment.getExternalStorageDirectory() + File.separator + "SohuLog";

    private static File getLogFile() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH");
        String name = format.format(new Date());

        return getLogFile(name + ".txt");
    }

    private static File getLogFile(String fileName) {
        File file = new File(LOG_DIR);
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }

        file = new File(LOG_DIR + File.separator + fileName);
        LogUtils.d(TAG, "Log file created:" + file);
        if (file.exists()) {
            return file;
        }
        boolean ret = false;
        try {
            ret = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret ? file : null;
    }

    private static final Object mLogLock = new Object();
    private static String mLogFileName = null;

    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;

    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    static {
        mLogFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "sohulog.txt";
    }

    /**
     * 输出日志到文件系统中. 供艾瑞统计测试使用，测试时本地设output=true.请勿提交到SVN上.
     * 
     * @param priority
     * @param tag
     * @param msg
     * @return
     */
    private static int println(int priority, String tag, String msg) {
        boolean output = false;
        if (!output) {
            return 0;
        }

        String[] ps = { "", "", "V", "D", "I", "W", "E", "A" };
        SimpleDateFormat df = new SimpleDateFormat("[MM-dd hh:mm:ss.SSS]");
        String time = df.format(new Date());
        StringBuilder sb = new StringBuilder();
        sb.append(time);
        sb.append("\t");
        sb.append(ps[priority]);
        sb.append("/");
        sb.append(tag);
        int pid = Process.myPid();
        sb.append("(");
        sb.append(pid);
        sb.append("):");
        sb.append(msg);
        sb.append("\n");
        FileWriter writer = null;

        synchronized (mLogLock) {
            try {
                File file = new File(mLogFileName);
                // not exist
                if (!file.exists()) {
                    file.createNewFile();
                }
                writer = new FileWriter(file, true);
                writer.write(sb.toString());
            } catch (FileNotFoundException e) {
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        return 0;
    }

    private static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        return sw.toString();
    }

    public static void printStackTrace(Throwable e) {
        ENABLE_LOG = canShow();
        if (ENABLE_LOG) {
            if (e != null) {
                e.printStackTrace();
                println(ERROR, TAG, getStackTraceString(e));
            }
        }
    }

    public static boolean canShow() {
        boolean b = false;
        // String value =
        // PropertiesHelper.getValueA(PropertiesHelper.PROPERTIES_DOMAIN_CONTROL_PATH,
        // PropertiesHelper.SHOW_LOG);
        // if (StringUtils.isNotEmpty(value)) {
        // b = ("true".equals(value));
        // }
        return true;
    }
}
