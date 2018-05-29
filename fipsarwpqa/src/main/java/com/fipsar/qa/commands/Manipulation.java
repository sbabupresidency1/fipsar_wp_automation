package com.fipsar.qa.commands;

import java.awt.AWTException;
import net.sourceforge.tess4j.*;
import javax.swing.JOptionPane;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.comparator.DirectoryFileComparator;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.html5.AddApplicationCache;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.IScreen;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.Assert;

import com.fipsar.qa.FipsarReports;
import com.fipsar.qa.enums.LogAs;
import com.fipsar.qa.util.CommandUtils;
import com.fipsar.qa.utils.Directory;
import com.fipsar.qa.utils.Platform;
import com.fipsar.qa.writers.ReportsPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

public class Manipulation extends CommandUtils implements OR {

	static Logger log = Logger.getLogger(Manipulation.class.getName());
	public static String ElementWait=Directory.WaitFor;
	public static int WaitElementSeconds=new Integer(ElementWait);

	public static void click(WebElement webElement) {
		try {
			if(webElement.isDisplayed()) {
				webElement.click();
			}
		}
		catch (StaleElementReferenceException e){ }
	}

	public static void jsClickByXPath(WebDriver driver,String NormalXpath) {
		WebElement element = driver.findElement(By.xpath(NormalXpath));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	public static void jsTypeByXPath(WebDriver driver,String NormalXpath, String InputData) {
		WebElement element = driver.findElement(By.xpath(NormalXpath));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		//JavascriptExecutor jse = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].type ='"+InputData+"';",element);
	}
	//	public static void actionClick(WebDriver driver, WebElement webElement) {
	//		try {
	//			if(webElement.isEnabled()) {
	//				Actions action = new Actions(driver);
	//				action.click(webElement).build().perform();
	//			}
	//		} catch (StaleElementReferenceException e) { }
	//	}
	public static void actionClick(WebDriver driver, WebElement webElement) {
		try {
			if(webElement.isEnabled()) {
				//Actions action = new Actions(driver);
				//action.click(webElement).build().perform();
				webElement.click();
			}
		} catch (StaleElementReferenceException e) { }
	}

	public static void actionType(WebDriver driver,WebElement webElement,CharSequence... keysToSend){
		try {
			if(webElement.isEnabled())
			{
				//Actions action = new Actions(driver);
				//action.sendKeys(webElement, keysToSend).build().perform();
				webElement.sendKeys(keysToSend);
			}
		} catch (StaleElementReferenceException e) { }
	}

	//	public static void actionType(WebDriver driver,WebElement webElement,CharSequence... keysToSend){
	//		try {
	//			if(webElement.isEnabled())
	//			{
	//				Actions action = new Actions(driver);
	//				action.sendKeys(webElement, keysToSend).build().perform();
	//			}
	//		} catch (StaleElementReferenceException e) { }
	//	}

	public static void doubleClick(WebDriver driver, WebElement webElement) {
		try {
			Actions action = new Actions(driver).doubleClick(webElement);
			action.build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not clickable "
					+ e.getStackTrace());
		}
	}

