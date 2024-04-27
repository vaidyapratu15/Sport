package com.playexch.testclass;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class EditStakeForBetSlipTC extends BaseTest {

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

	@Test(priority = 1)
    public void verifySportsBookMatches(){
		verifyAvailableMatches();
	}

	public void verifyAvailableMatches() {
	 Map<String, Runnable> sportsActions = new LinkedHashMap<>(); 
    
	    sportsActions.put("cricket", () -> {
	        cricketPage.clickPlayexchCricketbtn();
	        test.info("Clicked on cricket button");
	    });
	    sportsActions.put("soccer", () -> {
	        soccerPage.clickPlayexchOnSoccer();
	        test.info("Clicked on soccer button");
	    });
	    sportsActions.put("tennis", () -> {
	        tennisPage.clickPlayexchTennisbtn();
	        test.info("Clicked on tennis button");
	    });

	    for (Map.Entry<String, Runnable> entry : sportsActions.entrySet()) {
	        try {Thread.sleep(3000);
	            entry.getValue().run();
	           verifyMatchesAvailable();
	           oneClickPage.clickOnOddsButton();
	            break;
	        } catch (Exception e) {
	          
	        }
	    }
	}
	
	public void verifyMatchesAvailable() {
		cricketPage.setPlayexchMatch(driver);
		cricketPage.getPlayexchCurrentlyMatches();
		String actual = cricketPage.getPlayexchBackBtn();
		System.out.println(actual);
		Assert.assertEquals(actual, "Back");
	}
	
    @Test(priority = 2)
    public void ClickOnEditStakeSetting(){
        try {
            SetSettingiaBetSlip();
			test.info("Opened StakeSetting via BetSlip");
        } catch (Exception e) {
            SetStakeviaSetting();
			test.info("Opened StakeSetting via Profile");
        }
    }

    @Test(priority = 3)
    public void clickOnEditBtn(){
       editstake.clickOnEditBtn();
    }

	public void SetSettingiaBetSlip() throws InterruptedException {
		editstake.setEditStake();
		Thread.sleep(3000);
	}

    public void SetStakeviaSetting(){
        oneClickPage.clickPlayexchProfile();
		oneClickPage.clickProfileSettingBtn();
    } 

	@Test(priority = 4)
	public void editStakeValue() throws InterruptedException, IOException, ParseException{
		editstake.setStakesValue(Utilities.getStakeValues("10Stake"));    
	    editstake.clickOnSaveBtn();
	}

	@Test(priority = 5)
	public void openBetSlip() throws InterruptedException{
		verifyAvailableMatches();
		Thread.sleep(3000);
	}

	@Test(priority = 6 , dependsOnMethods = "openBetSlip")
	public void verifyEditValue() throws IOException, ParseException{
		editstake.verifystakeValues(Utilities.getStakeValues("10Stake"));     
	}

    
}
