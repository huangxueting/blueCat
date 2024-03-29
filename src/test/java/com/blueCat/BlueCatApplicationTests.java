package com.blueCat;

import com.alibaba.excel.EasyExcel;
import com.linuxense.javadbf.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@SpringBootTest
class BlueCatApplicationTests {

	@Test
	public void dbfFile() throws FileNotFoundException {
				// let us create field definitions first
				// we will go for 3 fields
				DBFField[] fields = new DBFField[11];

				fields[0] = new DBFField();
				fields[0].setName("ZQDM");
				fields[0].setType(DBFDataType.CHARACTER);
				fields[0].setLength(6);

				fields[1] = new DBFField();
				fields[1].setName("GQDJRQ");
				fields[1].setType(DBFDataType.CHARACTER);
				fields[1].setLength(10);

				fields[2] = new DBFField();
				fields[2].setName("GDDM");
				fields[2].setType(DBFDataType.CHARACTER);
				fields[2].setLength(10);

				fields[3] = new DBFField();
				fields[3].setName("GDXM");
				fields[3].setType(DBFDataType.CHARACTER);
				fields[3].setLength(128);

				fields[4] = new DBFField();
				fields[4].setName("YGDXM");
				fields[4].setType(DBFDataType.CHARACTER);
				fields[4].setLength(128);

				fields[5] = new DBFField();
				fields[5].setName("ZJHM");
				fields[5].setType(DBFDataType.CHARACTER);
				fields[5].setLength(64);

				fields[6] = new DBFField();
				fields[6].setName("TYZJHM");
				fields[6].setType(DBFDataType.CHARACTER);
				fields[6].setLength(64);

				fields[7] = new DBFField();
				fields[7].setName("CGSL");
				fields[7].setType(DBFDataType.NUMERIC);
				fields[7].setLength(13);
				fields[7].setDecimalCount(0);

				fields[8] = new DBFField();
				fields[8].setName("SFDWTP");
				fields[8].setType(DBFDataType.CHARACTER);
				fields[8].setLength(1);

				fields[9] = new DBFField();
				fields[9].setName("TPGDDM");
				fields[9].setType(DBFDataType.CHARACTER);
				fields[9].setLength(10);

				fields[10] = new DBFField();
				fields[10].setName("HBLX");
				fields[10].setType(DBFDataType.CHARACTER);
				fields[10].setLength(2);

				DBFWriter writer = new DBFWriter(new FileOutputStream("C:\\Users\\xthuang\\Desktop\\DBF\\test.dbf"));
				writer.setFields(fields);
				writer.setCharactersetName("GBK");

//				 now populate DBFWriter
				Object rowData[];
				for (int i = 0; i <= 50000; i++) {
					rowData = new Object[11];
					rowData[0] = "000002";
					rowData[1] = "2021-11-12";
					rowData[2] = String.valueOf(i);
					rowData[3] = "王帅五";
					rowData[4] = "王帅五";
					rowData[5] = "91000000000000005L";
					rowData[6] = "91000000000000005L";
					rowData[7] = 40000000;
					rowData[8] = "1";
					rowData[9] = "0000043090";
					rowData[10] = "DB";

					writer.addRecord(rowData);
				}

				// write to file
				writer.close();
	}

	@Test
	public void ss2ss() throws IOException {
//		String[] filtAry = {"*", "#"};
//		String ad = "*#*王*佳#绩**";
//		char[] chars = ad.toCharArray();
//		boolean falg = false;
//		int num = 0;
//		for (int i = 0; i < chars.length; i++) {
//			for (String s : filtAry) {
//				if (s.equals(String.valueOf(chars[i]))) {
//					num++;
//					falg = true;
//				}
//			}
//			falg = false;
//			if (falg) {
//				return;
//			}
//		}
//		num +=1;
//		System.out.println("num = " + num);
//		for (int i = 0; i < num; i++) {
//			System.out.println(chars[i]);
//		}
//		boolean flag = true;
//		while (flag) {
//			for (int i = 0; i < ad.length(); i++){
//
//			}
//			for (String s : filtAry) {
//				ad = ad.trim();
////			ad = StringUtils.strip(ad, s);
//				ad = StringUtils.stripStart(ad, s);
//				System.out.println("ad = " + ad);
//			}
//		}

//		String str = "  # #   小王";
//		str = str.trim();
//			int i = str.indexOf("#");
//			if (i == 0) {
//				str = str.substring(1);
//			}
//		System.out.println("str = " + str);
//
//
//		String test = " #* 挖寄 给你* ";
//		String[] str1 = {"*", "#", " "};
//		for (String s : str1) {
//			test = test.replace(s, "");
//		}
//		System.out.println("test = " + test);

//		InputStream fileInputStream = new InputStream(new FileInputStream("C:\\Users\\xthuang\\Desktop\\DBF\\adgr.dbf"));

		String ss = "85fe0e486d0ab3607d8b5cfcf420b9fe";

		FileInputStream fileInputStream = new FileInputStream("C:\\Users\\xthuang\\Desktop\\DBF\\adgr2.dbf");
		byte[] bytes = IOUtils.toByteArray(fileInputStream);
		String s = DigestUtils.md5Hex(bytes);
		System.out.println("s = " + s);
		String asd = "";


		String oo = "test";
		String soo = "stests";
		String format = String.format("%s/%s", oo, soo);
		System.out.println("format = " + format);
	}



