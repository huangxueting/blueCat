package com.blueCat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.blueCat.entity.Grade;
import com.blueCat.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class JsonTest {

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
