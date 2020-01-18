package org.firstprojectflyus;

import java.awt.AWTException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FlyUs extends BaseClass 
{
	
	public static void main(String[] args) throws InterruptedException, AWTException, IOException 
	{
		launchChrome();
		driver.manage().deleteAllCookies();
		openURL("https://www.flyus.com/");
		screenshot("mainPage.jpeg");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//scroll down concept used
		//clicking fb page
		scrollDown(findByXpath("//img[@src='img/social/fb-icon.svg']"));
		findByXpath("//img[@src='img/social/fb-icon.svg']").click();
		//windows handling concept used
		//switching back to main tab
		switchTab(1);
		scrollUp(findByXpath("//a[text()='Flights']"));
		//filling from place: here list, sendkeys and excelread concepts used
		List<String> fromAndTo = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\ThridProjectFlyUs\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 1);
		//filling from an to details fetched from excel sheet
		fillText(findByXpath("//input[@id='flys-dpt-0']"), fromAndTo.get(0));
		Thread.sleep(2000);
		//robot class used
		pressDownKey(1);
		pressEnter(1);
		
		//to place
		fillText(findByXpath("//input[@id='flys-arr-0']"), fromAndTo.get(1));
		Thread.sleep(2000);

		pressDownKey(1);
		pressEnter(1);
		
		//enter date: done forget to change the date while executing the program later
		fillText(findByXpath("//input[@id='flys-date-0']"), fromAndTo.get(2));
		
		System.out.println(fromAndTo.get(2));
		
		
		
		selectDate();
		screenshot("dateSelected.jpeg");

		Thread.sleep(2000);
		pressTab(2);		
		Thread.sleep(2000);

		findByXpath("//button[@type='submit']").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//closing a float window which aappears after few seconds
		findByXpath("(//button[@ng-click='paModalCtrl.close()'])[1]").click();
		screenshot("searchResults.jpeg");
		
		//clicking select flight
		findByXpath("(//img[@src='img/white-arrow-continue.svg'])[1]").click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		screenshot("selectedFlight.jpeg");
		
		//selecting title
		selectByVisibleText(findByXpath("//select[@name='paxes[ADULT-0][title]']"), "Mr.");
		
		//selecting gender
		selectByVisibleText(findByXpath("//select[@id='gender-pax-0']"), "Male");
		List<String> name = excelReadRow("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\ThridProjectFlyUs\\ExcelFiles\\ExcelInputGivingFile.xlsx", "Sheet0", 2);
		//entering firstname, lastname
		fillText(findByXpath("//input[@id='fname-pax-0']"), name.get(0) );
		fillText(findByXpath("//input[@id='lname-pax-0']"), name.get(1));
		
		//entering dob. first clicking..then entering
		findByXpath("//input[@id='paxes-ADULT-0-dob']").click();
		fillText(findByXpath("//input[@id='paxes-ADULT-0-dob']"),  name.get(2));
		
		//entering billing details
		//firstname, last name, billing address,
		fillText(findByXpath("//input[@id='cc-first-name']"), name.get(0));
		fillText(findByXpath("//input[@id='cc-last-name']"), name.get(1));
		
		fillText(findByXpath("(//input[@id='billing-address'])[1]"), name.get(3));
		
		//selecting country, state
		selectByVisibleText(findByXpath("//select[@id='cc-country']"), "India");
		Thread.sleep(3000);
		selectByVisibleText(findByXpath("//select[@id='cc-region']"), "Tamil Nadu");
		
		//entering city name, zip code, phone, email and email confirmation
		fillText(findByXpath("//input[@id='cc-city']"), name.get(4));
		fillText(findByXpath("//input[@id='cc-zip']"), name.get(5));
		fillText(findByXpath("//input[@id='cc-phone']"), name.get(6));
		fillText(findByXpath("//input[@id='cc-email']"), name.get(7));
		fillText(findByXpath("//input[@id='cc-email-conf']"), name.get(7));
		//findByXpath("(//input[@type='checkbox'])[2]").click();
		//findByXpath("//button[text()='CONTINUE & BOOK']").click();
	//	returnActionsObj().cl
		
		//getting text and writing in excel
		String price = findByXpath("(//h2[text()='$700.78 USD'])[2]").getText();
		excelWriteSingleCell("C:\\Users\\sathishPC\\Desktop\\selenium\\Programs\\ThridProjectFlyUs\\ExcelFiles\\ExcelOutputRecordingFile.xlsx", "sheet1", 0, 0, price);
		screenshot("last.jpeg");

	}

}
