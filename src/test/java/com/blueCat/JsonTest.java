package com.blueCat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blueCat.entity.Grade;
import com.blueCat.entity.Student;
import com.blueCat.entity.SynchDateHandleVo;
import com.blueCat.utils.DateUtils;
import com.blueCat.utils.PicUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootTest
public class JsonTest {

    @Test
    public void demo122331431122222() {
        String str = "/courseware/117/深圳市质量安全基础训练入门级培训课程.docx";
        String path = str.replaceAll("\\\\", "/");
        // 兼用迁移obs前老数据
        if (path.startsWith("/files")) {
            path = path.replaceFirst("/files", "");
        }
        String f = Paths.get("", path).normalize().toString();
        f.replaceAll("\\\\", "/");
        System.out.println("f = " + f);
    }

    @Test
    public void demo1223314311222() throws ParseException {
        String synchDate = "2024-07-01";

        //日期参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
        Date parse = sdf.parse(synchDate);
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        Calendar ca = Calendar.getInstance();
        ca.setTime(parse);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        SynchDateHandleVo synchDateHandleVo = this.querySynchDate(sdf, c, ca, calendar);
        System.out.println("synchDateHandleVo = " + synchDateHandleVo);
    }

    /**
     * 查询日期处理
     * @param sdf
     * @param c
     * @param ca
     * @param calendar
     * @return
     */
    private SynchDateHandleVo querySynchDate(SimpleDateFormat sdf, Calendar c, Calendar ca, Calendar calendar) {
        SynchDateHandleVo synchDateHandleVo = new SynchDateHandleVo();
        //获取当前月第一天：
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = sdf.format(c.getTime());
        synchDateHandleVo.setFirstDate(first);
        //获取当前月最后一天
        ca.add(Calendar.MONTH, -1);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = sdf.format(ca.getTime());
        synchDateHandleVo.setLastDate(last);
        //获取当前年月
        calendar.add(Calendar.MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        synchDateHandleVo.setYear(year);
        int month = calendar.get(Calendar.MONTH) + 1;
        synchDateHandleVo.setMonth(month);
        calendar.add(Calendar.MONTH, -1);
        return synchDateHandleVo;
    }


    @Test
    public void demo12233143112() throws ParseException {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 减去一天得到前一天的日期
        LocalDate yesterday = today.minusDays(1);
        // 格式化日期（例如格式为"yyyy-MM-dd"）
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedYesterday = yesterday.format(formatter);
        System.out.println("formattedYesterday = " + formattedYesterday);
    }

    @Test
    public void demo1223314311() throws ParseException {
        String ancestors = "0";
        List<String> ancestorsList = Arrays.asList(ancestors.split(","));
        System.out.println("ancestorsList = " + ancestorsList);
    }

    @Test
    public void demo1223311() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
        Date parse = sdf.parse("2023-11-12");
        Calendar c = Calendar.getInstance();
        c.setTime(parse);
        Calendar ca = Calendar.getInstance();
        ca.setTime(parse);
//        for (int i = 1; i <= 3; i++) {
            //获取当前月第一天：
            c.add(Calendar.MONTH, -1);
            c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String first = format.format(c.getTime());
            System.out.println("===============first:" + first);
//            c.add(Calendar.MONTH, -1);




            //获取当前月最后一天
            ca.add(Calendar.MONTH, -1);
            ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
            String last = format.format(ca.getTime());
            System.out.println("===============last:" + last);
//            ca.add(Calendar.MONTH, -1);
//        }

    }
    @Test
    public void demo12233() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 设置日期格式
        Date parse = sdf.parse("2024-01-12");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parse);
        int year3 = calendar.get(Calendar.YEAR);
        int month3 = calendar.get(Calendar.MONTH) + 1;
        calendar.add(Calendar.MONTH, -1);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        calendar.add(Calendar.MONTH, -1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH) + 1;

        String startTime = sdf.format(calendar.getTime());

        System.out.println("year3 = " + year3);
        System.out.println("month3 = " + month3);
        System.out.println("year2 = " + year2);
        System.out.println("month2 = " + month2);
        System.out.println("year1 = " + year1);
        System.out.println("month1 = " + month1);
        System.out.println("startTime = " + startTime);

    }


    @Test
    public void demo1223() throws Exception {
        String imgURL = "https://edu.ypt.szsti.org/files/78/image/323a179321a848808443140b0eea55cd.png";
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        InputStream in = null;
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            in = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = in.read(by)) != -1) {
                data.write(by, 0, len);
            }
        } catch (IOException e) {


        } finally {
            if (in != null) {
                in.close();
            }
        }

        byte[] bytes = PicUtils.compressPicForScale(data.toByteArray(), 600);
        BASE64Encoder encoder = new BASE64Encoder();
        String s = "data:image/png;base64," + replaceRN(encoder.encode(bytes));
        System.out.println("s = " + s);
    }

    /**
     * 去除\r\n，解决base64加密出现的\r\n换行问题
     *
     * @param str
     * @return
     */
    private static String replaceRN(String str) {
        if (str == null || str.equals("")) {
            return str;
        }
        str = str.replace("\r", "");
        str = str.replace("\n", "");
        return str;
    }

    @Test
    public void demo122() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, -1);
        Date y = c.getTime();
        System.out.println("y = " + format.format(y));

    }
    @Test
    public void demo1() {
        Grade group = new Grade();
        group.setId(0L);
        group.setName("admin");

        Student student = new Student();
        student.setId(2L);
        student.setName("guest");

        Student rootUser = new Student();
        rootUser.setId(3L);
        rootUser.setName("root");

        group.addStudent(student);
        group.addStudent(rootUser);

        group.setUser(student);

        // 转换为 JSON
        String jsonString = JSON.toJSONString(group);
        System.out.println("JSON字符串：" + jsonString);

        // 转换为 对象BEAN
        Grade grade = JSON.parseObject(jsonString, Grade.class);
        System.out.println("JavaBean对象：" + grade);

        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println("JSON对象：" + jsonObject);

        JSONObject user = jsonObject.getJSONObject("user");
        String name = user.getString("name");
        System.out.println("name = " + name);
    }


    @Test
    public void randomUUID() {
        for (int i = 1; i <= 64 ; i++) {
            String str = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            System.out.println("str = " + str);
        }
    }
}
