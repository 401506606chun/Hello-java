package com.lakala.api.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

public class Test3 {

	public static void main(String[] args) throws IOException {
		
		System.out.println("begin:"+System.currentTimeMillis());
		FileWriter writer = new FileWriter("E://mobile_147.txt");
		BufferedWriter bw= new BufferedWriter(writer); 
		for(int i=0; i<=0 ;i++){
			StringBuilder sb =  new StringBuilder();
			String mobile = "147"+getMobile(i);
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
	
	public static void file(String sb){
		System.out.println("file begin:"+System.currentTimeMillis());
		File file = new File("C://Users/Daimm/Desktop/mobile_data1.txt");
        FileOutputStream outputStream=null;
        FileChannel channel=null;
        ByteBuffer buffer= null;
		try {
			outputStream = new FileOutputStream(file);
			channel = outputStream.getChannel();
	        buffer = ByteBuffer.allocate(1024);
	        buffer.put(sb.getBytes());
	        buffer.flip();     //此处必须要调用buffer的flip方法
	        channel.write(buffer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			 try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		     try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		System.out.println("file end:"+System.currentTimeMillis());
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
