package com.blueCat;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Decoder;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

@SpringBootTest
class BlueCatApplicationTests {

	@Test
	public void dateTest() throws Exception {
		Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-14");
		System.out.println("parse = " + new SimpleDateFormat("yyyy-MM-dd").format(parse));
		int i = new Date().compareTo(parse);
		System.out.println("new = " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		System.out.println("i = " + i);

		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
	}

	@Test
	void wltp() throws Exception {
		String str = "wltp#20200107";

		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] iba = str.getBytes();
		md.update(iba);
		byte[] resultba = md.digest();
		String resultStr = bath(resultba);
		System.out.println("resultStr = " + resultStr);
	}
	private static String decrypt(String in) {
		BASE64Decoder dec = new BASE64Decoder();
		return null;
	}

	private static String bath(byte[] ba) {
		char[] hexd = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F',};
		char[] resultca = new char[ba.length * 2];
		int index = 0;
		for (byte b : ba) {
			resultca[index++] = hexd[b >>> 4 & 0xf];
			resultca[index++] = hexd[b & 0xf];
		}
		return new String(resultca);
	}

	@Test
	void contextLoads() {
		String str = "100";

		Double integer = Double.valueOf(str);
		System.out.println(integer == 100);


//
//		Long aLong = 1100l;
//		System.out.println("aLong = " + aLong.intValue());
//
		String str1="1.1.c";
		System.out.println(str1.replaceAll("[^\\d.]+", ""));

		List<String> r = new ArrayList<>();
		r.add("faf");
		boolean aa = r.remove("aa");
		System.out.println("aa = " + aa);
//		int length = str.length();
//		System.out.println("length = " + length);
//		int i = str.indexOf(".") +1;
//		System.out.println("i = " + i);
//		String h = null;
//		String trim = h.trim();


//		String stt = "<,《.;'hjg《》，。；‘“：";
//		String stt1 = stt.replace("《》", "");
//		System.out.println("stt = " + stt);
//		System.out.println("stt1 = " + stt1);



















//		Long la =  1000L;
//		Long lb =  1030L;
//
//		BigDecimal divide = new BigDecimal(String.valueOf(la)).divide(new BigDecimal("1000"));
//		BigDecimal divide1 = new BigDecimal(String.valueOf(lb)).divide(new BigDecimal("1000"));
//
//		Integer ia = 1000;
//		Integer ib = 1050;
//
//		Integer ic = ia / 1000;
//		Integer id = ib / 1000;
























//		List<String> stringList = new ArrayList<>();

//		String st = "13.2";
//		int i = st.indexOf(".");
//		String substring1 = st.substring(0, i);
//		String substring = st.substring(i + 1);
//		System.out.println("substring1 = " + substring1);
//		System.out.println("substring = " + substring);
//		String st1 = "14";
//		boolean contains = st1.contains(".");
//		System.out.println("contains = " + contains);

//		String str1 = "  《abc《d>efg》  ";
//		stringList.add("《abc《d>efg》  ");
//		boolean contains = stringList.contains(str1);
//		System.out.println("contains = " + contains);
//		String str2 = "\nabc\ndefg";
//		String str3 = "abc\ndefg\n";
//		String str4 = "abcdefg\n";
//		String str5 = "\nabcdefg";
//		System.out.println("str1 = " + str1);
//		String strip = StringUtils.strip(StringUtils.strip(str1.trim(), "《"), "》");
//		System.out.println("strip = " + strip);
		//		boolean contains1 = str1.trim();
//		boolean contains2 = str2.contains("\n");
//		boolean contains3 = str3.contains("\n");
//		boolean contains4 = str4.contains("\n");
//		boolean contains5 = str5.contains("\n");
//		System.out.println("contains = " + contains1);
//		System.out.println("contains = " + contains2);
//		System.out.println("contains = " + contains3);
//		System.out.println("contains = " + contains4);
//		System.out.println("contains = " + contains5);
//
//		System.out.println(str1);
	}
}
