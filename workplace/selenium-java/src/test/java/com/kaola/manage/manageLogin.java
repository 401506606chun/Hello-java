package com.kaola.manage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class manageLogin {
	WebDriver driver;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		manageLogin managetest = new manageLogin();
		managetest.InitDriver();
		managetest.login();
		
	}

	public void InitDriver() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\Java\\chromedriver\\chromedriver.exe");
		ChromeOptions option = new ChromeOptions();
		option.addArguments("disable-infobars");// 去掉自动软件控制的提示
		driver = new ChromeDriver(option);
		driver.get("http://10.5.19.26:9078/manage/index.html");
		driver.manage().window().maximize();

	}

	public void login() throws Exception {
//		String username = "zhangxiaochun";
//		String userpass = "1qaz@WSX3edc";
//		String validcode = "111111";
//		String userElement = "id";
//		String passElement = "id";
//		String btnElement = "id";
//		String validElement = "id";
		String loginValue = "a.properties";
		String  username = this.propertiesLoad(loginValue,"username");
		String  userpass = this.propertiesLoad(loginValue,"userpass");
		String  validcode = this.propertiesLoad(loginValue,"validcode");
		String  userElement = this.propertiesLoad(loginValue,"userElement");
		String  passElement = this.propertiesLoad(loginValue,"passElement");
		String  btnElement = this.propertiesLoad(loginValue,"btnElement");
		String  validElement = this.propertiesLoad(loginValue,"validElement");


		// 输入用户名
//		WebElement user = driver.findElement(By.id("userId"));
//		user.clear();
//		user.sendKeys("zhangxiaochun");
//		WebElement user= driver.findElement(this.byStr(userElement, "userId"));
		WebElement user= this.element(this.byStr(userElement, "userId"));
		user.sendKeys(username);
		
		// 输入密码
//		WebElement password = driver.findElement(By.id("password"));
//		password.clear();
//		password.sendKeys("1qaz@WSX3edc");
		driver.findElement(this.byStr(passElement, "password")).sendKeys(userpass);
		// 点击获取验证码
//		WebElement btnSendCode = driver.findElement(By.id("btnSendCode"));
//		btnSendCode.click();
		driver.findElement(this.byStr(btnElement, "btnSendCode")).click();
		// 输入短信验证码
		WebElement smsCode = driver.findElement(By.id("smsCode"));
		smsCode.sendKeys("111111");
		driver.findElement(this.byStr(validElement, "smsCode")).sendKeys(validcode);

		try {
			Thread.sleep(12000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 等待10s输入图形验证码
		WebElement submit = driver.findElement(By
				.xpath("//button[@class='btn btn-primary btn-block']"));
		submit.click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WebElement userName = driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-user i-binding']"));
		if (userName.getText().contains("张晓春")) {
			System.out.println("login success");
		} else {
			System.out.println("login fail");
		}
	}
//	封装By
	public By byStr(String by,String local){
		if(by.equals("id")){
			return By.id(local);
		}else if (by.equals("name")){
			return By.name(local);
		}else if (by.equals("className")){
			return By.className(local);
		}
		return By.xpath(local);
	}
//	封装element
	public WebElement element(By by){
	WebElement ele =driver.findElement(by);
	return ele;
	}
	
//	读取配置文件
	public  String propertiesLoad(String loginValue,String key) throws Exception{
		Properties prop = new Properties();
		FileInputStream fout = new FileInputStream(loginValue);
		BufferedInputStream bout = new BufferedInputStream(fout);
		prop.load(bout);
		System.out.println(key+"="+prop.getProperty(key));
		return prop.getProperty(key);
	}
}