	@Test
	public void ssss() {
		boolean wltpcgjh = "WLTPCGJH_20200202.DBF".contains("1WLTPCGJH");
		System.out.println("wltpcgjh = " + wltpcgjh);
		Long aLong = Long.valueOf("123");
		DBFReader reader = null;
		List<T> asl;
		try {

			// create a DBFReader object
			reader = new DBFReader(new FileInputStream("C:\\Users\\xthuang\\Desktop\\DBF\\adgr.dbf"));
			reader.setCharactersetName("GBK");

			// get the field count if you want for some reasons like the following

			int numberOfFields = reader.getFieldCount();

			// use this count to fetch all field information
			// if required

			for (int i = 0; i < numberOfFields; i++) {

				DBFField field = reader.getField(i);

				// do something with it if you want
				// refer the JavaDoc API reference for more details
				//
				System.out.println(field.getName());
			}

			// Now, lets us start reading the rows

			DBFRow row;

			while ((row = reader.nextRow()) != null) {
				String gdxm = row.getString("GDXM");
				System.out.println(gdxm);
			}

			// By now, we have iterated through all of the rows

		} catch (DBFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			DBFUtils.close(reader);
		}
	}

	@Test
	public void sss() {
				DBFReader reader = null;
				try {

					// create a DBFReader object
					reader = new DBFReader(new FileInputStream("C:\\Users\\xthuang\\Desktop\\DBF\\adgr.dbf"));
					reader.setCharactersetName("GBK");

					// get the field count if you want for some reasons like the following

					int numberOfFields = reader.getFieldCount();

					// use this count to fetch all field information
					// if required

					for (int i = 0; i < numberOfFields; i++) {

						DBFField field = reader.getField(i);

						// do something with it if you want
						// refer the JavaDoc API reference for more details
						//
						System.out.println(field.getName());
					}

					// Now, lets us start reading the rows

					Object[] rowObjects;

					while ((rowObjects = reader.nextRecord()) != null) {

						for (int i = 0; i < rowObjects.length; i++) {
							System.out.println(rowObjects[i]);
						}
					}

					// By now, we have iterated through all of the rows

				} catch (DBFException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					DBFUtils.close(reader);
				}
	}

