package com.lakala.api.identify;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class TestA {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public static void main(String[] args) throws IOException, ParseException {
		
		System.out.println("begin:"+System.currentTimeMillis());
		String cityCode = "110100";
		FileWriter writer = new FileWriter("E://md5_"+cityCode+".txt");
	 	String start = "19550101";
        String end = "20050102";
        Date dBegin = sdf.parse(start);
        Date dEnd = sdf.parse(end);
        List <Date> lDate = findDates(dBegin, dEnd);
        System.out.println("date size:"+lDate.size());
        BufferedWriter bw= new BufferedWriter(writer); 
        for (Date date: lDate) {
        	String identBegin = cityCode + sdf.format(date);
        	for(int i=0;i<=999;i++){
        		String identify = identBegin +getThree(i);
        		identify = identify + calcTrailingNumber(identify.toCharArray());
        		System.out.println("identify:"+identify);
        		StringBuilder sb =  new StringBuilder();
     			String md5Str = md5(identify);
     			sb.append(md5Str).append(",").append(identify);
     			bw.write(sb.toString());
     			bw.newLine(); 
        	}
           
        }
		bw.flush();
        bw.close();
        writer.close();
		System.out.println("end:"+System.currentTimeMillis());
	}
	

	//获取某段时间内的所有日期 
	public static List <Date> findDates(Date bBegin, Date dEnd) {
	    List<Date> lDate = new ArrayList<Date>();
	    lDate.add(bBegin);
	    Calendar calBegin = Calendar.getInstance();
	    // 使用给定的 Date 设置此 Calendar 的时间
	    calBegin.setTime(bBegin);
	    Calendar calEnd = Calendar.getInstance();
	    // 使用给定的 Date 设置此 Calendar 的时间
	    calEnd.setTime(dEnd);
	    // 测试此日期是否在指定日期之后
	    while (dEnd.after(calBegin.getTime())) {
	        // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
	        calBegin.add(Calendar.DAY_OF_MONTH, 1);
	        lDate.add(calBegin.getTime());
	    }
	    return lDate;
	}
	
	public static String md5(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}else{
			return DigestUtils.md5Hex(str).toUpperCase();
		}
	}
	
	public static String getThree(int lod){
		return String.format("%03d", lod);
	}

	/*
     * <p>18位身份证验证</p> 根据〖中华人民共和国国家标准 GB
     * 11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。 第十八位数字(校验码)的计算方法为：
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8
     * 4 2 2.将这17位数字和系数相乘的结果相加。 3.用加出来和除以11，看余数是多少？ 4.余数只可能有0 1 2 3 4 5 6 7 8 9
     * 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */
    public static char calcTrailingNumber(char[] chars) {
        if (chars.length < 17) {
            return ' ';
        }
        int[] c = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
        char[] r = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
        int[] n = new int[17];
        int result = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(chars[i] + "");
        }
        for (int i = 0; i < n.length; i++) {
            result += c[i] * n[i];
        }
        return r[result % 11];
    }
}
