package com.blueCat.controller;

import ch.qos.logback.classic.Logger;
import com.alibaba.excel.EasyExcel;
import com.blueCat.entity.BlueCatDemo;
import com.blueCat.entity.BlueCatExcelVo;
import com.blueCat.service.BlueCatService;
import com.sun.deploy.net.URLEncoder;
import listener.BlueCatExcelVoListener;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:01
 * @description:
 **/
@RestController(value = "/blueCat")
public class BlueCatController {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(BlueCatController.class);

    @Autowired
    private BlueCatService blueCatService;

    @PostMapping(value = "/saveTest")
    public void saveTest() {
        blueCatService.saveTest();
    }

    /**
     * @date: 2020/12/25 13:18
     * @description: 查询数据库测试
     * @param:
     * @return: List<BlueCatDemo>
     */
    @GetMapping(value = "/outDemo")
    public List<BlueCatDemo> outDemo() {
        return blueCatService.outDemo();
    }

    /**
     * 导出
     *
     * @param response
     */
    @GetMapping(value = "/exportDemo")
    public void exportDemo(HttpServletResponse response) throws IOException {
        List<BlueCatDemo> blueCatDemos = blueCatService.outDemo();

        List<BlueCatExcelVo> exportList = new ArrayList<>();
        for (BlueCatDemo blueCatDemo : blueCatDemos) {
            BlueCatExcelVo export = new BlueCatExcelVo();
            export.setTitle(blueCatDemo.getTitle());
            export.setCreateDate(blueCatDemo.getCreateDate());
            export.setDelFlag(blueCatDemo.getDelFlag());
            exportList.add(export);
        }

//        try{
        /**
         * web中的写
         */
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("blueCat", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), BlueCatExcelVo.class).sheet("sheet").doWrite(exportList);


        /**
         * web中的写并且失败的时候返回json
         */
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setCharacterEncoding("utf-8");
//            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
//            String fileName = URLEncoder.encode("blueCat", "UTF-8").replaceAll("\\+", "%20");
//            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
//            // 这里需要设置不关闭流
//            EasyExcel.write(response.getOutputStream(), BlueCatExcelVo.class).autoCloseStream(Boolean.FALSE).sheet("sheet").doWrite(exportList);
//        } catch (Exception e) {
//            // 重置response
//            response.reset();
//            response.setContentType("application/json");
//            response.setCharacterEncoding("utf-8");
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("status", "failure");
//            map.put("message", "下载文件失败" + e.getMessage());
//            response.getWriter().println(JSON.toJSONString(map));
//        }
    }

    /**
     * 导入
     * @param file
     */
    @PostMapping(value = "/inportDemo")
    public String inportDemo(@RequestParam("file") MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), BlueCatDemo.class, new BlueCatExcelVoListener(blueCatService)).sheet().doRead();
        return "success";
    }

    @GetMapping(value = "/zip")
    public void zip(HttpServletResponse response) throws IOException {
        ZipOutputStream zipout = null;
        InputStream inputStream = null;
        try {
            List<BlueCatDemo> blueCatDemos = blueCatService.outDemo();

            List<BlueCatExcelVo> exportList = new ArrayList<>();
            for (BlueCatDemo blueCatDemo : blueCatDemos) {
                BlueCatExcelVo export = new BlueCatExcelVo();
                export.setTitle(blueCatDemo.getTitle());
                export.setCreateDate(blueCatDemo.getCreateDate());
                export.setDelFlag(blueCatDemo.getDelFlag());
                exportList.add(export);
            }
            List<BlueCatExcelVo> newList = new ArrayList<>();
            for (BlueCatDemo blueCatDemo : blueCatDemos) {
                BlueCatExcelVo export = new BlueCatExcelVo();
                export.setTitle(blueCatDemo.getTitle());
                export.setCreateDate(blueCatDemo.getCreateDate());
                export.setDelFlag(blueCatDemo.getDelFlag());
                newList.add(export);
            }
            newList.remove(0);

            response.setContentType("application/force-download");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(("zipDemo" + ".zip").getBytes("UTF-8"), "ISO8859-1"));
            zipout = new ZipOutputStream(response.getOutputStream());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            EasyExcel.write(out, BlueCatExcelVo.class).sheet("sheet").doWrite(exportList);

            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            EasyExcel.write(out2, BlueCatExcelVo.class).sheet("sheet").doWrite(newList);

            zipOutput(out, "excelDemo1", zipout, inputStream);
            zipOutput(out2, "excelDemo2", zipout, inputStream);


        } catch(IOException e){
                LOGGER.error("导出时写入数据到文件出错" + e.getMessage(), e);
        } finally {
            if (zipout != null) {
                zipout.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private void zipOutput (ByteArrayOutputStream out, String fileName, ZipOutputStream zipout, InputStream inputStream) throws IOException {
        try {
            inputStream = new ByteArrayInputStream(out.toByteArray());

            //excel文件写入zip
            zipout.putNextEntry(new ZipEntry(fileName + ".xlsx"));
            int len;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) > 0) {
                zipout.write(buf, 0, len);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            LOGGER.error("zipFiles exception:{}", e.getMessage());
        }
    }
}
