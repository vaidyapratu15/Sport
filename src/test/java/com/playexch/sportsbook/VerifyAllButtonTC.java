package com.playexch.sportsbook;

import java.io.IOException;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.aventstack.extentreports.Status;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class VerifyAllButtonTC extends BaseTest{
	
	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
		driver.get(Utilities.getJsonData("url"));
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info("Title - " + driver.getTitle());
		test.info("Username - " + loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));
		loginPage.clickPlayexchLoginPageloginBtn();
	}

// @Test(priority = 1)
	public void checkCricketOdds() throws InterruptedException {
		cricketPage.clickPlayexchCricketbtn();
		verifyMatchesAvailable();
		checkAllOdds();
	}
	
//	@Test(priority = 2)
	public void checkSoccerOdds() throws InterruptedException {
		soccerPage.clickPlayexchOnSoccer();
		verifyMatchesAvailable();
		checkAllOdds();
	}
	
	@Test(priority = 3)
	public void checkTennisOdds() throws InterruptedException {
		tennisPage.clickPlayexchTennisbtn();
		verifyMatchesAvailable();
		checkAllOdds();
	}
	
	public void verifyMatchesAvailable() {
		cricketPage.setPlayexchMatch(driver);
		String text = cricketPage.getPlayexchCurrentlyMatches();
		test.log(Status.INFO, text);
		String actual = cricketPage.getPlayexchBackBtn();
		Assert.assertEquals(actual, "Back");
	}
	
	public void checkAllOdds() throws InterruptedException {
	    List<WebElement> allOdds = driver.findElements(By.xpath("//button[contains(@class, 'btn-odds')]"));

	    // Click on each element and print a message
	    for (WebElement oddElement : allOdds) {
	        try {
	            oddElement.click();
	            String elementText = oddElement.getText();
	            String extractedValue = extractFirstPart(elementText);
	            test.info("Clicked on element: " + extractedValue);
	            Thread.sleep(1000);
	        } catch (WebDriverException e) {
	        	 String elementText = oddElement.getText();
	        	  String extractedValue2 = extractFirstPart(elementText);
	            test.info("Could not click on element: " + extractedValue2);
	        }
	    }
	}

	// Method to extract the first part of the text before a space or any subsequent characters-2.94567   it will take 2
	private String extractFirstPart(String text) {
	    Pattern pattern = Pattern.compile("^([^\\s]+)");
	    Matcher matcher = pattern.matcher(text);

	    if (matcher.find()) {
	        return matcher.group(1);
	    }

	    return text;
	}
	
	@Test(priority = 4)
	public void allMyBetsPlaced() {
		openBetsPage.clickPlayexchOpenbetsBtn();
		openBetsPage.getPlayexchMyAllBets();
	}

	@Test(priority = 5)
	public void logOutToWeb() throws InterruptedException {
		loginPage.clickPlayexchLogOut(driver);
	}


	


}
