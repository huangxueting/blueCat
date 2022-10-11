package com.blueCat;

import cn.hutool.core.swing.RobotUtil;
import cn.hutool.core.swing.clipboard.ClipboardUtil;
import cn.hutool.core.thread.ThreadUtil;
import org.apache.commons.lang3.StringUtils;

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: huangxueting
 * @Description:
 * @Date: 2022/7/14 10:46
 */

public class RobotTest {

    public static void main(String[] args) {
//        BigDecimal zero = new BigDecimal(BigInteger.ZERO);
//        zero=zero.add(new BigDecimal("20"));
//        System.out.println("zero = " + zero);

        for (int i = 1,  j = 1; j < 10; i++) {
            System.out.print(i +"*"+j+"="+(i*j)+"\t");

            if (i == j) {
                i=0;
                j++;
                System.out.println();
            }
        }




    }

//    public static void main(String[] args) {
//        doOk(1096, 823);
//    }

    public static void doOk(int x, int y) {
        for (int i = 0; i < 50; i++) {
            RobotUtil.mouseMove(x, y);
            RobotUtil.click();
            RobotUtil.keyPressWithCtrl(KeyEvent.VK_V);
            ThreadUtil.sleep(1);
            RobotUtil.keyClick(KeyEvent.VK_ENTER);
            ThreadUtil.sleep(61);
//            System.out.println(ClipboardUtil.getStr());
//            RobotUtil.mouseMove(1248, 692);抽奖

//            RobotUtil.rightClick();
//            RobotUtil.keyClick(KeyEvent.VK_DOWN);
//            RobotUtil.setDelay(30);
//            RobotUtil.keyClick(KeyEvent.VK_ENTER);
//            String str= ClipboardUtil.getStr();
//            if (StringUtils.isNotBlank(str) &&  str.contains("你今天已经中过奖")) {
//                System.out.println(str);
//                break;
//            }
        }
    }
}
