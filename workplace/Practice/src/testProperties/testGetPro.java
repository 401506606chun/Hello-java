package testProperties;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

public class testGetPro {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
//		FileInputStream fout = new FileInputStream("a.properties");
//		BufferedInputStream bout = new BufferedInputStream(fout);	
//		prop.load(bout);

		 BufferedReader bufferedReader = new BufferedReader(new FileReader("a.properties"));
		 prop.load(bufferedReader);
		
		String a = prop.getProperty("name");
		System.out.println("name="+a);
		System.out.println("maxage="+prop.getProperty("maxage"));
		System.out.println("eye="+prop.getProperty("eye"));
//		bout.close();

	}

}
