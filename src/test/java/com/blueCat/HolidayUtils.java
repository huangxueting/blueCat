package com.blueCat;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 判断工作日/周末/节假日
 */
public class HolidayUtils {

    /**
     * @param httpArg :参数
     * @return 返回结果
     */
    public static String request(String httpArg) {
        String httpUrl = "http://tool.bitefu.net/jiari/";
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?d=" + httpArg + "&back=json";

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }


//    public static void main(String[] args) {
//        //判断今天是否是工作日 周末 还是节假日
//        //0 上班 1周末 2节假日
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String httpArg = f.format(new Date());
//        System.out.println(httpArg);
//        String jsonResult = request(httpArg);
//        System.out.println(jsonResult);
//    }

    public static void main(String[] args) throws Exception {
        //判断一个时间段的情况
        String start = "2022-12-01";
        String end = "2023-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date dBegin = sdf.parse(start);
        Date dEnd = sdf.parse(end);
        List<Date> lDate = findDates(dBegin, dEnd);
        StringBuffer dataStr = new StringBuffer();
        for (Date date : lDate) {
            //System.out.println(sdf.format(date));
            dataStr.append(dateFormat.format(date)).append(",");
        }
        String s = dataStr.substring(0,dataStr.length()-1).toString();
        String jsonResult = request(s);
//        System.out.println(jsonResult);
        Map<String, Integer> jsonToMap = JSON.parseObject(jsonResult, Map.class);
        Map<String, Integer> filterData = jsonToMap.entrySet().stream().filter(e -> e.getValue().intValue() == 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> mapToList = new ArrayList(filterData.keySet());
        List<String> resultList = mapToList.stream().sorted().collect(Collectors.toList());
        System.out.println("resultList = " + resultList);
        System.out.println(resultList.get(10));

    }

}
