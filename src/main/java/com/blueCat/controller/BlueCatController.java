package com.blueCat.controller;

import com.blueCat.entity.BlueCatDemo;
import com.blueCat.service.BlueCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:01
 * @description:
 **/
@RestController(value = "/blueCat")
public class BlueCatController {
    @Autowired
    private BlueCatService blueCatService;

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
}
