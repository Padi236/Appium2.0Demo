package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;

import static com.test.BaseClass.longPress;;



public class AndoridAppLaunchTest {

	@Test(enabled=false)
	public void androidLaunchTestAllActionApp() throws MalformedURLException, InterruptedException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(MobilePlatform.ANDROID);	//optional
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);	//optional		
		options.setDeviceName("Pixel 6 Pro API 31");		
		options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");
		options.autoGrantPermissions();
		
	
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
		
		//driver.findElement(AppiumBy.xpath("(//*[@class='android.widget.Button'])[2]")).click();
		driver.findElement(AppiumBy.xpath("//*[@class='android.widget.Button']")).click();
		//Thread.sleep(10);
		driver.findElement(AppiumBy.accessibilityId("Views")).click();		
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Custom Adapter')]")).click();
		
		WebElement longPressPeopleName = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'People Names')]"));
		
		
		longPress(driver, longPressPeopleName); 
		
		driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Sample menu')]")).click();
		//driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]/android.widget.TextView[@text='Log In']")).click();
		
		//driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("partebhosale");
		//driver.quit();
	
	}
	
	
	
		
	
			
}


