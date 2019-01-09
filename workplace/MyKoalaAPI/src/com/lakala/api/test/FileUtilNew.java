package com.lakala.api.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;



public class FileUtilNew {
	
	
	public static String getImageStr(String imgFile){
        byte[] data = null; 
        BufferedInputStream bufferd = null;  
        //读取图片字节数组  
        try   
        {  
        	bufferd = new BufferedInputStream(new FileInputStream(imgFile));
            data = new byte[bufferd.available()];  
            bufferd.read(data);  
            bufferd.close();  
        }catch(FileNotFoundException e){
        	System.out.println("idCardPhoto file not found:"+e);
        	return null;
        }catch (IOException e){
        	System.out.println("read idCardPhoto file error:"+e);
        	return null; 
        }  
        //对字节数组Base64编码  
        return Base64.encodeToString(data);
    }
	
//	public static String getImgPath(String fileDir,String fileSuffix){
//		Format format = new SimpleDateFormat("yyyyMMdd");
//        String fmtDate = format.format(new Date());
//        String imagePathDir = "/" + fmtDate;
//        String imageRealPath = fileDir + imagePathDir;
//        File imageSaveDir = new File(imageRealPath);
//        if (!imageSaveDir.exists()) {
//            imageSaveDir.mkdirs();
//        }
//        String uuid = UUID.randomUUID().toString();
//        String imageSaveName = uuid + fileSuffix;
//        String filename = imageRealPath + "/" + imageSaveName;
//        return filename;
//	}
//	
//	public static void generateImage(String imgStr,String imgFile){
//		OutputStream out = null;
//		try {
//			// Base64解码
//			byte[] b = Base64.decode(imgStr);
//			for (int i = 0; i < b.length; ++i) {
//				if (b[i] < 0) {// 调整异常数据
//				b[i] += 256;
//				}
//			}
//			// 生成jpg图片
//			String imgFilePath = imgFile;// 新生成的图片
//			out = new FileOutputStream(imgFilePath);
//			out.write(b);
//			out.flush();
//		} catch (Exception e) {
//			logger.error("generate image error:"+e);
//		}finally{
//			if(out != null){
//				try {
//					out.close();
//				} catch (Exception e) {
//					logger.error("generate image error out:"+e);
//				}
//			}
//		}
//	}
}
