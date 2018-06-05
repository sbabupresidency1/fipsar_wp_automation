package com.fipsar.qa.CPR;

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

import com.fipsar.qa.util.CommandUtils;
import com.fipsar.qa.utils.Directory;

public class CPRCommonMethods extends CommandUtils implements OR {
	static WebDriverWait wait;
	public static String generatedString = RandomStringUtils.randomAlphabetic(10);
	
	public static void cpr_Login(WebDriver driver) throws InterruptedException
	{
		wait = new WebDriverWait(driver,30);
		//		Get CPR URL
		driver.get(Directory.cpr_qa_url);
		driver.manage().window().maximize();
		//		Username field
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.USERNAME_FIELD)));
		driver.findElement(By.id(OR.USERNAME_FIELD)).sendKeys(Directory.cpr_qa_username);

		//		Password field
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PASSWORD_FIELD)));
		driver.findElement(By.id(OR.PASSWORD_FIELD)).sendKeys(Directory.cpr_qa_password);

		//		Click submit button
		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.SUBMIT_BUTTOM)));
		driver.findElement(By.id(OR.SUBMIT_BUTTOM)).click();

		System.out.println("Signed in with -  " + Directory.cpr_qa_username);
		Thread.sleep(2000);

		//		Navigate to Executive portal URL
		driver.navigate().to(Directory.cpr_qa_navigate_url);
		Thread.sleep(1000);
	}	
	public static void patientCreation(WebDriver driver) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.NEW_LINK)));
		driver.findElement(By.id(OR.NEW_LINK)).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.NEW_PHONE_CALL)));
		driver.findElement(By.xpath(OR.NEW_PHONE_CALL)).click();

		WebElement frame1 = driver.findElement(By.id(OR.FRAME_1));
		driver.switchTo().frame(frame1);

		WebElement frame2 = driver.findElement(By.id(OR.FRAME_2));		
		driver.switchTo().frame(frame2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ID_FIELD)));
		String MID=ssnInput();
		driver.findElement(By.id(OR.PATIENT_ID_FIELD)).sendKeys(MID);

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(OR.PATIENT_SEARCH_BUTTON)));
		driver.findElement(By.cssSelector(OR.PATIENT_SEARCH_BUTTON)).click();

		//Create new patient

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_FIRST_NAME)));		
		driver.findElement(By.id(OR.PATIENT_FIRST_NAME)).sendKeys(generatedString);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_LAST_NAME)));
		driver.findElement(By.id(OR.PATIENT_LAST_NAME)).sendKeys(generatedString);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.PATIENT_DOB_NAME)));
		driver.findElement(By.xpath(OR.PATIENT_DOB_NAME)).sendKeys("4/2/2018");

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_SSN)));
		String ssnInput=ssnInput();
		System.out.println(ssnInput);
		driver.findElement(By.id(OR.PATIENT_SSN)).sendKeys(ssnInput);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ETHNICITY)));
		WebElement utype4= driver.findElement(By.id(OR.PATIENT_ETHNICITY));
		Select d5=new Select(utype4);
		d5.selectByIndex(1);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_VETERAN)));
		WebElement utype5= driver.findElement(By.id(OR.PATIENT_VETERAN));
		Select d6=new Select(utype5);
		d6.selectByIndex(1);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_GENDER)));
		WebElement utype6= driver.findElement(By.id(OR.PATIENT_GENDER));
		Select d7=new Select(utype6);
		d7.selectByIndex(2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_EMP_STATUS)));
		WebElement utype7= driver.findElement(By.id(OR.PATIENT_EMP_STATUS));
		Select d8=new Select(utype7);
		d8.selectByIndex(2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_MARITAL_STATUS)));
		WebElement utype8= driver.findElement(By.id(OR.PATIENT_MARITAL_STATUS));
		Select d9=new Select(utype8);
		d9.selectByIndex(2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_PHN_TYPE)));
		WebElement utype9= driver.findElement(By.id(OR.PATIENT_PHN_TYPE));
		Select d10=new Select(utype9);
		d10.selectByIndex(3);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_PHN_NO)));
		driver.findElement(By.id(OR.PATIENT_PHN_NO)).sendKeys("9876543215");

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ADDLINE1)));
		driver.findElement(By.id(OR.PATIENT_ADDLINE1)).sendKeys(generatedString+"Addline1");

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ADD_CITY)));
		driver.findElement(By.id(OR.PATIENT_ADD_CITY)).sendKeys("city");

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ADD_ZIP)));
		driver.findElement(By.id(OR.PATIENT_ADD_ZIP)).sendKeys("62711");

		Thread.sleep(2000);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ADD_STATE)));
		WebElement utype10= driver.findElement(By.id(OR.PATIENT_ADD_STATE));
		Select d11=new Select(utype10);
		d11.selectByIndex(2);

		wait.until(ExpectedConditions.elementToBeClickable(By.id(OR.PATIENT_ADDLINE1)));
		driver.findElement(By.id(OR.PATIENT_ADDLINE1)).sendKeys(generatedString+"Addressline1");

		driver.findElement(By.xpath(OR.PATIENT_CREATE)).click();
		Thread.sleep(4000);
		System.out.println("Patient Created successfully");
	}
	
	public static void appCreation(WebDriver driver, String inputData) throws InterruptedException {
		driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]")).click();
		Thread.sleep(4000);
		WebElement element = driver.findElement(By.xpath("//div[4]/div/div/div[2]/div/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]"));

		Actions a=new Actions(driver);
		a.moveToElement(element).doubleClick().build().perform();
		Thread.sleep(4000);

		driver.switchTo().defaultContent();

		WebElement frame3 = driver.findElement(By.id(OR.FRAME_1));
		driver.switchTo().frame(frame3);

		WebElement frame4 = driver.findElement(By.id(OR.FRAME_2));		
		driver.switchTo().frame(frame4);

		WebElement frame5 = driver.findElement(By.id(OR.WRAPUP_FRAME));		
		driver.switchTo().frame(frame5);

		WebElement fundname=driver.findElement(By.xpath(OR.APP_FUND_NAME));
		Select d1=new Select(fundname);
		int index = Integer.parseInt(inputData);		
		d1.selectByIndex(index);
		Thread.sleep(4000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_FAMILY_SIZE))); 
		driver.findElement(By.id(OR.APP_FAMILY_SIZE)).sendKeys("2");
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_INCOME)));
		driver.findElement(By.id(OR.APP_INCOME)).sendKeys("1000");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_ANS_TRUE)));
		driver.findElement(By.id(OR.APP_ANS_TRUE)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.APP_PRE_QUAL_QA)));
		driver.findElement(By.name(OR.APP_PRE_QUAL_QA)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.APP_PRE_QUAL_QA1)));
		driver.findElement(By.name(OR.APP_PRE_QUAL_QA1)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.PATIENT_SUBMIT_BUTTON)));
		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();
		Thread.sleep(2000);

		driver.switchTo().defaultContent();
		WebElement frame6=driver.findElement(By.id(OR.FRAME_1));   	
		driver.switchTo().frame(frame6);
		
		WebElement frame7=driver.findElement(By.id(OR.FRAME_2));   	
		driver.switchTo().frame(frame7);
		
		WebElement frame8=driver.findElement(By.id(OR.WRAPUP_FRAME));   	
		driver.switchTo().frame(frame8);
		
		WebElement frame9=driver.findElement(By.name(OR.APP_ACTION_FRAME));   	
		driver.switchTo().frame(frame9);
		
		driver.findElement(By.xpath("//td[@id='newAssignPage.pyAssignActions1']/nobr/button")).click();
		Thread.sleep(1000);
		
		driver.switchTo().defaultContent();

		WebElement frame10=driver.findElement(By.id(OR.FRAME_1));   	
		driver.switchTo().frame(frame10);

		WebElement frame11=driver.findElement(By.id(OR.FRAME_2));   	
		driver.switchTo().frame(frame11);

		WebElement frame12=driver.findElement(By.id(OR.WRAPUP_FRAME));   	
		driver.switchTo().frame(frame12);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_PAT_RESIDENCY)));
		WebElement fundnme=driver.findElement(By.id(OR.APP_PAT_RESIDENCY));
		Select d2=new Select(fundnme);
		d2.selectByIndex(1);
		
		//Authorized person
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.APP_AUTH_PATIENT)));
		driver.findElement(By.xpath(OR.APP_AUTH_PATIENT)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_PAT_SPEAK_BEHALF)));
		WebElement authorizedperson=driver.findElement(By.id(OR.APP_PAT_SPEAK_BEHALF));
		Select d12=new Select(authorizedperson);
		d12.selectByIndex(1);
			
		//Additional info
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.APP_PAT_ADD_INFO)));
		driver.findElement(By.xpath(OR.APP_PAT_ADD_INFO)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_PAT_REF_SOURCE)));
		WebElement addinfo1=driver.findElement(By.id(OR.APP_PAT_REF_SOURCE));
		Select d3=new Select(addinfo1);
		d3.selectByIndex(1);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_OTHER_FUND_ASSI)));
		WebElement addinfo2=driver.findElement(By.id(OR.APP_OTHER_FUND_ASSI));
		Select d4=new Select(addinfo2);
		d4.selectByIndex(1);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_MANU_DRUG_PROGRAM)));
		WebElement addinfo3=driver.findElement(By.id(OR.APP_MANU_DRUG_PROGRAM));
		Select d13=new Select(addinfo3);
		d13.selectByIndex(1);
		
		//Tax return

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.APP_TAX_RETURN)));
		driver.findElement(By.xpath(OR.APP_TAX_RETURN)).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_FILE_TAX_RETURN)));
		WebElement taxreturn=driver.findElement(By.id(OR.APP_FILE_TAX_RETURN));
		Select d14=new Select(taxreturn);
		d14.selectByIndex(2);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.APP_HAS_ANNUAL_INCOME_CHA)));
		WebElement incomechange=driver.findElement(By.id(OR.APP_HAS_ANNUAL_INCOME_CHA));
		Select d15=new Select(incomechange);
		d15.selectByIndex(1);		
		
		driver.findElement(By.xpath("//td[2]/nobr/button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.APP_PRIMARY_INS_CARERIER)));
		driver.findElement(By.name(OR.APP_PRIMARY_INS_CARERIER)).sendKeys("and");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.APP_PRIMARY_INS_CARERIER)));
		driver.findElement(By.name(OR.APP_PRIMARY_INS_CARERIER)).sendKeys(Keys.DOWN);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(OR.APP_PRIMARY_INS_CARERIER)));
		driver.findElement(By.name(OR.APP_PRIMARY_INS_CARERIER)).sendKeys(Keys.ENTER);
		
		//InsuranceType, Plantype
		
		WebElement addinf1=driver.findElement(By.id(OR.APP_INSURANCE_TYPE));
		Select d16=new Select(addinf1);
		d16.selectByIndex(1);
		
		WebElement addinf2=driver.findElement(By.id(OR.APP_PLAN_TYPE));
		Select d17=new Select(addinf2);
		d17.selectByIndex(3);

		driver.findElement(By.id(OR.APP_POLICY_ID_NO)).sendKeys("4543454");
		
		driver.findElement(By.id(OR.APP_SUBSCRIBER_NAME)).sendKeys(generatedString+"TEST");
		
		driver.findElement(By.id(OR.APP_TELEPHONE_NO)).sendKeys("9876543216");
		
		driver.findElement(By.id(OR.APP_GROUP_NO)).sendKeys("25");
		
		driver.findElement(By.name(OR.APP_SUB_DOB)).sendKeys("5/2/2018");
		Thread.sleep(2000);

		WebElement drugplan=driver.findElement(By.id(OR.APP_PLAN_TYPE));
		Select d18=new Select(drugplan);
		d18.selectByIndex(2);	
		
		WebElement CoverPrescriptionDrugs=driver.findElement(By.id(OR.APP_PRES_DRUGS));
		Select d19=new Select(CoverPrescriptionDrugs);
		d19.selectByIndex(1);
		
		driver.findElement(By.id(OR.APP_CO_INSU_MED_SERVICE)).sendKeys("5");
		
		driver.findElement(By.id(OR.APP_CO_INSU_PHAR_BENEFITS)).sendKeys("5");
		
		WebElement HasMedcareD=driver.findElement(By.id(OR.APP_HAS_MEDICARE));
		Select d20=new Select(HasMedcareD);
		d20.selectByIndex(1);
		
		WebElement HasMedicareSupplement=driver.findElement(By.id(OR.APP_HAS_MEDICARE_SUPP));
		Select d21=new Select(HasMedicareSupplement);
		d21.selectByIndex(1);
		
		WebElement SecondaryInsurance=driver.findElement(By.id(OR.APP_SECOND_INS));
		Select d22=new Select(SecondaryInsurance);
		d22.selectByIndex(1);
		Thread.sleep(2000);
		
		WebElement CoverageCOBRA=driver.findElement(By.id(OR.APP_COVERAGE_COBRA));
		Select d23=new Select(CoverageCOBRA);
		d23.selectByIndex(1);
		
		//Clicking Next
		driver.findElement(By.xpath(OR.APP_NEXT_BUTTON)).click();
		
		driver.findElement(By.id(OR.APP_SEARCH_FACILITY)).sendKeys("john");

		driver.findElement(By.xpath(OR.APP_SEARCH_BUTTON)).click();		
		
		driver.findElement(By.xpath(OR.APP_SEARCH_BUTTON)).click();

		driver.findElement(By.xpath("//td/a")).click();
		
		driver.findElement(By.linkText(OR.APP_ADDLINK)).click();
		
		driver.findElement(By.xpath("//td[2]/table/tbody/tr/td[2]/nobr/button")).click();
		Thread.sleep(2000);
		
