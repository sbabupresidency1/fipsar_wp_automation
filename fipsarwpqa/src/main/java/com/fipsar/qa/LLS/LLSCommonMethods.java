package com.fipsar.qa.LLS;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fipsar.qa.CPR.OR;
import com.fipsar.qa.util.CommandUtils;
import com.fipsar.qa.utils.Directory;

public class LLSCommonMethods extends CommandUtils {
	static WebDriverWait wait;
	public static String generatedString = RandomStringUtils.randomAlphabetic(10);
	
	public static void lls_Login(WebDriver driver) throws InterruptedException
	{
		wait = new WebDriverWait(driver,30);
		//		Get CPR URL
		driver.get(Directory.lls_qa_url);
		driver.manage().window().maximize();
		//		Username field
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.USERNAME_FIELD)));
		driver.findElement(By.id(OR.USERNAME_FIELD)).sendKeys(Directory.lls_qa_username);

		//		Password field
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PASSWORD_FIELD)));
		driver.findElement(By.id(OR.PASSWORD_FIELD)).sendKeys(Directory.lls_qa_password);

		//		Click submit button
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.SUBMIT_BUTTOM)));
		driver.findElement(By.id(OR.SUBMIT_BUTTOM)).click();

		System.out.println("Signed in with -  " + Directory.lls_qa_username);
		Thread.sleep(2000);

		//		Navigate to Executive portal URL
		driver.navigate().to(Directory.lls_qa_navigate_url);
		Thread.sleep(1000);
	}	
	
}
