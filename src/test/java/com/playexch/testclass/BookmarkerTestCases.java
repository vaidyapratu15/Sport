package com.playexch.testclass;
import org.testng.annotations.Test;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class BookmarkerTestCases extends BaseTest {

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
		bookmakerPage.clickPlayexchCricketbtn();
		Thread.sleep(3000);
		bookmakerPage.getPlayexchCricketEvents();
	}

	@Test(priority = 2)
    public void verifyBookmaker() {
    int numEvents = 4; // The number of events to handle
    boolean verifyBookmakerOdds = false;

    for (int eventNumber = 1; eventNumber <= numEvents; eventNumber++) {
        try {
            handleEvent(eventNumber, verifyBookmakerOdds);
            verifyBookmakerOdds = true; // Set odds Executed to true after the first event
            break; // Break the loop after the common code is executed successfully
        } catch (Exception e) {
            e.printStackTrace(); 
            if (eventNumber == numEvents) {
                throw new RuntimeException("Event " + eventNumber + " not found. Test case failed.", e);
            }
        }
    }
    }

    private void handleEvent(int eventNumber, boolean verifyBookmakerOdds) throws InterruptedException {
    switch (eventNumber) {
        case 1:    // First event
            profilePage.getPlayexchFirstEventName();
            bookmakerPage.clickPlayexchCricketFirstMatch();
            break;

        case 2:   // Second event
            bookmakerPage.scrollUpToCricketBtn();
            bookmakerPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchSecondEventName();
            bookmakerPage.clickPlayexchCricketSecondMatch();
            break;

        case 3:   // Third event
            bookmakerPage.scrollUpToCricketBtn();
            bookmakerPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchThirdEventName();
            bookmakerPage.clickPlayexchCricketThirdMatch();
            break;

        case 4:   // Fourth event
            bookmakerPage.scrollUpToCricketBtn();
            bookmakerPage.clickPlayexchCricketbtn();
            profilePage.getPlayexchFourthEventName();
            bookmakerPage.clickPlayexchCricketFourthMatch();
            break;

        default:
            throw new RuntimeException("Invalid event number: " + eventNumber);
    }

        if (!verifyBookmakerOdds) {
		bookmakerPage.setPlayexchBookmaker();
		bookmakerPage.setPlayexchBookmakerOdds();
        }
    }

	
	@Test(priority = 3, dependsOnMethods = "verifyBookmaker")
	public void getProfileInfoBeforePlaceBet() throws InterruptedException, IOException {
		openBetsPage.clickOnCancelBtn();
		test.info("Before Bet Place");
		bookmakerPage.setPlayexchPageScrollUp();
		getProfileInfo();
	}

	@Test(priority = 4, dependsOnMethods = "verifyBookmaker")
	public void verifyBetPlace() throws InterruptedException {
		bookmakerPage.setPlayexchPageScrollDown();
		bookmakerPage.getPlayexchFirstHorseOddStatus();
		bookmakerPage.getPlayexchSecondHorseOddStatus();

		bookmakerPage.setPlayexchBookmakerOdds();

		bookmakerPage.getPlayexchStakeValue("10");
		bookmakerPage.clickPlayexchPlaceBtn();
		bookmakerPage.setPlayexchPageScrollUp();

	}

	@Test(priority = 5, dependsOnMethods = { "verifyBookmaker", "verifyBetPlace" })
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
		bookmakerPage.setPlayexchPageScrollUp();
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

	public void verifyBookmaker1() throws InterruptedException {
		updateNewVersion();
		cricketPage.clickPlayexchCricketbtn();
		// profilePage.getPlayexchFirstEventName();
		// bookmakerPage.clickPlayexchCricketFirstMatch();
		// profilePage.getPlayexchSecondEventName();bookmakerPage.clickPlayexchCricketSecondMatch();
		// profilePage.getPlayexchThirdEventName();
		// bookmakerPage.clickPlayexchCricketThirdMatch();
		// profilePage.getPlayexchForthEventName();
		// bookmakerPage.clickPlayexchCricketFourthMatch();
		// bookmakerPage.setPlayexchBookmaker();
	}

	// bookmakerPage.clickPlayexchFirstHorseBack(); // First Back
	// bookmakerPage.clickPlayexchFirstHorseLay(); // First Lay
	// bookmakerPage.clickPlayexchSecondHorseBack(); // Second Back
	// bookmakerPage.clickPlayexchSecondHorseLay(); // Second Lay
	// bookmakerPage.verifyPlayexchBookmakerOdds(); //used if else

	

	/*  // here only it checked 1st and direct jump on 3rd event so not using
	public void clickPlayexchCricketMatch(int matchNumber) throws NoSuchElementException {
		switch (matchNumber) {
			case 2:
				bookmakerPage.clickPlayexchCricketSecondMatch();
				break;
			case 3:
				bookmakerPage.clickPlayexchCricketThirdMatch();
				break;
			case 4:
				bookmakerPage.clickPlayexchCricketFourthMatch();
				break;
			default:
				throw new NoSuchElementException("Invalid match number: " + matchNumber);
		}
	}

	@Test(priority = 2)
	public void verifyBookmaker() throws InterruptedException {
		try {
			profilePage.getPlayexchFirstEventName();
			bookmakerPage.clickPlayexchCricketFirstMatch();
			bookmakerPage.setPlayexchBookmaker();

			try {
				bookmakerPage.setPlayexchBookmakerOdds();
			} catch (RuntimeException ex) {
				test.info("setPlayexchBookmakerOdds failed for the first event.");
				handleSubsequentEvents();
			}
		} catch (NoSuchElementException e) {
			bookmakerPage.scrollUpToCricketBtn();
			bookmakerPage.clickPlayexchCricketbtn();
			handleSubsequentEvents();
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
				bookmakerPage.setPlayexchBookmaker();
				bookmakerPage.setPlayexchBookmakerOdds();
				break; // break out of the loop if the operation succeeds
			} catch (NoSuchElementException ex) {
				bookmakerPage.scrollUpToCricketBtn();
				bookmakerPage.clickPlayexchCricketbtn();
				if (i == 4) {
					test.info("No bookmaker market available");
					throw ex; // re-throw the exception if all attempts fail
				}
			}
		}
	}
*/

}
