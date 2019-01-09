package com.imooc;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectDriver {
//	public  WebDriver driver;
	public  WebDriver driverName(String browser){
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver","D:\\Java\\chromedriver\\chromedriver.exe");
			return new ChromeDriver();
			
		} else {browser.equalsIgnoreCase("firefox");
			System.setProperty("webdriver.gecko.driver","D:\\Java\\geckodriver\\geckodriver.exe");
			return new FirefoxDriver();

			
		}
	}
	
	
}
