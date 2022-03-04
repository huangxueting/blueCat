package com.blueCat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangxueting
 * @Description:
 * @Date: 2022/2/14 8:41
 */
@SpringBootTest
public class LeetcodeTest {

    @Test
    public void _35() {
//        给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
//        请必须使用时间复杂度为 O(log n) 的算法。
        int num[] = {1, 4, 5, 9};
        int input = 4;

        int l = 0;
        int r = num.length - 1;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (input == num[mid]) {
                System.out.println(mid);
                return;
            } else if (input > num[l]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(l);
    }

    @Test
    public void _01() {
//        给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//        你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//        你可以按任意顺序返回答案。
        int num[] = {1, 3, 5, 9};
        int input = 6;

        for (int i = 0; i < num.length; i++){
            for (int j = i+1; j < num.length; j++) {
                if (input == num[i]+num[j]) {
                    System.out.println(num[i] + "," + num[j]);
                }
            }
        }
    }






}
