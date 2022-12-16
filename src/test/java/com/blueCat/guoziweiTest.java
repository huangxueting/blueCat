package com.blueCat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class guoziweiTest {

    @Test
    public void demo1() {
        List<String> strs = new ArrayList<>();
        strs.add("1");
        strs.add("2");
        strs.add("3");
        String s = strs.toString();
        System.out.println("s = " + s);

    }
}
