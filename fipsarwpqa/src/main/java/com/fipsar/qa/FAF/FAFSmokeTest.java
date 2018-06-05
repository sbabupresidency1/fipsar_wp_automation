package com.fipsar.qa.FAF;

import java.io.File;
import com.fipsar.qa.CPR.*;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fipsar.qa.utils.Directory;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FAFSmokeTest{

	public  static void FAF_Smoke_Test(WebDriver driver) throws InterruptedException, IOException
	{
		String fileName = Directory.uploadFilePath+"PAF_Inputs.xlsx";
		String sheetName = "FAF";
		System.out.println(fileName);

		File file =    new File(fileName);
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		Sheet sheet = wb.getSheet(sheetName);

		String url = sheet.getRow(1).getCell(4).getStringCellValue();
		String naviget_url = sheet.getRow(1).getCell(5).getStringCellValue();
		driver.manage().window().maximize();
		driver.get(url);
		WebDriverWait wait = new WebDriverWait(driver,30);
		System.out.println("Logged in as - " + url);
		String usrName = sheet.getRow(1).getCell(1).getStringCellValue();
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.USERNAME_FIELD)));
		driver.findElement(By.id(OR.USERNAME_FIELD)).sendKeys(usrName);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PASSWORD_FIELD)));
		driver.findElement(By.id(OR.PASSWORD_FIELD)).sendKeys("rules");

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.SUBMIT_BUTTOM)));
		driver.findElement(By.id(OR.SUBMIT_BUTTOM)).click();

		System.out.println("Signed in with -  " + usrName);
		Thread.sleep(2000);
		driver.navigate().to(naviget_url);
		System.out.println("Logged in as - " + naviget_url);
		Thread.sleep(2000);

		int patientId = (int) sheet.getRow(1).getCell(3).getNumericCellValue();
		String PID = Integer.toString(patientId);

		System.out.println("Entered patient ID - " + PID);
		Thread.sleep(1000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.NEW_LINK)));
		driver.findElement(By.id(OR.NEW_LINK)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.NEW_PHONE_CALL)));
		driver.findElement(By.xpath(OR.NEW_PHONE_CALL)).click();

		WebElement frame1 = driver.findElement(By.id(OR.FRAME_1));
		driver.switchTo().frame(frame1);

		WebElement frame2 = driver.findElement(By.id(OR.FRAME_2));                
		driver.switchTo().frame(frame2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ID_FIELD)));
		driver.findElement(By.id(OR.PATIENT_ID_FIELD)).sendKeys(PID);

		driver.findElement(By.cssSelector(OR.PATIENT_SEARCH_BUTTON)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.PATIENT_RECORD +PID+ OR.PATIENT_RECORD_END)));
		driver.findElement(By.xpath(OR.PATIENT_RECORD +PID+ OR.PATIENT_RECORD_END)).click();

		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.PATIENT_INFO_DOB)));
		driver.findElement(By.xpath(OR.PATIENT_INFO_DOB)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_INFO_SSN)));
		driver.findElement(By.id(OR.PATIENT_INFO_SSN)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_SUBMIT_BUTTON)));
		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();

		driver.switchTo().defaultContent();

		WebElement frame5 = driver.findElement(By.id(OR.FRAME_1)); 
		driver.switchTo().frame(frame5);

		WebElement diaction = driver.findElement(By.id(OR.FRAME_2));              
		driver.switchTo().frame(diaction);

		driver.findElement(By.xpath(OR.WRAPUP_INTENT)).click();
		WebElement wp=driver.findElement(By.xpath(OR.WRAPUP_INTENT));

		Actions builder = new Actions(driver);
		builder.doubleClick(wp).build().perform();

		WebElement WrapupFrame = driver.findElement(By.id(OR.WRAPUP_FRAME));               
		driver.switchTo().frame(WrapupFrame);

		WebElement state= driver.findElement(By.id(OR.WRAPUP_STATE_DROPDOWN));
		Select d1=new Select(state);
		d1.selectByIndex(2);

		WebElement Reason= driver.findElement(By.id(OR.WRAPUP_REASON_DROPDOWN));
		Select d2=new Select(Reason);
		d2.selectByIndex(2);

		driver.findElement(By.id(OR.WRAPUP_NOTES)).sendKeys("testing");
		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();

		driver.switchTo().defaultContent();		
		//driver.manage().deleteAllCookies();
	}
}
