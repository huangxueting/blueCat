package com.blueCat;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class DemoTest2025021401 {

    @Test
    public void demo2025021401() {
        UUID uuid = UUID.randomUUID();
        System.out.println("appid：" + uuid);

        UUID uuid2 = UUID.randomUUID();
        System.out.println("secret：" + uuid2);

        //创建sm2 对象
        SM2 sm2 = SmUtil.sm2();
        //这里会自动生成对应的随机秘钥对 , 注意！ 这里一定要强转，才能得到对应有效的秘钥信息
        byte[] privateKey = BCUtil.encodeECPrivateKey(sm2.getPrivateKey());
        //这里公钥不压缩  公钥的第一个字节用于表示是否压缩  可以不要
        byte[] publicKey = ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(false);
        //打印当前的公私秘钥
        System.out.println("SM2公钥: " + HexUtil.encodeHexStr(publicKey));
        System.out.println("SM2公钥长度: " + HexUtil.encodeHexStr(publicKey).length());
        System.out.println("SM2私钥: " + HexUtil.encodeHexStr(privateKey));
        System.out.println("SM2私钥长度: " + HexUtil.encodeHexStr(privateKey).length());
    }
}
