package com.spy.easyframe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by xiangyutian on 15/12/26.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class TimeUtils {
    private static final String TAG = "TimeUtils";

    // public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new
    // SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // public static final SimpleDateFormat DATE_FORMAT_DATE = new
    // SimpleDateFormat("yyyy-MM-dd");

    /**
     * long time to string
     *
     * @param timeInMillis
     * @param dateFormat
     * @return
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * long time to string, format is yyyy-MM-dd HH:mm:ss
     *
     * @param timeInMillis
     * @return
     */
    public static String getTime(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getDateTime(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    /**
     * long time to string, format is yyyy-MM-dd HH:mm:ss
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeM_D_HM(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("MM月dd日 HH:mm", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    /**
     * long time to string, format is yyyy-MM-dd
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeYMD(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    /**
     * long time to string, format is MM-dd
     *
     * @param timeInMillis
     * @return
     */
    public static String getTimeMD(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getFormatTimeMD(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("MM月dd日", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getTimeMDHMS(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("MM/dd  HH:mm:ss", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getTimeMDHM(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("MM/dd  HH:mm", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getTimeHHMM(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static String getTimeHH(long timeInMillis) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat("HH", Locale.getDefault());
        return getTime(timeInMillis, defaultFormat);
    }

    public static boolean isSameDate(long timeInMillis1,long timeInMillis2){
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date1=getTime(timeInMillis1,defaultFormat);
        String date2=getTime(timeInMillis2,defaultFormat);
        if (date1.equals(date2)){
            return true;
        }else {
            return false;
        }
    }

    public static String getTimeMS(int totalSeconds) {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0) {
            return String.format("%d时%02d分%02d秒", hours, minutes, seconds);
        } else {
            return String.format("%02d分%02d秒", minutes, seconds);
        }
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * get current time in milliseconds, format is yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * get current time in milliseconds
     *
     * @return
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }

    /**
     * 返回时间：HH:mm
     *
     * @return
     */
    public static String getTimeOfHourAndMinites() {
        Calendar calendar = Calendar.getInstance();
        // yyyy.MM.dd G 'at' HH:mm:ss vvvv
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(calendar.getTime());
        return time;
    }

    /**
     * 根据设定时间，返回时间：HH:mm
     *
     * @param t
     * @return
     */
    public static String getTimeOfHourAndMinites(long t) {
        // yyyy.MM.dd G 'at' HH:mm:ss vvvv
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time = sdf.format(t);
        return time;
    }

    /**
     * 根据设定时间，返回时间：HH:mm
     *
     * @param t
     * @return
     */
    public static String getTimeOfHourAndMinitesAndSecond(long t) {
        // yyyy.MM.dd G 'at' HH:mm:ss vvvv
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(t);
        return time;
    }

    /**
     * 时间转换
     *
     * @param nowTime
     * @return create at 2014-4-22 下午7:43:41
     * @author xiangyutian
     */
    public static String getDisplayDate(String nowTime) {
        StringBuffer date = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        // yyyy.MM.dd G 'at' HH:mm:ss vvvv
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        try {
            Date Time = sdf.parse(nowTime);
            calendar.setTime(Time);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            if (hour < 10) {
                date.append(0);
            }
            date.append(hour).append(":");
            if (min < 10) {
                date.append(0);
            }
            date.append(min);
        } catch (ParseException e) {

            LogUtils.e(e);
        }
        return date.toString();
    }

    /**
     * 根据固定格式获取时间long型
     *
     * @param nowTime
     * @return
     */
    public static long getDisplayTime(String nowTime) {
        long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
        try {
            Date data = sdf.parse(nowTime);
            time = data.getTime();
        } catch (Throwable e) {
            LogUtils.printStackTrace(e);
        }
        return time;
    }

    /**
     * 根据固定格式获取时间long型
     *
     * @param nowTime
     * @return
     */
    public static long getTimeTypeStr2Long(String nowTime) {
        long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date data = sdf.parse(nowTime);
            time = data.getTime();
        } catch (Throwable e) {
            LogUtils.printStackTrace(e);
        }
        return time;
    }

    /**
     * 根据固定格式获取时间long型
     *
     * @param nowTime
     * @return
     */
    public static long getTimeTypeStr2Long(String nowTime, String format) {
        long time = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date data = sdf.parse(nowTime);
            time = data.getTime();
        } catch (Throwable e) {
            LogUtils.printStackTrace(e);
        }
        return time;
    }

    /**
     * 时分秒,用于播放历史记录
     *
     * @param totalSeconds
     * @return create at 2014-5-25 下午7:05:52
     * @author xiangyutian
     */
    public static String getDisplayTimeFromSeconds(int totalSeconds) {
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    public static String getCurrentTime() {
        String datetime = "";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        datetime = sdf.format(date);
        return datetime;
    }

    /**
     * 获取传入时间戳当天零点的时间戳值
     *
     * @param timestamp
     * @return
     */
    public static long getDay0Hour(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取传入时间戳下一天零点的时间戳值
     *
     * @param timestamp
     * @return
     */
    public static long getNextDay0Hour(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 获取传入时间戳上一天零点的时间戳值
     *
     * @param timestamp
     * @return
     */
    public static long getPreviousDay0Hour(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR, 00);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 00);
        calendar.set(Calendar.MILLISECOND, 00);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTimeInMillis();
    }


    /**
     * 把毫秒数转换成天数
     *
     * @param timestamp
     * @return
     */
    public static int convertTime2Day(long timestamp) {
        if (timestamp < 0) {
            return 0;
        }
        int day = (int) (timestamp / 1000 / 3600 + 1);
        return day;
    }

    private static final String DATE_YMDHMS = "HH:mm";
    private static final String DATETIME = "yyyy-MM-dd HH:mm";
    private static final String DATE = "yyyy-MM-dd";
    private static final String TIME = "HH:mm:dd";
    private static final String YEAR = "yyyy";
    private static final String MONTH = "MM";
    private static final String DAY = "dd";
    private static final String HOUR = "HH";
    private static final String MINUTE = "mm";
    private static final String SEC = "ss";
    private static final String DATETIMECHINESE = "yyyy年MM月dd日 HH时mm分ss秒";
    private static final String DATECHINESE = "yyyy年MM月dd日";
    private static final String SIMPLEDATECHINESE = "MM月dd日";
    private static final String SIMPLETIME = "HH:mm";

    /**
     * 判断一个字符串日期是否过期
     *
     * @param dateTime
     * @return (int)&nbsp;过期返回1，不过期返回0
     * @throws ParseException
     */
    public static int isOutOfDate(String dateTime) throws ParseException {
        long nowTimeLong = new Date().getTime();
        long ckTimeLong = new SimpleDateFormat(DATETIME).parse(dateTime)
                .getTime();
        if (nowTimeLong - ckTimeLong > 0) {// 过期
            return 1;
        }
        return 0;
    }

    /**
     * 判断是否在一个起止日期内<br/>
     * 例如:2012-04-05 00:00:00~2012-04-15 00:00:00
     *
     * @param start_time
     * @param over_time
     * @return (int)&nbsp;在这个时间段内返回1，不在返回0
     * @throws ParseException
     */
    public static int isOutOfDate(String start_time, String over_time)
            throws ParseException {
        long nowTimeLong = new Date().getTime();
        long ckStartTimeLong = new SimpleDateFormat(DATETIME).parse(start_time)
                .getTime();
        long ckOverTimeLong = new SimpleDateFormat(DATETIME).parse(over_time)
                .getTime();
        if (nowTimeLong > ckStartTimeLong && nowTimeLong < ckOverTimeLong) {
            return 1;
        }
        return 0;
    }

    /**
     * 判断一个自定义日期是否在一个起止日期内<br/>
     * 例如:判断2012-01-05 00:00:00是否在2012-04-05 00:00:00~2012-04-15 00:00:00
     *
     * @param start_time
     * @param over_time
     * @return (int)&nbsp;在这个时间段内返回1，不在返回0
     * @throws ParseException
     */
    public static int isOutOfDate(String time, String start_time,
                                  String over_time) throws ParseException {
        LogUtils.d(TAG, "time =========  " + time + "   start_time ====  " + start_time + " over_time ====   " + over_time);
        long timeLong = new SimpleDateFormat(DATETIME).parse(time).getTime();
        long ckStartTimeLong = new SimpleDateFormat(DATETIME).parse(start_time)
                .getTime();
        long ckOverTimeLong = new SimpleDateFormat(DATETIME).parse(over_time)
                .getTime();
        if (timeLong > ckStartTimeLong && timeLong <= ckOverTimeLong) {
            return 1;
        }
        return 0;
    }

    /**
     * 判断是否在一个时间段内<br/>
     * 例如:8:00~10:00
     *
     * @param time_limit_start
     * @param time_limit_over
     * @return (int) 1在这个时间段内，0不在这个时间段内
     * @throws ParseException
     */
    public static int isInTime(String time_limit_start, String time_limit_over)
            throws ParseException {
        // 获取当前日期
        String nowDate = new SimpleDateFormat(DATE).format(new Date());
        return isOutOfDate(nowDate + " " + time_limit_start, nowDate + " "
                + time_limit_over);
    }

    /**
     * 判断一个自定义时间是否在一个时间段内<br/>
     * 例如:判断02:00是否在08:00~10:00时间段内
     *
     * @param time_limit_start
     * @param time_limit_over
     * @return (int) 1在这个时间段内，0不在这个时间段内
     * @throws ParseException
     */
    public static int isInTime(String time, String time_limit_start,
                               String time_limit_over) throws ParseException {
        String nowDate = new SimpleDateFormat(DATE).format(new Date());
        return isOutOfDate(nowDate + " " + time, nowDate + " "
                + time_limit_start, nowDate + " " + time_limit_over);
    }

    /**
     * 取得自定义月份后的日期，如13个月以后的时间
     *
     * @param monthNum 往后几个月
     * @return 时间字符串
     */
    public static String crateTimeFromNowTimeByMonth(int monthNum) {
        Calendar calendar = new GregorianCalendar(Integer.parseInt(getYear()),
                Integer.parseInt(getMonth()) - 1, Integer.parseInt(getDay()),
                Integer.parseInt(getHour()), Integer.parseInt(getMinute()),
                Integer.parseInt(getSec()));
        calendar.add(Calendar.MONTH, monthNum);
        return new SimpleDateFormat(DATETIME).format(calendar.getTime());
    }

    /**
     * 取得自定义天数后的日期，如13天以后的时间
     *
     * @param dayNum 往后几天
     * @return 时间字符串(DateTime)
     */
    public static String crateTimeFromNowTimeByDay(int dayNum) {
        Calendar calendar = new GregorianCalendar(Integer.parseInt(getYear()),
                Integer.parseInt(getMonth()) - 1, Integer.parseInt(getDay()),
                Integer.parseInt(getHour()), Integer.parseInt(getMinute()),
                Integer.parseInt(getSec()));
        calendar.add(Calendar.DATE, dayNum);
        return new SimpleDateFormat(DATETIME).format(calendar.getTime());
    }

    /**
     * 取得自定义天数后的日期，如13天以后的时间
     *
     * @param dayNum 往后几天
     * @return 时间字符串(Date)
     */
    public static String crateTimeFromNowDayByDay(int dayNum) {
        Calendar calendar = new GregorianCalendar(Integer.parseInt(getYear()),
                Integer.parseInt(getMonth()) - 1, Integer.parseInt(getDay()),
                Integer.parseInt(getHour()), Integer.parseInt(getMinute()),
                Integer.parseInt(getSec()));
        calendar.add(Calendar.DATE, dayNum);
        return new SimpleDateFormat(DATE).format(calendar.getTime());
    }

    /**
     * 取得自定义时间后再过几分钟的时间，如12:05以后5分钟的时间
     *
     * @param timeNum 往后几天
     * @return 时间字符串(Date)
     */
    public static String crateTimeFromNowDayByTime(int timeNum) {
        Calendar calendar = new GregorianCalendar(Integer.parseInt(getYear()),
                Integer.parseInt(getMonth()) - 1, Integer.parseInt(getDay()),
                Integer.parseInt(getHour()), Integer.parseInt(getMinute()),
                Integer.parseInt(getSec()));
        calendar.add(Calendar.MINUTE, timeNum);
        return new SimpleDateFormat(DATETIME).format(calendar.getTime());
    }

    /**
     * 计算两个时间间隔(精确到分钟)
     *
     * @param startDay  开始日(整型):0表示当日，1表示明日
     * @param startTime 开始时间(24h):00:00
     * @param endDay    结束日(整型):0表示当日，1表示明日，限制：大于等于 startDay
     * @param endTime   结束时间(24h):23:50
     * @return 格式化的日期格式：DD天HH小时mm分
     */
    public static String calculateIntervalTime(int startDay, String startTime,
                                               int endDay, String endTime) {
        int day = endDay - startDay;
        int hour = 0;
        int mm = 0;
        if (day < 0) {
            return null;
        } else {
            int sh = Integer.valueOf(startTime.split(":")[0]);
            int eh = Integer.valueOf(endTime.split(":")[0]);
            int sm = Integer.valueOf(startTime.split(":")[1]);
            int em = Integer.valueOf(endTime.split(":")[1]);
            hour = eh - sh;
            if (hour > 0) {
                mm = em - sm;
                if (mm < 0) {
                    hour--;
                    mm = 60 + mm;
                }
            } else {
                day = day - 1;
                hour = 24 + hour;
                mm = em - sm;
                if (mm < 0) {
                    hour--;
                    mm = 60 + mm;
                }
            }
        }
        if (hour == 24) {
            day++;
            hour = 0;
        }
        if (day != 0) {
            return day + "天" + hour + "小时" + mm + "分";
        } else {
            return hour + "小时" + mm + "分";
        }
    }

    /**
     * 计算两个时间差
     *
     * @param startTime
     * @param endTime
     * @return long
     * @throws ParseException
     */
    public static long calculateIntervalTime(String startTime, String endTime)
            throws ParseException {
        return parseDateTime(endTime).getTime()
                - parseDateTime(startTime).getTime();
    }

    // 字符串转换成时间
    public static Date parseDateTime(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME);
        return sdf.parse(datetime);
    }

    // 获取当前详细日期时间
    public static String getDateTime() {
        return new SimpleDateFormat(DATETIME).format(new Date());
    }

    // 转换为中文时间
    public static String getChineseDateTime() {
        return new SimpleDateFormat(DATETIMECHINESE).format(new Date());
    }

    // 转换为中文时间
    public static String getChineseDate() {
        return new SimpleDateFormat(DATECHINESE).format(new Date());
    }

    // 转换为中文时间
    public static String getSimpleChineseDate() {
        return new SimpleDateFormat(SIMPLEDATECHINESE).format(new Date());
    }

    // 转换为中文时间 如果num为-1表示前一天 1为后一天 0为当天
    public static String getSimpleChineseDate(int num) {
        Date d = new Date();
        try {
            d = parseDateTime(crateTimeFromNowTimeByDay(num));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(SIMPLEDATECHINESE).format(d);
    }

    // 获取当前时间
    public static String getTime() {
        return new SimpleDateFormat(TIME).format(new Date());
    }

    // 获取当前年
    public static String getYear() {
        return new SimpleDateFormat(YEAR).format(new Date());
    }

    // 获取当前月
    public static String getMonth() {
        return new SimpleDateFormat(MONTH).format(new Date());
    }

    // 获取当前日
    public static String getDay() {
        return new SimpleDateFormat(DAY).format(new Date());
    }

    // 获取当前时
    public static String getHour() {
        return new SimpleDateFormat(HOUR).format(new Date());
    }

    // 获取当前分
    public static String getMinute() {
        return new SimpleDateFormat(MINUTE).format(new Date());
    }

    // 获取当前秒
    public static String getSec() {
        return new SimpleDateFormat(SEC).format(new Date());
    }

    // 获取昨天日期
    public static String getYestday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date d = cal.getTime();
        return new SimpleDateFormat(DATETIME).format(d);// 获取昨天日期
    }

    public static String getMonday() {
        Calendar calendar = new GregorianCalendar();
        // 取得本周一
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return new SimpleDateFormat(DATETIME).format(calendar.getTime());// 获取昨天日期
    }

    public static String getFormatTimeInMMDD(String time) throws ParseException {
        return new SimpleDateFormat(SIMPLEDATECHINESE).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
    }

    public static String getFormatTimeInHHMM(String time) throws ParseException {
        return new SimpleDateFormat(SIMPLETIME).format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time));
    }

    /**
     * 将时间转换成long
     *
     * @param time
     * @return
     */
    public static long getDayTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param strDateBegin 开始时间 00:00:00
     * @param strDateEnd   结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(String strData, String strDateBegin,
                                   String strDateEnd) {
        String strDate = strData;
        // 截取当前时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // 字符串转换成时间
    public static Date parseDateTime_YMDHMS(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_YMDHMS);
        return sdf.parse(datetime);
    }

}
