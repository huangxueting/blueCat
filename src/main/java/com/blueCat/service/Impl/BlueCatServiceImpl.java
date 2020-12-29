package com.blueCat.service.Impl;

import com.alibaba.fastjson.JSON;
import com.blueCat.constant.CacheConst;
import com.blueCat.entity.BlueCatDemo;
import com.blueCat.mapper.BlueCatMapper;
import com.blueCat.service.BlueCatService;
import com.blueCat.utils.RedisUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:02
 * @description:
 **/
@Service
public class BlueCatServiceImpl implements BlueCatService {
    @Autowired
    private BlueCatMapper blueCatMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(readOnly = true)
    public List<BlueCatDemo> outDemo() {
        List<BlueCatDemo> blueCatDemos;
        String outDemo1 = (String) redisUtils.get(CacheConst.BLUE_CAT_DEMO_1);
        if (StringUtils.isBlank(outDemo1)) {
            blueCatDemos = blueCatMapper.outDemo();
            if (CollectionUtils.isNotEmpty(blueCatDemos)) {
                redisUtils.set(CacheConst.BLUE_CAT_DEMO_1, JSON.toJSONString(blueCatDemos));
            }
        } else {
            blueCatDemos = JSON.parseArray(outDemo1, BlueCatDemo.class);
        }
        return blueCatDemos;
    }
}
