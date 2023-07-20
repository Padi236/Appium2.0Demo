package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebElement;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;
import static com.test.BaseClass.doubleTapElement;

public class AndroidTapTest {
	
				
	@Test
	public void doubletapTest() throws MalformedURLException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(MobilePlatform.ANDROID);	//optional
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);	//optional
		
		options.setDeviceName("Pixel 6 Pro API 31");
		options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
	
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
		WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
		
		doubleTapElement(driver, openMenu);
		
		driver.quit();
	
	}
	
	
	
	
}
