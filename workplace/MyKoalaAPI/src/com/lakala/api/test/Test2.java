package com.lakala.api.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class Test2 {
//	private static final BASE64Encoder encoder = new BASE64Encoder();
//	private static final BASE64Decoder decoder = new BASE64Decoder();
	
	

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		for(int i=0;i<1;i++){
			String path ="E:/IDCards/20180313/3a7cbaea-be8e-452b-b146-934b2156da21.jpg";
			startRequest(path);
			Thread.sleep(1000);
		}
	}
	
	static void startRequest(String path) throws InterruptedException, ExecutionException{
		ExecutorService exec = Executors.newFixedThreadPool(300); 
		long sum = 0l;
		List<Future<Long>> fList = new ArrayList<Future<Long>>();
		for (int i = 0; i < 500; i++) {
		    Future<Long> f = exec.submit(new TradeTask("request" + i, path));  
            fList.add(f);  
		}
		exec.shutdown();
		for(Future<Long> fu : fList){
			long a = fu.get();
			sum = sum + a;
		}
		System.out.println("总耗时："+sum);
		System.out.println("平均时："+sum/500);
	
	}
	
	static class TradeTask implements Callable {

		private String name;
		
		private String imgFile;

		public TradeTask(String name, String imgFile) {
			this.name = name;
			this.imgFile = imgFile;
		}

		 @Override
		 public Object call() {
			long startTime = System.currentTimeMillis();
			InputStream in = null;  
	        byte[] data = null;  
	        //读取图片字节数组  
	        try   
	        {  
	            in = new FileInputStream(imgFile);          
	            data = new byte[in.available()];  
	            in.read(data);  
	            in.close();  
		        }catch(FileNotFoundException e){
		        	System.out.println("idCardPhoto file not found:"+e);
		        }catch (IOException e){
		        	System.out.println("read idCardPhoto file error:"+e);
		        }  
		        //对字节数组Base64编码  
			long endTime = System.currentTimeMillis();
			long time = endTime - startTime;
			System.out.print(name + ",请求耗时(ms):" + (time)+"\n");
			return time;

		}


	}
}
