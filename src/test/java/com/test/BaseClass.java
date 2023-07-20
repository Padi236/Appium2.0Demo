package com.test;

import java.time.Duration;
import java.util.Collections;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Origin;

import io.appium.java_client.android.AndroidDriver;

public class BaseClass {
	
	/* List of Methods
	 * 
	 * tapElement
	 * doubleTapElement
	 * getCenterOfElement
	 * longPress
	 * 
	 * All kinds of wait(Implicit, Explicit) are handled by <element-wait> plugin on server side
	 * Enable this plugin while starting appium server
	 * <appium server --use-plugins=device-farm,images,element-wait>
	 */
	
	public static void tapElement(AndroidDriver driver, WebElement element)
	{
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location,size);
		
		PointerInput rightThumb = new PointerInput(PointerInput.Kind.TOUCH, "rightThumb");
		Sequence sequence = new Sequence(rightThumb, 1)
			.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), centerOfElement))
			.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause(rightThumb,Duration.ofMillis(200)))
			.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(sequence));
	}
		
	public static void doubleTapElement(AndroidDriver driver, WebElement element)
	{
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location,size);
		
		PointerInput rightThumb = new PointerInput(PointerInput.Kind.TOUCH, "rightThumb");
		Sequence sequence = new Sequence(rightThumb, 1)
			.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), centerOfElement))
			.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause(rightThumb,Duration.ofMillis(200)))
			.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause(rightThumb,Duration.ofMillis(200)))
			.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause(rightThumb,Duration.ofMillis(200)))
			.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(sequence));
	}
	
	public static Point getCenterOfElement(Point location, Dimension size)
	{
		return new Point(location.getX()+size.getWidth()/2, location.getY()+size.getHeight()/2);
	}
	
	public static void longPress(AndroidDriver driver, WebElement element)
	{
		Point location = element.getLocation();
		Dimension size = element.getSize();
		
		Point centerOfElement = getCenterOfElement(location,size);
		
		PointerInput rightThumb = new PointerInput(PointerInput.Kind.TOUCH, "rightThumb");
		Sequence sequence = new Sequence(rightThumb, 1)
			.addAction(rightThumb.createPointerMove(Duration.ZERO, Origin.viewport(), centerOfElement))
			.addAction(rightThumb.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
			.addAction(new Pause(rightThumb,Duration.ofSeconds(3)))
			.addAction(rightThumb.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		
		driver.perform(Collections.singletonList(sequence));
	}
	
	public static Point getPointOnCircle (int step, int totalSteps, Point origin, double radius) {
	    double theta = 2 * Math.PI * ((double)step / totalSteps);
	    int x = (int)Math.floor(Math.cos(theta) * radius);
	    int y = (int)Math.floor(Math.sin(theta) * radius);
	    return new Point(origin.x + x, origin.y + y);
	}
	
}