	@Test
	public void ss() {
		Date nowDate = new Date();
		Calendar c =Calendar.getInstance();
		c.setTime(nowDate);
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY, 15);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		Date time = c.getTime();
		String format = new SimpleDateFormat("yyyyMMdd - HH:mm:ss").format(time);
		System.out.println("format = " + format);
	}

	/**
	 * 根据模板生成新word文档
	 * 判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
	 * @param inputUrl 模板存放地址
	 * @param textMap 需要替换的信息集合
	 * @param tableList 需要插入的表格信息集合
	 * @return 成功返回true,失败返回false
	 */
	public static boolean changWord(String inputUrl, String outputUrl,
									Map<String, String> textMap, List<String[]> tableList) {

		//模板转换默认成功
		boolean changeFlag = true;
		try {
			//获取docx解析对象
			XWPFDocument document = new XWPFDocument(POIXMLDocument.openPackage(inputUrl));
			//解析替换文本段落对象
			changeText(document, textMap);
			//解析替换表格对象
			changeTable(document, textMap, tableList);

			//生成新的word
			File file = new File(outputUrl);
			FileOutputStream stream = new FileOutputStream(file);
			document.write(stream);
			stream.close();

		} catch (IOException e) {
			e.printStackTrace();
			changeFlag = false;
		}

		return changeFlag;

	}

	/**
	 * 替换段落文本
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 */
	public static void changeText(XWPFDocument document, Map<String, String> textMap){
		//获取段落集合
		List<XWPFParagraph> paragraphs = document.getParagraphs();

		for (XWPFParagraph paragraph : paragraphs) {
			//判断此段落时候需要进行替换
			String text = paragraph.getText();
			if(checkText(text)){
				List<XWPFRun> runs = paragraph.getRuns();
				for (XWPFRun run : runs) {
					//替换模板原来位置
					run.setText(changeValue(run.toString(), textMap),0);
				}
			}
		}

	}

	/**
	 * 替换表格对象方法
	 * @param document docx解析对象
	 * @param textMap 需要替换的信息集合
	 * @param tableList 需要插入的表格信息集合
	 */
	public static void changeTable(XWPFDocument document, Map<String, String> textMap,
								   List<String[]> tableList){
		//获取表格对象集合
		List<XWPFTable> tables = document.getTables();
		for (int i = 0; i < tables.size(); i++) {
			//只处理行数大于等于2的表格，且不循环表头
			XWPFTable table = tables.get(i);
			if(table.getRows().size()>1){
				//判断表格是需要替换还是需要插入，判断逻辑有$为替换，表格无$为插入
				if(checkText(table.getText())){
					List<XWPFTableRow> rows = table.getRows();
					//遍历表格,并替换模板
					eachTable(rows, textMap);
				}else{
//                  System.out.println("插入"+table.getText());
					insertTable(table, tableList);
				}
			}
		}
	}





	/**
	 * 遍历表格
	 * @param rows 表格行对象
	 * @param textMap 需要替换的信息集合
	 */
	public static void eachTable(List<XWPFTableRow> rows ,Map<String, String> textMap){
		for (XWPFTableRow row : rows) {
			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				//判断单元格是否需要替换
				if(checkText(cell.getText())){
					List<XWPFParagraph> paragraphs = cell.getParagraphs();
					for (XWPFParagraph paragraph : paragraphs) {
						List<XWPFRun> runs = paragraph.getRuns();
						for (XWPFRun run : runs) {
							run.setText(changeValue(run.toString(), textMap),0);
						}
					}
				}
			}
		}
	}

	/**
	 * 为表格插入数据，行数不够添加新行
	 * @param table 需要插入数据的表格
	 * @param tableList 插入数据集合
	 */
	public static void insertTable(XWPFTable table, List<String[]> tableList){
		//创建行,根据需要插入的数据添加新行，不处理表头
		for(int i = 1; i < tableList.size(); i++){
			XWPFTableRow row =table.createRow();
		}
		//遍历表格插入数据
		List<XWPFTableRow> rows = table.getRows();
		for(int i = 1; i < rows.size(); i++){
			XWPFTableRow newRow = table.getRow(i);
			List<XWPFTableCell> cells = newRow.getTableCells();
			for(int j = 0; j < cells.size(); j++){
				XWPFTableCell cell = cells.get(j);
				cell.setText(tableList.get(i-1)[j]);
			}
		}
	}
	/**
	 * 判断文本中时候包含$
	 * @param text 文本
	 * @return 包含返回true,不包含返回false
	 */
	public static boolean checkText(String text){
		boolean check  =  false;
		if(text.indexOf("$")!= -1){
			check = true;
		}
		return check;
	}
	/**
	 * 匹配传入信息集合与模板
	 * @param value 模板需要替换的区域
	 * @param textMap 传入信息集合
	 * @return 模板需要替换区域信息集合对应值
	 */
	public static String changeValue(String value, Map<String, String> textMap){
		Set<Map.Entry<String, String>> textSets = textMap.entrySet();
		for (Map.Entry<String, String> textSet : textSets) {
			//匹配模板与替换值 格式${key}
			String key = "${"+textSet.getKey()+"}";
			if(value.indexOf(key)!= -1){
				value = textSet.getValue();
			}
		}
		//模板未匹配到区域替换为空
		if(checkText(value)){
			value = "";
		}
		return value;
	}



	@Test
	public void exportWord1() throws Exception {

		//模板文件地址
		String inputUrl = "C:\\Users\\xthuang\\Desktop\\word\\create_table.docx";
		//新生产的模板文件
		String outputUrl = "C:\\Users\\xthuang\\Desktop\\word\\test.docx";

		Map<String, String> testMap = new HashMap<String, String>();
		testMap.put("number", "1");
		testMap.put("code1", "123");
		testMap.put("code2", "456");
		testMap.put("code3", "789");
		testMap.put("code4", "987");
		testMap.put("code5", "654");

		List<String[]> testList = new ArrayList<String[]>();
		testList.add(new String[]{"1","1AA","1BB","1CC","1DD","1EE"});
		testList.add(new String[]{"2","2AA","2BB","2CC","2DD","2EE"});
		testList.add(new String[]{"3","3AA","3BB","3CC","3DD","3EE"});
		testList.add(new String[]{"4","4AA","4BB","4CC","4DD","4EE"});



	}

	//-----------------------------------------------------------------------------------------

	@Test
	public void exportWord() throws Exception {
		XWPFDocument document = new XWPFDocument();

		//Write the Document in file system
		FileOutputStream out = new FileOutputStream("C:\\Users\\xthuang\\Desktop\\word\\create_table.docx");

		//添加标题
		XWPFParagraph titleParagraph = document.createParagraph();
		//设置段落居中
		titleParagraph.setAlignment(ParagraphAlignment.CENTER);

		XWPFRun titleParagraphRun = titleParagraph.createRun();
		titleParagraphRun.setBold(true);
		titleParagraphRun.setFontFamily("黑体");
		titleParagraphRun.setFontSize(14);
		titleParagraphRun.setText("股东数据导入记录表");
		titleParagraphRun.setColor("000000");

		//段落
		XWPFParagraph firstParagraph = document.createParagraph();
		firstParagraph.setAlignment(ParagraphAlignment.RIGHT);
		XWPFRun run = firstParagraph.createRun();
		run.setText("制表日期：2021年07月26日");
		run.setColor("000000");
		run.setFontFamily("宋体");
		run.setFontSize(14);

		//工作经历表格
		XWPFTable ComTable = document.createTable();


		//列宽自动分割
		CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
		comTableWidth.setType(STTblWidth.DXA);
		comTableWidth.setW(BigInteger.valueOf(9072));

		//表格第一行
		XWPFTableRow comTableRowOne = ComTable.getRow(0);
		comTableRowOne.getCell(0).setText("序号");
		comTableRowOne.addNewTableCell().setText("会议代码");
		comTableRowOne.addNewTableCell().setText("证券代码");
		comTableRowOne.addNewTableCell().setText("证券简称");
		comTableRowOne.addNewTableCell().setText("股权登记日");
		comTableRowOne.addNewTableCell().setText("互联网投票起始日期");
		comTableRowOne.addNewTableCell().setText("导入时间");
		comTableRowOne.addNewTableCell().setText("执行人");
		comTableRowOne.addNewTableCell().setText("导入情况说明");
		comTableRowOne.addNewTableCell().setText("复核人");

		XWPFTableRow xwpfTableRow;
		for (int i = 0; i<3; i++) {
			xwpfTableRow = ComTable.createRow();
			xwpfTableRow.getCell(0).setText(Integer.toString(i));
			xwpfTableRow.getCell(1).setText("73767");
			xwpfTableRow.getCell(2).setText("000690");
			xwpfTableRow.getCell(3).setText("宝新能源");
			xwpfTableRow.getCell(4).setText("2021-04-19");
			xwpfTableRow.getCell(5).setText("2021-04-23 09:15:00");
		}

		CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
		XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);


		document.write(out);
		out.close();
		System.out.println("create_table document written success.");
	}

	@Test
	public void dateTest() throws Exception {
		Date date = new Date();
		System.out.println("date = " + new SimpleDateFormat("yyyy-MM-dd").format(date));
		Calendar c = Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2021-08-31"));
		c.add(Calendar.DAY_OF_MONTH, -1);
		c.set(Calendar.HOUR_OF_DAY,15);
		c.set(Calendar.MINUTE,0);
		c.set(Calendar.SECOND,0);
		c.set(Calendar.MILLISECOND,0);
		Date time = c.getTime();
		System.out.println("time = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time));
		int i = date.compareTo(time);
		System.out.println("i = " + i);
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

	@Test
	void getRegex() {
		String regex = "^[+-]?[0-9]{1,16}$";
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println("list = " + list.toString());
		boolean matches = Pattern.matches(regex, "-1234567891234566");
		System.out.println("matches = " + matches);
	}
}
