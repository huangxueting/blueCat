package com.blueCat.service;

import com.blueCat.entity.BlueCatDemo;

import java.util.List;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:02
 * @description:
 **/
public interface BlueCatService {
    /**
     * @date: 2020/12/25 13:19
     * @description: 查询数据库测试
     * @param:
     * @return: List<BlueCatDemo>
     */
    List<BlueCatDemo> outDemo();

    /**
     * 保存
     * @param blueCatDemos
     * @return
     */
    Integer save(List<BlueCatDemo> blueCatDemos);
}
