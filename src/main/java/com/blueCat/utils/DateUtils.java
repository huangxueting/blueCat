package com.blueCat.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author liaoqh
 * @date 2020年4月13日
 */
public class DateUtils  {

    public static final String YYYY_MM_DD_235959 = "yyyy-MM-dd 23:59:59";

    public static final String YYYY_MM_DD_000000 = "yyyy-MM-dd 00:00:00";

    public static final String YYYY_MM_01 = "yyyy-MM-01";

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String MM = "MM";

    public static final String DD = "dd";

    /**
     * 返回给定日期中的月份中的最后一天
     *
     * @param date
     *            基准日期
     * @return 该月最后一天的日期
     */
    public static Date getMonthLastDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 将日期设置为下一月第一天
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);

        // 减去1天，得到的即本月的最后一天
        calendar.add(Calendar.DATE, -1);

        return calendar.getTime();
    }

    /**
     * 返回给定日期中的月份中的第一天
     *
     * @param date
     *            基准日期
     * @return 该月第一天的日期
     */
    public static Date getMonthFirstDay(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return calendar.getTime();
    }

    /**
     * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数
     *
     * @param one
     *            第一个日期数，作为基准
     * @param two
     *            第二个日期数，作为比较
     * @return 两个日期相差月份数
     */
    public static int differMonths(Date one, Date two) {

        Calendar calendar = Calendar.getInstance();

        // 得到第一个日期的年分和月份数
        calendar.setTime(one);
        int yearOne = calendar.get(Calendar.YEAR);
        int monthOne = calendar.get(Calendar.MONDAY);
        // 得到第二个日期的年份和月份
        calendar.setTime(two);
        int yearTwo = calendar.get(Calendar.YEAR);
        int monthTwo = calendar.get(Calendar.MONDAY);

        return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
    }

    /**
     * 计算两个时间相差天数
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static int differDays(Date beginTime, Date endTime) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(beginTime);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(endTime);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {// 不同年
            return day2 - day1;
        }
    }

    /**
     * 计算两个时间相差小时
     * @param startDate
     * @param endDate
     * @return
     */
    public static int differHour(Date startDate, Date endDate) {
        long dayM = 1000 * 24 * 60 * 60;
        long hourM = 1000 * 60 * 60;
        long differ = endDate.getTime() - startDate.getTime();
        long hour = differ % dayM / hourM + 24 * (differ / dayM);
        return Integer.parseInt(String.valueOf(hour));
    }

    /**
     * 计算两个时间相差分钟数
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long differMin(Date beginTime, Date endTime) {
        long between = (endTime.getTime() - beginTime.getTime()) / 1000;
        long min = between / 60;
        return min;
    }

    /**
     * 获取两个日期的时间范围
     * @param startDt
     * @param endDt
     * @return
     */
    public static List<Date> getDayRange(Date startDt, Date endDt) {
        List<Date> dateRangeList = new ArrayList<>();

        long diffDays = DateUtils.differDays(startDt, endDt) + 1;
        for(int i = 0;i<diffDays; i++) {
            Date beginDt = DateUtils.addDays(startDt, i);
            dateRangeList.add(beginDt);
        }
        return dateRangeList;
    }


    /**
     * 为指定日期增加相应的天数或月数
     *
     * @param date
     *            基准日期
     * @param amount
     *            增加的数量
     * @param field
     * @return 增加以后的日期
     */
    public static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(field, amount);

        return calendar.getTime();
    }

    /**
     * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
     *
     * @param date
     *            基准日期
     * @param days
     *            增加的日期数
     * @return 增加以后的日期
     */
    public static Date addDays(Date date, int days) {
        return add(date, days, Calendar.DATE);
    }

    /**
     * 判断时间是否晚于另一个时间
     * 方法用途: <br>
     * 实现步骤: <br>
     * @param thisTime
     * @param anotherTime
     * @param format
     * @return
     */
    public static boolean isTimeBehindTheOther(Date thisTime, Date anotherTime, String format) {
        return isTimeBehindTheOther(format(thisTime, format), format(anotherTime, format));
    }


    /**
     * 判断时间是否晚于另一个时间
     * @param thisTime
     * @param anotherTime
     * @return
     */
    public static boolean isTimeBehindTheOther(String thisTime, String anotherTime) {
        return thisTime.compareTo(anotherTime) > 0;
    }

    /**
     * 格式化日期对象
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式:yyyy-MM-dd
     *
     * @return
     */
    public static String formatDateYyyyMMdd(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.YYYY_MM_DD);
        return dateFormat.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param str
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(str);
    }

    //获取一个月天数
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static Date getLastDay(Date data) {
        // 获取Calendar类的实例
        Calendar c = Calendar.getInstance();
        c.setTime(data);

        // 获取当前时间下，该月的最大日期的数字
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 将获取的最大日期数设置为Calendar实例的日期数
        c.set(Calendar.DAY_OF_MONTH, lastDay);

        return c.getTime();
    }
}
