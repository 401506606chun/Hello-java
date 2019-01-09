package com.lakala.api.mobile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class FourMobileTitle {

	public static void main(String[] args) throws IOException {
		
		System.out.println("begin:"+System.currentTimeMillis());
		String fourTitle = args[0];
		System.out.println("fourTitle:"+fourTitle);
		String filePath = args[1];
		System.out.println("filePath:"+filePath);
		FileWriter writer = new FileWriter(filePath+"mobile_"+fourTitle+".txt");
		BufferedWriter bw= new BufferedWriter(writer); 
		for(int i=0; i<=9 ;i++){
			StringBuilder sb =  new StringBuilder();
			String mobile = fourTitle+getMobile(i);
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
		return String.format("%07d", oldMobile);
	}
}
