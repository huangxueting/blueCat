package com.blueCat.mapper;

import com.blueCat.entity.BlueCatDemo;

import java.util.List;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:03
 * @description:
 **/
public interface BlueCatMapper {
    /**
     * @date: 2020/12/25 13:25
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
