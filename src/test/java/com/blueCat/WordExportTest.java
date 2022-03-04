package com.blueCat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.plugin.table.HackLoopTableRenderPolicy;
import com.deepoove.poi.plugin.table.LoopRowTableRenderPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @Author: huangxueting
 * @Description:
 * @Date: 2022/3/1 11:16
 */
@SpringBootTest
public class WordExportTest {
    @Test
    public void wordExport() throws Exception {
        InputStream path = WordExportTest.class.getResourceAsStream("/templete/poi-tl.docx");
        LoopRowTableRenderPolicy enderPolicy  = new LoopRowTableRenderPolicy ();
        Configure configure = Configure.builder("", enderPolicy).build();
        XWPFTemplate template = XWPFTemplate.compile().render();
    }
}
