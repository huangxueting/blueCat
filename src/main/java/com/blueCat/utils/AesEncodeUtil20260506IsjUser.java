package com.blueCat.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * Aes加解密工具类
 *
 * @author Shichao Li
 */
public class AesEncodeUtil20260506IsjUser {

    /**
     * 编码方式
     */
    public static final String CODE_TYPE = "UTF-8";
//    public static final String CODE_TYPE = "GBK";

    /**
     * 填充类型
     */
//    public static final String AES_TYPE = "AES/ECB/PKCS5Padding";
//    public static final String AES_TYPE = "AES/ECB/PKCS7Padding";
//    /**
//     * java 不支持ZeroPadding
//     */
//    public static final String AES_TYPE = "AES/CBC/ZeroPadding";
    /**
     * 此类型 加密内容,密钥必须为16字节的倍数位,否则抛异常,需要字节补全再进行加密
     */
    public static final String AES_TYPE = "AES/ECB/NoPadding";

    /**
     * 私钥
     * AES固定格式为128/192/256 bits.即：16/24/32bytes。DES固定格式为128bits，即8bytes。
     */
    // private static final String AES_KEY = "ldELOMLavkU9WKFt";

    /**
     * 字符补全
     */
    private static final String[] CONSULT = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G"};

    /**
     * 加密 NoPadding 字节补全
     *
     * @param encrypt
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encryptNoPadding(String encrypt, String aesKey) throws UnsupportedEncodingException {
        String encrypt1 = completionCodeFor16Bytes(encrypt);
        return encrypt(encrypt1, aesKey);
    }

    /**
     * 解密 NoPadding 字节补全去除
     *
     * @param decrypt
     * @return
     */
    public static String decryptNoPadding(String decrypt, String aesKey) {
        String decrypt1 = decrypt(decrypt, aesKey);
        return resumeCodeOf16Bytes(decrypt1);
    }

    /**
     * 加密
     *
     * @param cleartext
     * @return
     */
    public static String encrypt(String cleartext, String aesKey) {
        //加密方式： AES128(CBC/PKCS5Padding) + Base64, 私钥：1111222233334444
        try {
            //两个参数，第一个为私钥字节数组， 第二个为加密方式 AES或者DES
            SecretKeySpec key = new SecretKeySpec(aesKey.getBytes(), "AES");
            //实例化加密类，参数为加密方式，要写全
            //PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            //初始化，此方法可以采用三种方式，按加密算法要求来添加。（1）无第三个参数（2）第三个参数为SecureRandom random = new SecureRandom();中random对象，随机数。(AES不可采用这种方法)（3）采用此代码中的IVParameterSpec
            //加密时使用:ENCRYPT_MODE;  解密时使用:DECRYPT_MODE;
            //CBC类型的可以在第三个参数传递偏移量zeroIv,ECB没有偏移量
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //加密操作,返回加密后的字节数组，然后需要编码。主要编解码方式有Base64, HEX, UUE,7bit等等。此处看服务器需要什么编码方式
            byte[] encryptedData = cipher.doFinal(cleartext.getBytes(CODE_TYPE));
            return new BASE64Encoder().encode(encryptedData);
        } catch (Exception e) {
//            log.error("加密出错", e);
            return "";
        }
    }

    /**
     * 解密
     *
     * @param encrypted
     * @return
     */
    private static String decrypt(String encrypted, String aesKey) {
        try {
            byte[] byteMi = new BASE64Decoder().decodeBuffer(encrypted);
            SecretKeySpec key = new SecretKeySpec(
                    aesKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(AES_TYPE);
            //与加密时不同MODE:Cipher.DECRYPT_MODE
            //CBC类型的可以在第三个参数传递偏移量zeroIv,ECB没有偏移量
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, CODE_TYPE);
        } catch (Exception e) {
//            log.error("解密出错", e);
            return "";
        }
    }

    /**
     * NoPadding 补全字符
     *
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String completionCodeFor16Bytes(String str) throws UnsupportedEncodingException {
        int num = str.getBytes(CODE_TYPE).length;
        int index = num % 16;
        //进行加密内容补全操作, 加密内容应该为 16字节的倍数, 当不足16*n字节是进行补全, 差一位时 补全16+1位
        //补全字符 以 $ 开始,$后一位代表$后补全字符位数,之后全部以0进行补全;
        if (index != 0) {
            StringBuffer sbBuffer = new StringBuffer(str);
            if (16 - index == 1) {
                sbBuffer.append("$" + CONSULT[16 - 1] + addStr(16 - 1 - 1));
            } else {
                sbBuffer.append("$" + CONSULT[16 - index - 1] + addStr(16 - index - 1 - 1));
            }
            str = sbBuffer.toString();
        }
        return str;
    }

    /**
     * 追加字符
     *
     * @param num
     * @return
     */
    private static String addStr(int num) {
        StringBuffer sbBuffer = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            sbBuffer.append("0");
        }
        return sbBuffer.toString();
    }

    /**
     * 还原字符(进行字符判断)
     *
     * @param str
     * @return
     */
    private static String resumeCodeOf16Bytes(String str) {
        int indexOf = str.lastIndexOf("$");
        if (indexOf == -1) {
            return str;
        }
        String trim = str.substring(indexOf + 1, indexOf + 2).trim();
        int num = 0;
        for (int i = 0; i < CONSULT.length; i++) {
            if (trim.equals(CONSULT[i])) {
                num = i;
            }
        }
        if (num == 0) {
            return str;
        }
        return str.substring(0, indexOf).trim();
    }

    public static void main(String[] args) {
        long currentTime = System.currentTimeMillis();
//        String tokenText = String.format("%s|%s|%s", "1001", currentTime, "tmY0rAo102EmnV47");    //i深建报名
        String tokenText = String.format("%s|%s|%s", "1002", currentTime, "EKcqlyT3wy8x5UUA");      //人脸机签到
        String token = null;
        try {
//            token = AesEncodeUtil.encryptNoPadding(tokenText, "ECJb1QRf76p3ybZb");    //i深建报名
//            token = AesEncodeUtil.encryptNoPadding(tokenText, "ECJb1QRf76p3ybZb");  //人脸机签到
            token = encryptNoPadding(tokenText, "ECJb1QRf76p3ybZb");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("token = " + token);


    }
}
