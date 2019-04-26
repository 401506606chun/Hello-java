package org.seleniumhq.selenium.selenium_java;

//import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class login {
	WebDriver driver;
//	@Test
	public void InitDriver() {
		String os = System.getProperty("os.name");
		System.out.println(os);
//		以下是根据不同电脑走不同的驱动路径
		if(os.contentEquals("Windows 10")){
		//windows使用
		System.setProperty("webdriver.chrome.driver", "D:\\Git\\demo\\selenium-java\\chromedriver.exe");
		}else if(os.contentEquals("MAC")){
//		MAC使用
		System.setProperty("webdriver.chrome.driver", "//Users//jiubugaosuni//workspace//javaSelenium//chromedriver");
//		System.setProperty("webdriver.chrome.driver", "/Users/jiubugaosuni/workspace/javaSelenium/chromedriver");
		}else {
		System.out.println("没有这个驱动");
		}
//		配置浏览器（不要提示具体信息，如浏览器受到自动软件控制）
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");
		driver = new ChromeDriver(option);
		driver.get("https://www.imooc.com");
//		浏览器最大化		
		driver.manage().window().maximize();
	}
//	@test
	public void sleep(int sleeptime) {
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	封闭By by
	public By byStr(String by,String element) {
		if(by.equals("id")) {
			return By.id(element);
		}else if(by.equals("name")){
			return By.name(element);
			
		}else if(by.equals("className")) {
			return By.className(element);
		}
		else{
			return By.xpath(element);
		}
		
	}
//	封装element
//	@Test
	public void loginScript() {
		this.InitDriver();
//		往下定义变量
		String userValue = "18612963986";
		String passwordValue ="a1111111";
		String userElement = "email";
		String userBy = "name";
		String passwordElement = "password";
		String passwordBy = "name";
		String loginButtonElement = "//div[@class='rlf-group clearfix']";
		String loginButtonBy = "xpath";
		String headerBy = "id";
		String headerElement = "header-avator";
		String userInfoBy ="className";
		String userInfoElement = "name";
//		点击登录按钮
		driver.findElement(By.id("js-signin-btn")).click();
//		等待后输入用户及密码进行登录
		sleep(3000);
		WebElement user = driver.findElement(this.byStr(userBy, userElement));
		user.isDisplayed();
		WebElement password = driver.findElement(this.byStr(passwordBy, passwordElement));
		password.isDisplayed();
		
		sleep(1000);
		WebElement loginButton = driver.findElement(this.byStr(loginButtonBy, loginButtonElement));
		loginButton.isDisplayed();
		
		user.sendKeys(userValue);
		password.sendKeys(passwordValue);
		loginButton.click();
		
		//	鼠标移动到头像，检查是否登录成功
		sleep(3000);
		WebElement header = driver.findElement(this.byStr(headerBy, headerElement));
		header.isDisplayed();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(header).perform();
		
		String userInfo = driver.findElement(this.byStr(userInfoBy, userInfoElement)).getText();
		System.out.println(userInfo);
		System.out.println("登录成功");
		this.takeScreenShot("登录成功.png");
		
	}
//	封装截图
//	参数photo意义为 定义截图的格式 如.png/.jpg等
	public void takeScreenShot(String photo) {
		long date = System.currentTimeMillis();
		SimpleDateFormat nowDate = new SimpleDateFormat("yyyymmddhhmmss");//SimpleDateFormat可用于格式化时间
		String shotPath = nowDate.format(date);
		shotPath = shotPath+photo;
		String curPath = System.getProperty("user.dir");
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回。
		try {
//			FileUtils.copyFile(srcFile,new File(".\\Screenshots\\screen.png"));
			FileUtils.copyFile(srcFile, new File(curPath+"/"+shotPath));
			//利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象。
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("我截图了"+curPath+"/"+shotPath);
	}
	public void closeBroswer() {
		driver.close();
		
	}
	
	
//	@Test
	public static void main(String[] args) {
		login logins = new login();
//		logins.InitDriver();
		logins.loginScript();
//		logins.closeBroswer();
		}
	}
	
