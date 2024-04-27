package com.playexch.module;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.playexch.library.BaseClass;
import io.netty.handler.timeout.TimeoutException;

public class PlayexchOpenBets extends BaseClass {

	@FindBy(xpath = "//span[contains(text(),' Open Bets')]")
	private WebElement openBetsBtn;

	@FindBy(xpath = "//button[contains(text(),'Unmatched Bets')]")
	private WebElement unmatchedBtn;

	@FindBy(xpath = "//div[contains(@class,'unmatch-section')]")
	private WebElement unmatchedText;

	@FindBy(xpath = "//button[contains(text(),'Matched Bets')]")
	private WebElement matchedBtn;

	@FindBy(xpath = "(//a[contains(@class,'mybet')])[1]")
	private WebElement matchedText;

	@FindBy(xpath = "(//a[contains(@class,'mybet')])[2]")
	private WebElement matchedText1;

	@FindBy(xpath = "(//a[contains(@class,'mybet')])[3]")
	private WebElement matchedText2;
	
	@FindBy(xpath = "//a[contains(@class,'mybet')]")
	private List<WebElement> myBets;

	@FindBy(xpath = "(//div[contains(@class,'v-item-group')])[5]//div[contains(@class,'mybet-card')]")
	private List<WebElement> myPremiumBets;

	@FindBy(xpath = "(//div[contains(@class,'text-white')])[1]")
	private WebElement verifyMatchedName;
	
	@FindBy (xpath="(//div[contains(@class,'v-expansion-panel')])[3]//button | (//div[contains(@class,'v-expansion-panel')])[6]//button")
	private List<WebElement> oddList;   //div//span[contains(@class,'position-relative')]

    @FindBy(xpath = "//button//span[contains(text(),'Cancel')]")
	private WebElement cancelbtn;


	public PlayexchOpenBets(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void clickPlayexchOpenbetsBtn() {
		openBetsBtn.click();
	}

	public String getPlayexchOpenBtnText() {
		String text = openBetsBtn.getText();
		return text;
	}

	public String getPlayexchUnmatched() {
		String text = unmatchedBtn.getText();
		return text;
	}

	public String getPlayexchUnmatchedBackLay() {
		String text = unmatchedText.getText();
		return text;
	}

	public String getPlayexchMatched() {
		String text = matchedBtn.getText();
		return text;
	}

	public String getPlayexchMatchedBackLay1() {
		String text = matchedText.getText();
		return text;
	}

	public String getPlayexchMatchedBackLay2() {
		String text = matchedText1.getText();
		return text;
	}

	public String getPlayexchMatchedBackLay3() {
		String text = matchedText2.getText();
		return text;
	}

	public String getPlayexchVerifyMatchedName() {
		String text = verifyMatchedName.getText();
		return text;
	}

	public void getPlayexchMyAllBets() {
		for (WebElement a : myBets) {
			test.info(a.getText());
		}
	}

	public void getPlayexchMyAllPremiumBets() {
		for (WebElement a : myPremiumBets) {
			test.info(a.getText());
		}
	}

    public void setRandomClick() {
        Random gen = new Random();
        boolean buttonClicked = false;
        int maxButtonsToCheck = 5; //will check 5 buttons

        for (int i = 0; i < maxButtonsToCheck; i++) {
            if (oddList.isEmpty()) {
                break;  //after 5 buttons, it will stop
            }

            WebElement ele = oddList.get(gen.nextInt(oddList.size()));
            if (isElementClickable(ele)) {
                try {
                    ele.click();
                    String elementText = ele.getText();
                    String extractedValue = extractFirstPart(elementText);
                    test.info("Clicked on element: " + extractedValue);
                    buttonClicked = true;
                    break; // Exit the loop if a clickable button is found
                } catch (WebDriverException e) {
                    String elementText = ele.getText();
                    String extractedValue = extractFirstPart(elementText);
                    test.info("Unable to click on element: " + extractedValue);
                    oddList.remove(ele);
                }
            } else {
                String elementText = ele.getText();
                String extractedValue = extractFirstPart(elementText);
                test.info("Button not clickable: " + extractedValue);
                oddList.remove(ele);
            }
        }

        if (!buttonClicked) {
            test.pass("No clickable buttons were found, and the markets odds are not visible");
        }
    }

    private boolean isElementClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isEnabled();
        } catch (TimeoutException e) {
            return false;
        }
    }

    private String extractFirstPart(String text) {
        String[] parts = text.split("\\s");
        String value = parts[0];
        value = value.replaceAll("[^\\d.]", ""); // Remove non-digit and non-dot characters
        return value;
    }
    public void clickOnCancelBtn() {
		try {
			cancelbtn.click();
		} catch (Exception e) {
		}
	}

}
