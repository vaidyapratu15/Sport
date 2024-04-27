package com.playexch.testclass;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.testng.Assert;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class EditOneClickStakes extends BaseTest {

    @Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException {
		driver.get(Utilities.getJsonData("url"));
		updateNewVersion();
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info("Username - " + loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));
		loginPage.clickPlayexchLoginPageloginBtn();
	}

	@Test(priority = 1)
	public void checkToggleONOFF(){
		oneClickPage.checkActivationPopup();
	}
    
    @Test(priority = 2, dependsOnMethods = {"loginToWeb"})
	public void setOneClickPage() throws InterruptedException {
		updateNewVersion();
		Thread.sleep(2000);
		oneClickPage.clickPlayexchProfile();
		oneClickPage.clickProfileSettingBtn();
		Thread.sleep(3000);
	//	oneClickPage.verifyToggleBtnOn();
	}
    @Test (priority = 3 ,dependsOnMethods = "setOneClickPage")
    public void clickOnToggleBtn() throws InterruptedException {
		Thread.sleep(3000);
		oneClickPage.clickOneClickBettingBtn();	
	}

   @Test (priority = 4 , dependsOnMethods = "setOneClickPage")
    public void setEditStake() throws InterruptedException{
        oneClickPage.clickEditBtn();
    }

    @Test(priority = 5, dependsOnMethods = "setOneClickPage")
    public void editSatkeValue() throws InterruptedException{
        oneClickPage.setStakesValue("5");
        oneClickPage.clickOnSaveBtn();
    }
    
	@Test(priority = 6 , dependsOnMethods = { "setOneClickPage" })
	public void verifyActiveStake() throws InterruptedException {
		cricketPage.clickPlayexchCricketbtn();
     	oneClickPage.getActivestakePopup();
	}

	@Test(priority = 7, dependsOnMethods = { "setOneClickPage" })	
    public void placeBetViaOneClick() throws Exception {
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
	        try {
	            entry.getValue().run();
	            verifyMatchesAvailable();
	          //  openBetsPage.setRandomClick();
	            oneClickPage.clickOnOddsButton();
	            break;
	        } catch (Exception e) {
	          
	        }
	    }

	    try {
	        oneClickPage.getPlayexchBetPlacedMsg();
	    } catch (Exception e) {
	    }
	}
    @Test(priority = 8 , dependsOnMethods = { "setOneClickPage" })	
	public void closeOneClickBetSetting() throws InterruptedException {
		Thread.sleep(3000);
		oneClickPage.offOneClickBet();
		
	}
	
	@Test(priority = 9 , dependsOnMethods = { "setOneClickPage" })	
	public void checkToggleButton() {
		try {
			oneClickPage.checkToggleBtnOff();
		}catch(Exception e) { }
	}
  



    @Test(priority = 11)
	public void logOutToWeb() throws InterruptedException {
		bookmakerPage.setPlayexchPageScrollUp();
		loginPage.clickPlayexchLogOut(driver);
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}
    
	public void verifyMatchesAvailable() {
		cricketPage.setPlayexchMatch(driver);
		cricketPage.getPlayexchCurrentlyMatches();
		String actual = cricketPage.getPlayexchBackBtn();
		Assert.assertEquals(actual, "Back");
	}
}