	public static void clickAt(WebDriver driver, WebElement webElement, int x,int y) {
		try {
			System.out.println("Browser is " + Directory.browser);
			Robot robot = new Robot();
			robot.mouseMove(x,y);
			wait(driver, "3");
			robot.mousePress(InputEvent.BUTTON1_MASK);
			wait(driver, "1");
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			wait(driver, "1");

		} catch (Exception e) {
			log.info("Could not click " + e.getStackTrace());
		}

	}
	public static void clickUnavailabilityRedArea(WebDriver driver, WebElement webElement, int x,int y)
	{
		String OSName=Platform.OS.toUpperCase();
		if(OSName.contains("WINDOWS"))
		{
			try {
				System.out.println("Browser is " + Directory.browser);
				Robot robot = new Robot();
				robot.mouseMove(x,y);
				wait(driver, "3");
				robot.mousePress(InputEvent.BUTTON1_MASK);
				wait(driver, "1");
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				wait(driver, "1");

			} catch (Exception e) {
				log.info("Could not click " + e.getStackTrace());
			}
		}
		else if(OSName.contains("MAC"))
		{
			if((Directory.browser).equalsIgnoreCase("chrome"))
			{
				Navigate.pageDown(driver);
				Robot robot;
				try {
					robot = new Robot();
					System.out.println("eeeentereed");
					robot.mousePress(InputEvent.BUTTON1_MASK);
					robot.mouseRelease(InputEvent.BUTTON1_MASK);
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Screen screen = new Screen();
				Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
				wait(driver, "5");

				try {
					screen.wait(logo, 7000);
					screen.click(logo);
					screen.wait(logo, 7000);}
				catch (FindFailed e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}}
			else
			{
				Navigate.pageDown(driver);
				Robot robot;

				Screen screen = new Screen();
				Pattern logo = new Pattern(Directory.uploadFilePath+"Capture7.JPG");
				wait(driver, "5");

				try {
					screen.wait(logo, 7000);
					screen.click(logo);
					screen.wait(logo, 7000);}
				catch(Exception e4)
				{

				}
			}

		}
	}


	public static void clickAndHold(WebDriver driver,WebElement webElement)	{
		try {
			Actions builder = new Actions(driver);
			builder.clickAndHold(webElement).build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not clickable "
					+ e.getStackTrace());
		}
	}

