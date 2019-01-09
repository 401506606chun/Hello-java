package com.lakala.api.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class TestB {
	
public static void main(String[] args) throws IOException {
		
		System.out.println("begin:"+System.currentTimeMillis());
		FileWriter writer = new FileWriter("E://mobile-MD5/cmcc/mobile_152.txt");
		BufferedWriter bw= new BufferedWriter(writer); 
		for(int i=0; i<=99999999 ;i++){
			StringBuilder sb =  new StringBuilder();
			String mobile = "152"+getMobile(i);
			System.out.println("mobile:"+mobile);
			String md5Str = md5(mobile);
			sb.append(md5Str).append(",").append(mobile);
			bw.write(sb.toString());
			bw.newLine(); 
		}
		bw.flush();
        bw.close();
        writer.close();
		System.out.println("end:"+System.currentTimeMillis());
	}
	
	public static String md5(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}else{
			return DigestUtils.md5Hex(str).toUpperCase();
		}
	}
	
	public static String getMobile(int oldMobile){
		return String.format("%08d", oldMobile);
	}
}
