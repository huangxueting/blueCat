package com.blueCat.service.Impl;

import com.blueCat.entity.BlueCatDemo;
import com.blueCat.mapper.dataSource2.BlueCatMapper2;
import com.blueCat.service.BlueCatServiceA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: huangxueting
 * date: 2020/12/25 13:02
 * @description:
 **/
@Service
public class BlueCatServiceAImpl implements BlueCatServiceA {
    @Autowired
    private BlueCatMapper2 blueCatMapper2;

//    @Autowired
//    private RedisUtils redisUtils;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void outDemo() {
        List<BlueCatDemo> blueCatDemos;
//        String outDemo1 = (String) redisUtils.get(CacheConst.BLUE_CAT_DEMO_1);
//        if (StringUtils.isBlank(outDemo1)) {
        BlueCatDemo blueCatDemo = new BlueCatDemo();
        blueCatDemo.setTitle("事务二");
        blueCatDemo.setDelFlag(2);
        blueCatDemo.setCreateDate(new Date());
        blueCatDemos = new ArrayList<>();
        blueCatDemos.add(blueCatDemo);
        int x = blueCatMapper2.save(blueCatDemos);

//            if (CollectionUtils.isNotEmpty(blueCatDemos)) {
//                redisUtils.set(CacheConst.BLUE_CAT_DEMO_1, JSON.toJSONString(blueCatDemos));
//            }
//        } else {
//            blueCatDemos = JSON.parseArray(outDemo1, BlueCatDemo.class);
//        }
        int ss = 10/0;
    }
}
