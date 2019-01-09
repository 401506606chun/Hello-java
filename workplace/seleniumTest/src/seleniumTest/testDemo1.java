package seleniumTest;

//import org.openqa.selenium.*;
import org.junit.Assert;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

public class testDemo1 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.firefox.bin","D:\\Mozilla Firefox\\firefox.exe");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://test.kaolazhengxin.com/pcsb/login.html");
		driver.manage().window().maximize();
//		driver.findElement(By.id("username")).sendKeys(new String[] {"201804231353240000000001"});
		//若sendkeys报错，可修改Properties -> Java Compiler，选择1.7版本
		driver.findElement(By.id("username")).sendKeys("201804231353240000000001");
		WebElement password = driver.findElement(By.id("password"));
		password.clear();
		password.sendKeys("1a2b3c4d");
		Thread.sleep(10000);
		WebElement vaidCode = driver.findElement(By.id("btnSendCode"));
		vaidCode.click();
		Thread.sleep(13000);
		WebElement login = driver.findElement(By.xpath("//input[@type='button']"));
		login.click();
		
//		 WebElement money = driver.findElement(By.className("pcs-main-container-title"));
//		 String Str1 = money.getText();
//		//断言登录
//		 Assert.assertEquals(Str1, "当前余额");
	}

}
