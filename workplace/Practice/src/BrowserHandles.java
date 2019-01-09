import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserHandles {
	public String url="https://www.baidu.com/s?ie=UTF-8&wd=caoliao";
	public static WebDriver driver;
	public void firefoxStart(){
		System.setProperty("webdriver.firefox.bin", "D:\\Mozilla Firefox\\firefox.exe" );		
		System.setProperty("webdriver.gecko.driver","D:\\Java\\geckodriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(url);
		System.out.println("打开火狐浏览器了");
	}
	public void chromeStart(){
		System.setProperty("webdriver.chrome.driver","D:\\Java\\chromedriver\\chromedriver.exe");
//		ChromeDriver chrome = new ChromeDriver();
//		chrome.get(url);
		driver = new ChromeDriver();
		driver.get(url);
		System.out.println("打开谷歌浏览器了");
	}
	public void closeBrowser(){
		driver.close();
	}
	
	public int max(int a, int b){
		if(a>=b){
			return a;
		}else{
			return b;
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrowserHandles browser = new BrowserHandles();
		browser.firefoxStart();
		browser.closeBrowser();
		browser.chromeStart();
		browser.closeBrowser();
		
	}

}
	
