package com.blueCat;

import com.alibaba.fastjson.JSONArray;
import com.blueCat.utils.DateUtilss;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


@SpringBootTest
public class DemoTest20240329 {

    public List<LocalDate> enumerateDates(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate current = startDate;

        while (!current.isAfter(endDate)) {
            dates.add(current);
            current = current.plusDays(1);
        }

        return dates;
    }

    @Test
    public void demo20240904() {
        String startDate = "2024-08-04";

        String startDateFirstDay = null; // 开始日期的第一天
        int monthOfDays = 0;
        Date beginDate = null;
        try {
            beginDate = DateUtilss.strToDate(startDate, DateUtilss.YYYY_MM_DD);
            monthOfDays = DateUtilss.getDaysOfMonth(beginDate); // 获取月份的总天数
            startDateFirstDay = DateUtilss.formatDate(beginDate, "yyyy-MM-01");
        } catch (ParseException e) {

            return;
        }

        List<String> dateStrList = new ArrayList<>();
        for (int i = 1; i <= monthOfDays; i++) {
            String day = null;
            if (i < 10) {
                day = DateUtilss.formatDate(beginDate, "yyyy-MM-0" + i);
            } else {
                day = DateUtilss.formatDate(beginDate, "yyyy-MM-" + i);
            }
            dateStrList.add(day);
            System.out.println("day = " + day);
        }

        Date date = DateUtils.addMonths(new Date(), -(1));
        System.out.println("date = " + DateUtilss.formatDate(date, "yyyy-MM-dd"));
    }

    @Test
    public void demo20240523() {
        UUID uuid = UUID.randomUUID();
        System.out.println("uuid = " + uuid);
        List<String> d = null;
//        d.add("1");
//        d.add("2");
//        d.add("3");

        String s = JSONArray.toJSONString(d);
        System.out.println("s = " + s);

       List<String> engIdList = JSONArray.parseArray(s, String.class);
//        for (String s1 : engIdList) {
//            System.out.println("s1 = " + s1);
//
//        }

//        Integer in = null;
//        in.intValue();
//        int c = 3/0;
//        int cc = 0/0;
        int ccc = 0/3;
    }


    @Test
    public void demo20240515() {
        Date startDate = DateUtils.addMonths(new Date(), -(1));
        Date endDate = DateUtils.addMonths(new Date(), -(1));
        System.out.println("startDate = " + new SimpleDateFormat("yyyy-MM-dd").format(startDate));
        System.out.println("endDate = " + new SimpleDateFormat("yyyy-MM-dd").format(endDate));

        String startDateStr = new SimpleDateFormat("yyyy-MM-dd").format(DateUtils.addDays(new Date(), - (50-1)));
        System.out.println("startDateStr = " + startDateStr);
    }

    @Test
    public void demo20240425() {

        LocalDate startDate1 = LocalDate.of(2024, 3, 29);
        LocalDate endDate1 = LocalDate.of(2024, 4, 15);

        List<LocalDate> datesInRange = enumerateDates(startDate1, endDate1);

        for (LocalDate date : datesInRange) {
            System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }


        // 定义指定时间范围的起始日期和结束日期
        LocalDate startDate = LocalDate.of(2024, 3, 29);
        LocalDate endDate = LocalDate.of(2024, 4, 15);

        // 计算两个日期之间的天数
        Period period = startDate.until(endDate);
        int numberOfDays = period.getDays();

        // 输出结果
        System.out.println("Number of days between " + startDate + " and " + endDate + ": " + numberOfDays);

    }

    @Test
    public void demo202404081() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 0);
        Date y = c.getTime();
        String r = format.format(y);
        System.out.println("r = " + r);
    }

    @Test
    public void demo20240408() {
        //分时段查询预警内容
        Date date = new Date();
//        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse();

        // 获取当前时间的Calendar对象
        Calendar now = Calendar.getInstance();

        // 假设要比较的另一个时分，如晚上8点30分
        int targetHour = 20;
        int targetMinute = 30;

        // 设置目标时间到Calendar对象
        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR_OF_DAY, targetHour);
        targetTime.set(Calendar.MINUTE, targetMinute);
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.MILLISECOND, 0);

        // 方法一：直接比较
        if (now.before(targetTime)) {
            System.out.println("当前时间在 " + targetHour + ":" + targetMinute + " 之前");
        } else if (now.after(targetTime)) {
            System.out.println("当前时间在 " + targetHour + ":" + targetMinute + " 之后");
        } else {
            System.out.println("当前时间等于 " + targetHour + ":" + targetMinute);
        }
    }

    @Test
    public void demo20240329() {
        List<String> dates = getDatesOfCurrentMonth();
        for (String date : dates) {
            System.out.println(date);
        }
    }

    public static List<String> getDatesOfCurrentMonth() {
        // 获取当前日期
        LocalDate now = LocalDate.now();

        // 获取当前月份的第一天
        LocalDate firstDayOfMonth = now.withDayOfMonth(1);

        // 获取当前月份的最后一天
        LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());

        // 使用Stream API生成日期列表
        return LongStream.rangeClosed(0, lastDayOfMonth.getDayOfMonth() - 1)
                .mapToObj(firstDayOfMonth::plusDays)
                .map(date -> date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) // 格式化日期字符串
                .collect(Collectors.toList());
    }

}