//		driver.findElement(By.xpath("//nobr/button")).click();
		
		driver.findElement(By.xpath(OR.APP_MEDICAL)).click();
		
		Thread.sleep(1000);
		driver.findElement(By.name(OR.APP_PATIENT_DIAGNOSIS)).sendKeys("and");
		Thread.sleep(2000);
		
		driver.findElement(By.name(OR.APP_PATIENT_DIAGNOSIS)).sendKeys(Keys.DOWN);
		Thread.sleep(1000);
		driver.findElement(By.name(OR.APP_PATIENT_DIAGNOSIS)).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.name(OR.APP_DOB_TREATMENT)).sendKeys("5/24/2018");
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//nobr/button")).click();
		Thread.sleep(1000);
		
		Thread.sleep(1000);
		driver.findElement(By.id(OR.APP_PROVIDER_AGREE)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(OR.APP_PATIENT_AGREE)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(OR.APP_CALLERS_NAME)).sendKeys(generatedString+"Test");
		Thread.sleep(1000);
		driver.findElement(By.id(OR.APP_CALLERS_PH_NO)).sendKeys("2018877025");
		Thread.sleep(1000);
		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();
		Thread.sleep(1000);
		driver.findElement(By.id(OR.PATIENT_SUBMIT_BUTTON)).click();
		Thread.sleep(1000);
	}
	public static String ssnInput(){
		// initialize a Random object somewhere; you should only need one
		Random random = new Random();

		// generate a random integer from 0 to 899, then add 100
		int x = random.nextInt(900) + 100;	String ssn_1 = Integer.toString(x); System.out.println(x);
		int y = random.nextInt(10) + 90;	String ssn_2 = Integer.toString(y);System.out.println(y);
		int z = random.nextInt(9000) + 1000;	String ssn_3 = Integer.toString(z);System.out.println(z);
		String ssn=ssn_1+ssn_2+ssn_3;		
		return ssn;
	}
}
