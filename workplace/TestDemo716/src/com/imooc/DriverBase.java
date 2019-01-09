package com.imooc;

import org.openqa.selenium.WebDriver;

public class DriverBase {
	public WebDriver driver;
	public DriverBase(String browser){
		SelectDriver selectDriver = new SelectDriver();
		this.driver = selectDriver.driverName("chrome");
	}


	public void startBrowser(){
		driver.get("http://10.5.19.26:9078/manage/");
	}

}
