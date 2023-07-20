package com.test;

import static com.test.BaseClass.getCenterOfElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobilePlatform;

public class AndroidDragAndDropTest {
	
	@Test(priority=1)
	public void dragAndDropTest() throws MalformedURLException
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
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		
		//(//*[@class='android.view.View'])[2]  
		WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));
		
		Point sourceElementCenter = getCenterOfElement(source.getLocation(),source.getSize());
		Point targetElementCenter = getCenterOfElement(target.getLocation(),target.getSize());
		
		PointerInput rightThumb = new PointerInput(Kind.TOUCH, "rightThumb"); 		
		
		Sequence sequence = new Sequence(rightThumb, 1)
				.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), sourceElementCenter))
				.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(rightThumb,Duration.ofMillis(500)))
				.addAction(rightThumb.createPointerMove(Duration.ofMillis(500), Origin.viewport(), targetElementCenter))
				.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Arrays.asList(sequence));
		
		System.out.println(driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_text")).getText());
		
	}
	
	@Test
	public void dragDropTestOG() throws MalformedURLException
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
		driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
		
		WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
		WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));
		
		
		

	}
	
}
