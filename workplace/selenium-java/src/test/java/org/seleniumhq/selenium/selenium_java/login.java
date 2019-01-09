package org.seleniumhq.selenium.selenium_java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class login {
	WebDriver driver;
	public void InitDriver() {
//		System.setProperty("webdriver.chrome.driver", "//Users//jiubugaosuni//workspace//javaSelenium//chromedriver");//MAC
		System.setProperty("webdriver.chrome.driver","D:\\Java\\chromedriver\\chromedriver.exe");//windows
		driver = new ChromeDriver();
		driver.get("https://www.imooc.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("js-signin-btn")).click();

	}
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
	
	public void loginScript() {
		this.InitDriver();
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
		
		
		sleep(1000);
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
		
		//鼠标移动到头像，检查是否登录成功
		sleep(3000);
		WebElement header = driver.findElement(this.byStr(headerBy, headerElement));
		header.isDisplayed();
		
		Actions actions = new Actions(driver);
		actions.moveToElement(header).perform();
		
		String userInfo = driver.findElement(this.byStr(userInfoBy, userInfoElement)).getText();
		System.out.println(userInfo);
		System.out.println("登录成功");
		
	}
	
	
	public static void main(String[] args) {
		login logins = new login();
//		logins.InitDriver();
		logins.loginScript();

		}
	}
	
