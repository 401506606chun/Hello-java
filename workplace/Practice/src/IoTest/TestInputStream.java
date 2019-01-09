package IoTest;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


public class TestInputStream {
	
//	读文件
	public void myRe(String filea) throws Exception{
//		FileInputStream a = new FileInputStream("D:/Java/workplace/Practice/test.txt");
		FileInputStream a = new FileInputStream(filea);
		byte[] bs = new byte[15];
		int len = 0;
		while( ( len=a.read(bs) ) != -1){
			for( int i = 0; i<len ; i++){
				System.out.print( (char)bs[i] );
			}
			System.out.println();
		}
		a.close();
	}
//	写文件
	public void myWrite(String fileb) throws Exception{
		
//		定义一个变量存放要写入的内容
		String hello = "Hello world!~";
		byte[] bs = hello.getBytes();
		
		FileOutputStream b = new FileOutputStream(fileb,true);//true 如果文件存在 就追加内容
		b.write(bs);
		b.close();
	
	}
	
	public void TestDataStream() throws Exception{
//		创建节点流
		FileOutputStream fout = new FileOutputStream("test3.txt");
//		封装过滤流
		DataOutputStream dout = new DataOutputStream(fout);
//		写数据
		dout.writeChars("I know what you want.");
//		关闭流
		dout.close();
	}
	
	public void TestBufferdStream() throws Exception{
		String data = "Hello all the world!";
		byte[] bs = data.getBytes();

//		创建节点流
		FileOutputStream fout2 = new FileOutputStream("test3.txt");
		//		封装过滤流
		BufferedOutputStream dout2 = new BufferedOutputStream(fout2);
//		读写
		dout2.write(bs);
//		关闭流
		dout2.close();
	}
	
	public static void main(String[] args) throws Exception {
		TestInputStream myAction = new TestInputStream();
		myAction.myWrite("test.txt");
		myAction.myRe("test.txt");
		myAction.TestDataStream();
		myAction.TestBufferdStream();
		myAction.myRe("test3.txt");
		
		
	}
}
