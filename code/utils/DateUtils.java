package com.abc.code.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String FORMAT_COMMON = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_COMMON_TIME = "HH:mm:ss";
    public static final String FORMAT_NO_SECOND_TIME = "HH:mm";
    public static final String FORMAT_COMMON_DAY = "yyyy-MM-dd";
    public static final String FORMAT_COMMON_DAY_TIME = "yyyy-MM-dd_HH";
    public static final String FORMAT_8_DAY = "yyyyMMdd";
    public static final String FORMAT_MMDDHHMM = "MM-dd HH:mm";
    public static final String FORMAT_CHINESE = "yyyy/MM/dd";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_MMDD = "MM-dd";
    public static final String FORMAT_YYYYMM = "YYYY-MM";
    public static final String FORMAT_MM = "MM";

    /**
     * 获取当前时间
     *
     * @return 返回当前时间，时间格式为yyyy-MM-dd_HH:mm
     */
    public static String getDayTimes() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_DAY_TIME, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String formatDate(Long inDate, String pattern) {
        return formatDate(new Date(inDate), pattern);
    }


    public static String formatDate(Long inDate) {
        return formatDate(new Date(inDate), FORMAT_COMMON);
    }

    public static String formatDate(Date inDate, String pattern) {
        String ret = null;
        if (inDate != null && pattern != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            ret = sdf.format(inDate);
        }
        return ret;
    }

    /**
     * 字符串转换为时间
     *
     * @param dateString 字符串
     * @param pattern    时间格式
     * @return
     */
    public static Date stringParseDate(String dateString, String pattern) {
        Date ret = null;
        try {
            if (dateString != null && pattern != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                ret = sdf.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * 时间转换为字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String DateParseString(Date date, String pattern) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            String dateString = formatter.format(date);
            return dateString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 系统时间格式设置
     *
     * @param year
     * @param month
     * @param day
     * @param hourOfDay
     * @param minute
     * @param secend
     * @return
     */
    public static long setTime(String year, String month, String day, String hourOfDay, String minute, String secend) {
        try {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, Integer.parseInt(year));
            c.set(Calendar.MONTH, Integer.parseInt(month) - 1);
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hourOfDay));
            c.set(Calendar.MINUTE, Integer.parseInt(minute));
            c.set(Calendar.SECOND, Integer.parseInt(secend));
            c.set(Calendar.MILLISECOND, 0);
            long when = c.getTimeInMillis();

            if (when / 1000 < Integer.MAX_VALUE) {
                // SystemClock.setCurrentTimeMillis(when);
                return when;
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 系统时间格式设置
     *
     * @param pattern 时间格式
     * @param day     减去的天数
     * @return 减去后的时间
     */
    public static String getFormatedAddDateTime(String pattern, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) - day);
        return sdf.format(date.getTime());
    }

    /**
     * 将当前时间转化成String型(yyyyMMdd)
     *
     * @return
     */
    public static String getToday8() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_8_DAY, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型（yyyy-MM-dd）
     *
     * @return
     */
    public static String getToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_DAY, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型(HH:mm)
     *
     * @return
     */
    public static String getTodayTiemNoSecond() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_NO_SECOND_TIME, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型(yyyy-MM-dd HH:mm:ss)
     *
     * @return
     */
    public static String getTodayCommon() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String getTodayTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_CHINESE, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getYearTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YEAR, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    public static String getTodayTimeUpdate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_DAY, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     *  "yyyy-MM-dd";
     * @param date
     * @return
     */
    public static String formatTimeUpdate(String date) {
        String dateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_COMMON);
            Date date1 = sdf.parse(date);
            SimpleDateFormat sdf1 = new SimpleDateFormat(FORMAT_COMMON_DAY);
            dateString = sdf1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }


    /**
     * 将当前时间转化成String型(yyyyMMddHHmmss)
     *
     * @return
     */
    public static String getTodayCommon2() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYYMMDDHHMMSS, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型(yyyy-MM-dd HH:mm)
     *
     * @return
     */
    public static String getTodayCommon3() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_YYYYMMDDHHMM, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将当前时间转化成String型(MM-dd HH:mm)
     *
     * @return
     */
    public static String getTodayOrderShow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_MMDDHHMM, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String getTodayTiemYesSecond() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_TIME, Locale.CHINA);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static long getNowLong() {
        return System.currentTimeMillis();
    }

    /**
     * YYYY-MM-dd格式日期转化为对应毫秒值
     *
     * @param strData YYYY-MM-dd格式日期（2012-05-05）
     * @return
     */
    public static long getTimeMillons(String strData) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(strData);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss转毫秒
     */
    public static long dateToMillions(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_COMMON);
            long millionSeconds = sdf.parse(date).getTime();//毫秒
            return millionSeconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String dateFormat(String date) {
        String dateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDDHHMM);
            Date date1 = sdf.parse(date);
            SimpleDateFormat sdf1 = new SimpleDateFormat(FORMAT_MMDD);
            dateString = sdf1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }


    public static String dateFormat1(String date) {
        String dateString = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYYMMDDHHMM);
            Date date1 = sdf.parse(date);
            SimpleDateFormat sdf1 = new SimpleDateFormat(FORMAT_CHINESE);
            dateString = sdf1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }

    public static String dateFormat2(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_CHINESE);
        String dateString = sdf.format(date);
        return dateString;
    }


    /**
     * 时间戳转换成日期格式字符串
     *
     * @param time 精确到秒的字符串
     * @return
     */
    public static String timeStamp2DateTwp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        @SuppressWarnings("unused") Long i = Long.valueOf(time) / 1000;
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }


    public static Date parseString(String dateString, String pattern) {
        Date ret = null;
        try {
            if (dateString != null && pattern != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                ret = sdf.parse(dateString);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String dateSpan(int span) {
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        calendar1.add(Calendar.DATE, span);
        return sdf1.format(calendar1.getTime());
    }

    public static String dateSpan2(int span) {
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        calendar1.add(Calendar.DATE, span);
        return sdf1.format(calendar1.getTime());
    }

    /**
     * 获取是周几
     *
     * @param pTime
     * @return
     * @throws Throwable
     */
    public static int dayForWeek(Date pTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(pTime);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return w;
    }


    /**
     * 获取当前月份
     *
     * @param pTime
     * @return
     */
    public static int monthOfYear(Date pTime) {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取月
     *
     * @return
     */
    public static String getMonth() {
        Calendar cd = Calendar.getInstance();
        return (cd.get(Calendar.MONTH) + 1) + "";
    }


    /**
     * 将当前时间转化成String型（yyyy-MM-dd）
     *
     * @return
     */
    public static String getDateToToday10(Date pTime) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_COMMON_DAY, Locale.CHINA);
        String dateString = formatter.format(pTime);
        return dateString;
    }


    /**
     * 功能：得到当前月份月初 格式为：xxxx-yy-zz (eg: 2007-12-01)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisMonth(Calendar c) {
        Calendar localTime = c;//Calendar.getInstance();
        String strY = null;
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01";
    }


    /**
     * 功能：得到当前月份月底 格式为：xxxx-yy-zz (eg: 2007-12-31)<br>
     *
     * @return String
     * @author pure
     */
    public static String thisMonthEnd(Calendar c) {
        Calendar localTime = c;// Calendar.getInstance();
        String strY = null;
        String strZ = null;
        boolean leap = false;
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            } else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }


    /**
     * 功能：判断输入年份是否为闰年<br>
     *
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            } else leap = true;
        } else leap = false;
        return leap;
    }

}
