package com.kaolazhengxin.TestDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;

public class testLogin {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:\\Java\\chromedriver\\chromedriver.exe");
//		设置浏览器
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");	
		WebDriver driver = new ChromeDriver(option);
//		driver.get("https://baidu.com");
		driver.get("https://test.kaolazhengxin.com/pcs/login.html");
//		获取元素
		WebElement username = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement btnSendCode = driver.findElement(By.id("btnSendCode"));
		WebElement loginBtn = driver.findElement(By.xpath("//input[@value='立即登录']"));
		WebElement smsCode = driver.findElement(By.id("smsCode"));
		//		输入内容
		username.clear();
		username.sendKeys("18612963986");
		password.clear();
		password.sendKeys("a12345");
//		等待输入
		Thread.sleep(15000);
		smsCode.sendKeys("111111");
		btnSendCode.click();
		loginBtn.click();
//		WebDriver.Options

//		ChromeOptions option = new ChromeOptions();
//		option.addArguments("disable-infobars");	
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
////		capabilities.setCapability(capabilityName, value);
//		option.addArguments("disable-infobars");	
	}

}
