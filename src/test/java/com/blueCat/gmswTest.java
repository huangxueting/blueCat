package com.blueCat;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.blueCat.entity.CompanyHasImgModel;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@SpringBootTest
public class gmswTest {

    @Test
    public void demo2() {
        String orgCode = "58";
        int trainLevelCode = 1;
        long orderIndex = 8097L;

        StringBuilder sb = new StringBuilder();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        if (orgCode == null || orgCode.length() == 0) {
            sb.append("00");
        } else if (orgCode.length() == 1) {
            sb.append("0");
        }
        sb.append(orgCode);
        String trainLevelCodeStr = String.format("%02d", trainLevelCode);
        String orderIndexStr = String.format("%07d", orderIndex);
        sb.append(year).append(trainLevelCodeStr).append(orderIndexStr);
        System.out.println("CertificateNumber：" + sb.toString());
    }

    @Test
    public void demo1() {
//        String str = null;
//        List<String> strings = Arrays.asList(str.split(","));
//        for (String string : strings) {
//            System.out.println("string = " + string);
//        }
//        Integer is = 0;
//        Integer i = this.getCurrSignRound(is) == null ? 1 : this.getCurrSignRound(is);// 签到轮次
//        System.out.println("i = " + i);

        String s = null;
        String s1 = "  ";
        String s2 = "";
//        s.trim();
        String trim = s1.trim();
        String trim1 = s2.trim();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Date modifyTimeStart = Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date modifyTimeStart1 = Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public Integer getCurrSignRound(Integer is) {
        return is == null ? is : 1;
    }

    @Test
    public void exportCompanyImg() throws Exception {
        List<CompanyHasImgModel> list = new ArrayList<CompanyHasImgModel>();
        list.add(new CompanyHasImgModel("百度", "C:\\Users\\qq976\\Pictures\\Snipaste_2022-12-05_16-58-50.png"));
        list.add(new CompanyHasImgModel("阿里巴巴", "C:\\Users\\qq976\\Pictures\\Snipaste_2022-12-05_16-58-50.png"));
        list.add(new CompanyHasImgModel("Lemur", "C:\\Users\\qq976\\Pictures\\Snipaste_2022-12-05_16-58-50.png"));
        list.add(new CompanyHasImgModel("一众", "C:\\Users\\qq976\\Pictures\\Snipaste_2022-12-05_16-58-50.png"));


        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(), CompanyHasImgModel.class, list);
        FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportHasImgTest.exportCompanyImg.xls");
        workbook.write(fos);
        fos.close();
    }
}
