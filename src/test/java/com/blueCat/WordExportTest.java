package com.blueCat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.HackLoopTableRenderPolicy;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author: huangxueting
 * @Description:
 * @Date: 2022/3/1 11:16
 */
@SpringBootTest
public class WordExportTest {

    private static final Logger logger1 = LoggerFactory.getLogger(WordExportTest.class);

    @Test
    public void wordExport() throws Exception {
//        InputStream path = WordExportTest.class.getResourceAsStream("/templete/poi-tl.docx");
//        LoopRowTableRenderPolicy enderPolicy  = new LoopRowTableRenderPolicy ();
//        Configure configure = Configure.builder("", enderPolicy).build();
//        XWPFTemplate template = XWPFTemplate.compile().render();
        boolean t = BigDecimal.ZERO.compareTo(new BigDecimal(12)) < 0;
        System.out.println((4010 / 1000));
        System.out.println((4010 / 1000) * 1000);
        String ss = "ESSSUCC:入队成功！";
        System.out.println(ss.contains("SUCCESS"));
//        new BigDecimal("100").compareTo()
        int e = 1100 % 3;
        System.out.println("e = " + e);
        String sss = "123";
//        sss.substring()
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int s = 1/0;
                } catch (Exception e) {
                    logger1.error("hehe ", e);
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int s = 1/0;
                } catch (Exception e) {
                    logger1.error("hahah ", e);
                }
            }
        });
        thread1.start();
        List<String> asf = new ArrayList<>();
        for (String o : asf) {
            System.out.println("o = " + o);
        }
    }
}
