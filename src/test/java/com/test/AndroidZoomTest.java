package com.test;

import static com.test.BaseClass.getCenterOfElement;
import static com.test.BaseClass.tapElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
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

public class AndroidZoomTest {

	@Test(enabled=false)
	public void zoomTest() throws MalformedURLException
	{
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName(MobilePlatform.ANDROID);	//optional
		options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);	//optional		
		options.setDeviceName("Pixel 6 Pro API 31");		
		options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");
		options.autoGrantPermissions();
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
		WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));
		
		tapElement(driver,openMenu);
		
		driver.findElement(By.xpath("//android.widget.TextView[@text='Drawing']")).click();
		
		WebElement drawingView = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"drawing screen\"]"));
		
		Point centerOfDrawingView = getCenterOfElement(drawingView.getLocation(), drawingView.getSize());
		System.out.println(drawingView.getLocation());
		
		PointerInput rightThumb = new PointerInput(Kind.TOUCH, "rightThumb"); 		
		
		Sequence sequence1 = new Sequence(rightThumb, 1)
				.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), centerOfDrawingView))
				.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(rightThumb,Duration.ofMillis(200)))
				.addAction(rightThumb.createPointerMove(Duration.ofMillis(200), Origin.viewport(), centerOfDrawingView.getX()-200,centerOfDrawingView.getY()+100));
		
		PointerInput rightIndexFinger = new PointerInput(Kind.TOUCH, "rightIndexFinger"); 
		
		Sequence sequence2 = new Sequence(rightIndexFinger, 1)
				.addAction(rightIndexFinger.createPointerMove(Duration.ZERO, Origin.viewport(), centerOfDrawingView))
				.addAction(rightIndexFinger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(rightIndexFinger,Duration.ofMillis(200)))
				.addAction(rightIndexFinger.createPointerMove(Duration.ofMillis(200), Origin.viewport(), centerOfDrawingView.getX()+200,centerOfDrawingView.getY()-100));
	
		driver.perform(Arrays.asList(sequence1,sequence2));
		
	}
	
	
}
