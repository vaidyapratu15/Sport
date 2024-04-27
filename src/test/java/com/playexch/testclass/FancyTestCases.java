package com.playexch.testclass;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class FancyTestCases extends BaseTest {

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
	public void CricketEventAvailable() throws InterruptedException {
		fancyPage.clickPlayexchCricketbtn();
		Thread.sleep(2000);
		fancyPage.getPlayexchCricketEvents();
	}
	
    @Test(priority = 2)
    public void verifyFancyMarket() {
    int numEvents = 4; // The number of events to handle
    boolean verifyFancyMarketOdds = false;

    for (int eventNumber = 1; eventNumber <= numEvents; eventNumber++) {
        try {
            handleEvent(eventNumber, verifyFancyMarketOdds);
            verifyFancyMarketOdds = true; // Set odds Executed to true after the first event
            break; // Break the loop after the common code is executed successfully
        } catch (Exception e) {
            e.printStackTrace(); 
            if (eventNumber == numEvents) {
                throw new RuntimeException("Event " + eventNumber + " not found. Test case failed.", e);
            }
        }
    }
}

    private void handleEvent(int eventNumber, boolean verifyFancyMarketOdds) throws InterruptedException {
    switch (eventNumber) {
        case 1:
            // First event
            profilePage.getPlayexchFirstEventName();
            fancyPage.clickPlayexchCricketFirstMatch();
            break;

        case 2:
            // Second event
            fancyPage.scrollUpToCricketBtn();
            fancyPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchSecondEventName();
            fancyPage.clickPlayexchCricketSecondMatch();
            break;

        case 3:
            // Third event
            fancyPage.scrollUpToCricketBtn();
            fancyPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchThirdEventName();
            fancyPage.clickPlayexchCricketThirdMatch();
            break;

        case 4:
            // Fourth event
            fancyPage.scrollUpToCricketBtn();
            fancyPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchFourthEventName();
            fancyPage.clickPlayexchCricketFourthMatch();
            break;

        default:
            throw new RuntimeException("Invalid event number: " + eventNumber);
    }

    if (!verifyFancyMarketOdds) {
        fancyPage.setPlayexchFancy();
        fancyPage.setPlayexchFancyYesNoOdds();
    }
}

 
	@Test(priority = 3, dependsOnMethods = "verifyFancyMarket")
	public void getProfileInfoBeforePlaceBet() throws InterruptedException, IOException {
		openBetsPage.clickOnCancelBtn();
		fancyPage.setPlayexchPageScrollUp();
		test.info("Before Bet Place");
		getProfileInfo();
		fancyPage.setPlayexchPageScrollDown();
	}

	@Test(priority = 4, dependsOnMethods = { "verifyFancyMarket" })
	public void verifyBetPlace() throws InterruptedException {
		fancyPage.getPlayexchFirstOddStatus();
		fancyPage.getPlayexchSecondOddStatus();

		fancyPage.setPlayexchFancyYesNoOdds();
		// fancyPage.clickPlayexchFancyMarketNo();
		// fancyPage.clickPlayexchfancyMarketYes();

		fancyPage.getPlayexchStakeValue("3");
		fancyPage.clickPlayexchPlaceBtn();
		fancyPage.setPlayexchPageScrollUp();
	}

	@Test(priority = 5, dependsOnMethods = { "verifyFancyMarket", "verifyBetPlace" })
	public void getProfileInfoAfterPlaceBet() throws InterruptedException, IOException {
		test.info("After Bet Place");
		getProfileInfo();
	}

	@Test(priority = 6, dependsOnMethods = "verifyBetPlace")
	public void VerifyAllBetPlaceInfo() throws InterruptedException {
		updateNewVersion();
		openBetsPage.clickPlayexchOpenbetsBtn();
		Thread.sleep(3000);
		openBetsPage.getPlayexchMyAllBets();
	}

	@Test(priority = 7)
	public void logOutToWeb() throws InterruptedException {
		fancyPage.setPlayexchPageScrollUp();
		loginPage.clickPlayexchLogOut(driver);
	}

	public void updateNewVersion() {
		loginPage.clickPlayexchUpdate();
	}

	public void getProfileInfo() throws InterruptedException {
		profilePage.clickPlayexchProfile();
		Thread.sleep(3000);
		test.info(profilePage.getPlayexchavailableBlns());
		test.info(profilePage.getPlayexchCreditLimit());
		test.info(profilePage.getPlayexchWinnings());
		test.info(profilePage.getPlayexchNetExposure());
		profilePage.clickPlayexchCloseBtn();
	}

	/* 
	@Test(priority = 2)
	public void verifyFancyMarket() throws InterruptedException {
		try {
			profilePage.getPlayexchFirstEventName();
			fancyPage.clickPlayexchCricketFirstMatch();
			fancyPage.setPlayexchFancy();

			try {
				fancyPage.setPlayexchFancyYesNoOdds();
				// If the above line succeeds, continue with the rest of the test
			} catch (RuntimeException ex) {
				test.info("setPlayexchFancyYesNoOdds failed for the first event.");
				handleSubsequentEvents();
			}
		} catch (NoSuchElementException e) {
			fancyPage.scrollUpToCricketBtn();
			fancyPage.clickPlayexchCricketbtn();
			handleSubsequentEvents();
		}
	}

	public void clickPlayexchCricketMatch(int matchNumber) throws NoSuchElementException {
		switch (matchNumber) {
			case 2:
				fancyPage.clickPlayexchCricketSecondMatch();
				break;
			case 3:
				fancyPage.clickPlayexchCricketThirdMatch();
				break;
			case 4:
				fancyPage.clickPlayexchCricketFourthMatch();
				break;
			default:
				throw new NoSuchElementException("Invalid match number: " + matchNumber);
		}
	}


	private void handleSubsequentEvents() throws InterruptedException {
		for (int i = 2; i <= 4; i++) {
			try {
				switch (i) {
					case 2:
						profilePage.getPlayexchSecondEventName();
						break;
					case 3:
						profilePage.getPlayexchThirdEventName();
						break;
					case 4:
						profilePage.getPlayexchFourthEventName();
						break;
				}
				clickPlayexchCricketMatch(i);
				fancyPage.setPlayexchFancy();
				fancyPage.setPlayexchFancyYesNoOdds();
				break; // break out of the loop if the operation succeeds
			} catch (NoSuchElementException ex) {
				fancyPage.scrollUpToCricketBtn();
				fancyPage.clickPlayexchCricketbtn();
				if (i == 4) {
					test.info("No fancy market available");
					throw ex; // re-throw the exception if all attempts fail
				}
			}
		}
	}
*/
	

	// public void verifyFancyMarket() throws InterruptedException {
	// updateNewVersion();
	// cricketPage.clickPlayexchCricketbtn();
	//
	//// profilePage.getPlayexchFirstEventName();
	// fancyPage.clickPlayexchCricketFirstMatch();
	//// profilePage.getPlayexchSecondEventName();
	// fancyPage.clickPlayexchCricketSecondMatch();
	// profilePage.getPlayexchThirdEventName();
	// fancyPage.clickPlayexchCricketThirdMatch();
	//// profilePage.getPlayexchForthEventName();
	// fancyPage.clickPlayexchCricketFourthMatch();
	//
	//// fancyPage.setPlayexchPageScrollDown();
	// fancyPage.setPlayexchFancy();
	// }
}
