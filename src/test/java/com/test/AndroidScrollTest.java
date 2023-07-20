package com.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;

public class AndroidScrollTest {

	
	@Test(priority=1)
	public void scrollDownTest() throws MalformedURLException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(MobilePlatform.ANDROID);	//optional
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);	//optional		
		options.setDeviceName("Pixel 6 Pro API 31");		
		options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");
		options.autoGrantPermissions();
			
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
				
		driver.findElement(AppiumBy.xpath("//*[@class='android.widget.Button']")).click();
		driver.findElement(AppiumBy.accessibilityId("Views")).click();		
	
		Dimension windowSize = driver.manage().window().getSize();
		int startX = windowSize.getWidth()/2;
		int startY = windowSize.getHeight()/2;
		
		int endY = windowSize.getHeight()/4;
		int endX = startX;
		
		PointerInput rightThumb = new PointerInput(Kind.TOUCH, "rightThumb");
		Sequence sequence = new Sequence(rightThumb, 1)
							.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), startX,startY))
							.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(rightThumb, Duration.ofMillis(300)))
							.addAction(rightThumb.createPointerMove(Duration.ofMillis(300), Origin.viewport(), endX,endY))
							.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));		
		driver.perform(Arrays.asList(sequence));
				
		int endY1 = (int)(windowSize.getHeight() * 0.75);
		int endX1 = startX;
		PointerInput rightThumb1 = new PointerInput(Kind.TOUCH, "rightThumb1");
		Sequence sequence1 = new Sequence(rightThumb1, 1)
							.addAction(rightThumb1.createPointerMove(Duration.ZERO, Origin.viewport(), endX,endY))
				
							.addAction(rightThumb1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
							.addAction(new Pause(rightThumb1, Duration.ofMillis(300)))
							.addAction(rightThumb1.createPointerMove(Duration.ofMillis(300), Origin.viewport(), endX1,endY1))
							.addAction(rightThumb1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));		
		driver.perform(Arrays.asList(sequence1));
			
		//driver.quit();		
	}
}