	public static void clear(WebElement webElement) {
		webElement.clear();

	}
	public static void actionClear(WebDriver driver,WebElement webElement) {
		webElement.click();
		Actions action = new Actions(driver);
		webElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), "55");
		action.sendKeys(Keys.DELETE);


	}

	public static String clearAndType(WebElement webElement, String input) {
		webElement.clear();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		webElement.sendKeys(input);
		return input;
	}

	public static String sendKeys(WebElement webElement,String keysToSend) {
		webElement.sendKeys(keysToSend);
		return keysToSend;
	}

	public static void submit(WebElement webElement) {
		webElement.submit();
	}

	public static void keyDown(Actions actions, Keys keys) {
		actions.keyDown(keys);
	}

	public static void keyUp(Actions actions, Keys keys) {
		actions.keyUp(keys);
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static void mouseOver(WebDriver driver, WebElement webElement) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not mouseOver "
					+ e.getStackTrace());
		}
	}

	public static void mouseOverAndClick(WebDriver driver, WebElement webElement) {
		try {
			Actions builder = new Actions(driver);
			builder.moveToElement(webElement).click().build().perform();
		} catch (StaleElementReferenceException e) {
			log.info("Element is not attached to the page document "
					+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			log.info("Element " + webElement + " was not found in DOM "
					+ e.getStackTrace());
		} catch (Exception e) {
			log.info("Element " + webElement + " was not mouseOver "
					+ e.getStackTrace());
		}
	}

	public static void selectCheckBox(WebElement element) {
		try {
			if (element.isSelected()) {
				log.info("Checkbox: " + element + "is already selected");
			} else {
				element.click();
			}
		} catch (Exception e) {
			log.info("Unable to select the checkbox: " + element);
		}
	}

	public static void deSelectCheckBox(WebElement element) {
		try {
			if (element.isSelected()) {
				element.click();
			} else {
				log.info("Checkbox: " + element + "is already deselected");
			}
		} catch (Exception e) {
			log.info("Unable to deselect checkbox: " + element);
		}
	}

	public static void selectByIndex(WebElement element, String inputData) {
		try {
			Integer index = new Integer(inputData);
			Select selectBox = new Select(element);
			selectBox.selectByIndex(index);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void selectByValue(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.selectByValue(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static String selectByVisibletext(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.selectByVisibleText(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
		return inputData;
	}

	public static void deSelectByVisibletext(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.deselectByVisibleText(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void deSelectByIndex(WebElement element, String inputData) {
		try {
			Integer index = new Integer(inputData);
			Select selectBox = new Select(element);
			selectBox.deselectByIndex(index);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static void deSelectByValue(WebElement element, String inputData) {
		try {
			Select selectBox = new Select(element);
			selectBox.deselectByValue(inputData);
		} catch (Exception e) {
			log.info("Unable to select selectbox: " + element);
		}
	}

	public static String Main_Window = "";
	public static void getWindow(WebDriver driver, WebElement webElement)
	{
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		click(webElement);
		wait(driver, "3");

		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");

		driver.switchTo().window(tabs2.get(1));
		wait(driver, "3");
		System.out.println("Entered tabs");
		wait(driver, "3");

		/*try{
			com.zillion.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		catch (Exception e)
		{

		}	*/
	}

	public static void getWindowUsingJsclick(WebDriver driver, String xpath) {
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		jsClickByXPath(driver, xpath);
		wait(driver, "2");
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);
		driver.switchTo().window(allWindows.get(0));
		if (Platform.BROWSER_NAME.equalsIgnoreCase("UnKnown"))
		{
			try
			{
				if(driver.findElement(By.xpath("//a[@id='overridelink']")).isDisplayed()){
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			}
			catch(Exception e)
			{

			}
		}
		else
		{

		}

	}

	public static String Main_Window1 ="";
	public static void getwindowandclose(WebDriver driver, WebElement webElement) {
		Navigate.waitTime(driver, "5");
		Main_Window1 = driver.getWindowHandle();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		wait(driver, "3");
		click(webElement);
		wait(driver, "2");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<String> allWindows = new ArrayList<String>(
				driver.getWindowHandles());
		allWindows.remove(Main_Window1);
		driver.switchTo().window(allWindows.get(0));
		wait(driver, "3");
		try{
			com.fipsar.qa.commands.Manipulation.browserURLSecurityException(driver);
		}
		catch (Exception e)
		{

		}
		if (Platform.BROWSER_NAME.equalsIgnoreCase("UnKnown")) {
			try {
				if (driver.findElement(By.xpath("//a[@id='overridelink']"))
						.isDisplayed()) {
					driver.get("javascript:document.getElementById('overridelink').click();");

				}
			} catch (Exception e) {

			}
		} else {

		}
		driver.switchTo().window(Main_Window1).close();
		driver.switchTo().window(allWindows.get(0));

	}

	public static void getSecondWindow(WebDriver driver, WebElement webElement) {
		String Second_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", webElement);
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "10");
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No of windows is: "
				+ "" +allWindows.size());
		//Set<String> tab_handles = driver.getWindowHandles();
		allWindows.remove(Main_Window);
		allWindows.remove(Second_Window);
		driver.switchTo().window(allWindows.get(0));
		/*try
		{
			driver.get("javascript:document.getElementById('overridelink').click();");

		}
		catch(Exception e)
		{

		}
		wait(driver, "10");*/
	}

	public static String GetCurrentWindow(WebDriver driver) {
		String CurrentWindow = driver.getWindowHandle();
		return CurrentWindow;
	}

	public static void switchParticularWindow(WebDriver driver,String Window) {
		driver.switchTo().window(Window);
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
	}


	public static void switchWindow(WebDriver driver) {
		driver.switchTo().window(Main_Window);
		try{Thread.sleep(1000);}
		catch(InterruptedException e){e.printStackTrace();}
	}

	public static void switchDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
	}

	public static void getAutoit(WebDriver driver, String inputData) {
		try {
			Runtime.getRuntime().exec(inputData);
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}

	public static void wait(WebDriver driver,String inputData) {
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time*1000;
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static WebElement fromElement;
	public static void dragElement(WebDriver driver, WebElement webElement)	{
		fromElement=webElement;
	}

	public static void dropElement(WebDriver driver, WebElement webElement) {
		Actions action = new Actions(driver);
		Action dragDrop = action.dragAndDrop(fromElement, webElement).build();
		dragDrop.perform();
	}

	public static boolean elementIsSelected(WebDriver driver, WebElement webElement) {
		try {
			webElement.isSelected();
			return true;
		} catch(NoSuchElementException e) {
			log.info("Unable to Select WebElement: " + webElement);
			return false;
		}
	}

	public static boolean verifyElementIsPresent(WebDriver driver, WebElement webElement){
		try {
			webElement.isDisplayed();
			return true;
		} catch(NoSuchElementException e)   {
			log.info("Unable to Displayed WebElement: " + webElement);
			return false;
		}
	}

	public static String verifyElementIsnotdisplayed(WebDriver driver, String webElement){
		String Result="";
		try {
			if(driver.findElement(By.xpath(webElement)).isDisplayed())
			{
				Result="Element is present";
			}
			else
			{
				Result="Element is Not present";
			}

		} catch(Exception e)   {
			Result="Element is Not present";
		}

		return Result;
	}


	public static String verifyElementIsNotPresent(WebDriver driver, WebElement webElement){
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
			wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(webElement)));
		}
		catch(Exception e)
		{
			return "Verified Element is Hidden";
		}
		return "Verified Element is Visible";
	}

	public static boolean elementIsEnable(WebDriver driver, WebElement webElement){
		try {
			webElement.isEnabled();
			return true;
		} catch(NoSuchElementException e)  {
			log.info("Unable to Enabled WebElement: " + webElement);
			return false;
		}
	}

	public static void visibilityElement(WebDriver driver, WebElement webElement){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public static void inVisibilityElement(WebDriver driver, String NormalXpath){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(NormalXpath)));
	}

	public static void testIsPresent(WebDriver driver,WebElement webElement, String inputData){
		driver.getPageSource().contains(inputData);
	}

	public static void textTobePresent(WebDriver driver,WebElement webElement, String inputData){
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.textToBePresentInElement(webElement, inputData));
	}

	public static void testIsNotPresent(WebDriver driver, String NormalXpath,String inputData){
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath(NormalXpath), inputData));
	}

	public static void elementTobeClickable(WebDriver driver,WebElement webElement)	{
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public static void elementToBeSelected(WebDriver driver,WebElement webElement)	{
		WebDriverWait waits = new WebDriverWait(driver, WaitElementSeconds);
		waits.until(ExpectedConditions.elementToBeSelected(webElement));
	}

	public static void waitForElement(WebDriver driver, String NormalXpath)	{
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(NormalXpath)));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NormalXpath)));
	}

	public static void waitForElementNotpresent(WebDriver driver,String NormalXpath) {
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(By.xpath(NormalXpath))));
	}

	public static void textElementPresentValue(WebDriver driver,WebElement webElement, String inputData){
		WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds);
		wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, inputData));
	}

	public static String linkCounts(WebDriver driver, String NormalXpath){
		int count = driver.findElements(By.xpath(NormalXpath)).size();
		String elementCounts = String.valueOf(count);
		return elementCounts;
	}

	/**
	 * Description :
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 */
	public static String dynamicSendkeys(WebDriver driver,String inputData, WebElement webElement){
		webElement.clear();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		String currenttime = new SimpleDateFormat("EHHmmss").format(Calendar.getInstance().getTime());

		String generatedString = RandomStringUtils.randomAlphabetic(8);
		String combinedValues1 = generatedString+currenttime+inputData;
		sendKeys(webElement, combinedValues1);
		return combinedValues1;
	}

	/**
	 * Description :
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 */
	public static String dynamicAlphabetic(WebDriver driver,String inputData, WebElement webElement){
		webElement.clear();
		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		//	String currenttime = new SimpleDateFormat("EHHmmss").format(Calendar.getInstance().getTime());
		String originalValue = inputData;

		String generatedString = RandomStringUtils.randomAlphabetic(10);
		String combinedValues = generatedString+originalValue;
		sendKeys(webElement, combinedValues);

		//System.out.println(combinedValues);
		return combinedValues;
	}



	public static void waitForAjax(WebDriver driver) {
		new WebDriverWait(driver, 180).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) 	{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				return (Boolean) js.executeScript("return jQuery.active == 0");
			}
		});
	}

	public static String condtionMatch(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		try	{
			if((GetText1.trim()).equalsIgnoreCase(GetText2.trim()))	{
				output = "The value "+GetText2+" is Verified";
				return output;
			}
			else
			{
				output = "The value is not matched";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value "+GetText2+" is not Matched";
			return output;
		}
	}
	public static String condtionNotMatch(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		try	{
			//if((GetText1.trim())!=(GetText2.trim()))
			if(!GetText1.trim().equalsIgnoreCase(GetText2.trim()))
			{
				output = "The value is not matched";
				return output;
			}
			else
			{
				output = "The value"+GetText2+" is Verified";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value"+GetText2+" is not Matched";
			return output;
		}
	}

	/**
	 * Name : Abinaya.P
	 * Created Date:12/30/2015
	 * Modified Date:
	 * Description : Image or File Upload using Robot
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 * @throws AWTException
	 */
	public static void fileUploadRobot(WebDriver driver, String inputData) throws AWTException {

		StringSelection ss = new StringSelection(inputData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}


	/*
	 * Description : RightClick MailDrop Generated link
	 *
	 */
	public static void rightClickMailDrop(WebDriver driver, WebElement webElement) {
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		Actions newwin = new Actions(driver);
		newwin.keyDown(Keys.SHIFT).click(webElement).keyUp(Keys.SHIFT).build().perform();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		ArrayList<String> allWindows=new ArrayList<String>(driver.getWindowHandles());
		allWindows.remove(Main_Window);
		driver.switchTo().window(allWindows.get(0));
	}
	/**
	 * Name :Abinaya.P
	 * Created Date:02/Feb/2016
	 * Modified Date:
	 * Description : Split number and character
	 *
	 */
	public static String separateDigitsAndAlphabets(WebDriver driver,String inputData) {
		String number = "";
		String letter = "";
		for (int i = 0; i < inputData.length(); i++) {
			char a = inputData.charAt(i);
			if (Character.isDigit(a)) {
				number = number + a;

			} else {
				letter = letter + a;

			}
		}
		return number;

	}

	public static String sumOfTwoString(String GetText1, String GetText2)
	{
		System.out.println("Object is Before :" +GetText1);
		System.out.println("Object to After : "+GetText2);
		int string1= Integer.parseInt(GetText1);
		int string2= Integer.parseInt(GetText2);
		int sum1= string1+string2;
		String sum= Integer.toString(sum1);
		System.out.println("Sum of two strings"+sum);
		return sum;
	}
	public static String getSelectedFirstElementOfSelectBox(WebElement element) {
		Select dropdown= new Select(element);
		WebElement option = dropdown.getFirstSelectedOption();
		String text=option.getText();
		return text;
	}
	public static String checkPartialText(String GetText1, String GetText2){
		String output = "";
		System.out.println("Object is Before :" +GetText1.trim().toLowerCase());
		System.out.println("Object to After : "+GetText2);
		try	{
			if((GetText1.trim()).toLowerCase().contains(GetText2.trim().toLowerCase())||(GetText2.trim()).toLowerCase().contains(GetText1.trim().toLowerCase()))	{
				output = "The value "+GetText2+" is Verified";
				return output;
			}
			else
			{
				output = "The value is not matched";
				Assert.fail();
				return output;
			}
		} catch(NoSuchElementException e)
		{
			log.info("Unable to Matched WebElement: " + output);
			output = "The value "+GetText2+" is not Matched";
			return output;
		}
	}
	public static void clickUsingSize(WebDriver driver,String NormalXpath) {
		//WebElement element = driver.findElement(By.xpath(NormalXpath));
		int size =driver.findElements(By.xpath(NormalXpath)).size();
		driver.findElements(By.xpath(NormalXpath)).get(size-1).click();
	}

	public static String stringIsEmpty(String refSte2)
	{
		if (refSte2.equalsIgnoreCase(" "))
		{
			System.out.println("The value is Empty");
		}
		else
		{
			System.out.println("The value is not Empty");
			Assert.fail();

		}
		return refSte2;

	}

	public static void browserURLSecurityException(WebDriver driver)

	{
		final String osName = Platform.OS.toUpperCase();
		Manipulation.wait(driver, "2");
		try {
			driver.findElement(By.xpath("//*[text()='Advanced']")).click();
			Manipulation.wait(driver, "2");
			driver.findElement(By.xpath("//*[text()='Add Exception…']")).click();

			if (osName.contains("WINDOWS")) {
				Manipulation.wait(driver, "1");
				Robot robot5 = null;
				try {
					robot5 = new Robot();
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_TAB);
				Manipulation.wait(driver, "1");
				robot5.keyPress(KeyEvent.VK_ENTER);
				Manipulation.wait(driver, "5");
				Manipulation.wait(driver, "8");
			}

			else if (osName.contains("MAC")) {
				Robot ss;
				try {
					ss = new Robot();
					ss.mousePress(InputEvent.BUTTON1_MASK);
					ss.mouseRelease(InputEvent.BUTTON1_MASK);
					Manipulation.wait(driver, "1");
					ss.keyPress(KeyEvent.VK_ENTER);
					ss.keyRelease(KeyEvent.VK_ENTER);
					Manipulation.wait(driver, "1");
					ss.keyPress(KeyEvent.VK_CONTROL);
					System.out.println("Entered");
					ss.keyPress(KeyEvent.VK_C);
					ss.keyRelease(KeyEvent.VK_C);
					ss.keyRelease(KeyEvent.VK_CONTROL);
					Manipulation.wait(driver, "1");
				} catch (AWTException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (Exception ex) {

		}
	}

	public static void getWindow1(WebDriver driver, WebElement webElement)
	{
		Navigate.waitTime(driver, "5");
		Main_Window = driver.getWindowHandle();
		try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
		wait(driver, "3");
		click(webElement);
		wait(driver, "15");

		ArrayList<String> tabs2=new ArrayList<String>(driver.getWindowHandles());
		wait(driver, "3");

		driver.switchTo().window(tabs2.get(2));
		wait(driver, "3");
		System.out.println("Entered tabs");
		wait(driver, "3");
	}
	/**
	 * Description : Image or File Upload using Robot
	 * Ticket ID :
	 * Required Inputs :
	 * Purpose :
	 * @throws AWTException
	 */
	public static void coachFileuploadrobot(WebDriver driver, String inputData) throws AWTException {

		StringSelection ss = new StringSelection(inputData);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		wait(driver, "2");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		wait(driver, "2");

	}
	public static String dynamickeys(WebDriver driver){

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		String currenttime = new SimpleDateFormat("yyyy"+"-"+"MM"+"-"+"dd").format(Calendar.getInstance().getTime());
		String combinedValues = currenttime;
		System.out.println(combinedValues);
		return combinedValues;
	}

	/**
	 * Description :   Create a common method for get Attribute value
	 */
	public  static String getAttribute(WebDriver driver,WebElement element)
	{
		//WebElement Email_ID= driver.findElement(By.xpath(INSUFFICIENT_PROGRAM_MEMBER_PROFILE_EMAIL));
		String Member_Email_ID=element.getAttribute("value");
		return Member_Email_ID;
	}

	public static void typeupload(WebElement element, String inputData) {
		String inputdata=Directory.uploadFilePath+inputData+".PNG";		
		element.sendKeys(inputdata);				
	}

	public static void sikuliClick(String inputData) throws FindFailed, InterruptedException {
		Screen s=new Screen();	
		String inputdata=Directory.uploadFilePath+inputData+".PNG";
		System.out.println("inputdata="+inputdata);
		s.mouseMove(inputdata);
		s.doubleClick(inputdata);
		Thread.sleep(2000);		
	}
	public static String tenDigit(WebDriver driver, WebElement element){
		// initialize a Random object somewhere; you should only need one
		Random random = new Random();

		// generate a random integer from 0 to 899, then add 100
		int x = random.nextInt(900) + 100;            String ssn_1 = Integer.toString(x); System.out.println(x);
		int y = random.nextInt(10) + 90; String ssn_2 = Integer.toString(y);System.out.println(y);
		int z = random.nextInt(90000) + 10000;        String ssn_3 = Integer.toString(z);System.out.println(z);
		String TEN=ssn_1+ssn_2+ssn_3;		 System.out.println(TEN);
		element.clear();
		Manipulation.sendKeys(element, TEN);	                
		Manipulation.wait(driver, "1");
		return TEN;
	}
	public static void dobMarch(WebDriver driver, WebElement element){

		element.click();
		WebElement el1=driver.findElement(By.xpath("//div[@data-label='dob']//th[2]"));
		Manipulation.click(el1);
		WebElement el2=driver.findElement(By.xpath("//div[@data-label='dob']//div[@class='rdtMonths']//td[text()='Mar']"));
		Manipulation.click(el2);
		WebElement el3=driver.findElement(By.xpath("//div[@class='rdtPicker']//td[text()='1']"));
		Manipulation.click(el3);
	}

}

