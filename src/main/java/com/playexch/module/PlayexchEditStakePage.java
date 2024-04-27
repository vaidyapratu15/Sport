package com.playexch.module;

import java.util.NoSuchElementException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;

public class PlayexchEditStakePage extends BaseClass{

    ActionClass action = new ActionClass();
	Actions actobj = new Actions(driver);


    @FindBy (xpath="//div//a//span[text()='Edit Stakes']")
	private WebElement editStakeBtn;

    @FindBy (xpath="(//div[@class='pa-2 ma-auto col col-6'])[3]//button")
    private WebElement editBtn;

    @FindBy (xpath="(//div[@class='pa-2 ma-auto col col-6'])[4]//button")
    private WebElement saveBtn;

	@FindBy (xpath = "(//div[@class='v-text-field__slot']//input)[1]")
	private WebElement stakeValue;

	@FindBy (xpath="((//div[contains(@class,'row no-gutters')])[1]//button)[1]")
	private WebElement betSlipStakeValue;


    public PlayexchEditStakePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}




    public void setEditStake() {
		action.jSForClick(driver, editStakeBtn);
	//	editStakeBtn.click();
		test.info("Clicked on edit stake button");
	}

    public void clickOnEditBtn(){
       
	    boolean isEnabled = saveBtn.isEnabled();
	    if (!isEnabled) {
            test.info("Save button isEnabled : "  + saveBtn.isEnabled());
	        action.jSForClick(driver, editBtn);
	        test.pass("Clicked on Edit button");
            test.info("After clicked on Edit button Save button isEnabled : "  + saveBtn.isEnabled());
            
	    } else {
	        test.fail("Save button isEnabled before click on edit button");
	    }
	
    }
    
	public void setStakesValue(String valueStake) throws InterruptedException {
		actobj.moveToElement(stakeValue).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
		Thread.sleep(3000);
		stakeValue.sendKeys(valueStake);
		test.info("Stake value set as"+ " " + valueStake);
	}

	public void clickOnSaveBtn(){
		saveBtn.click();
		test.info("Clicked on save button");
		test.info("Save button isEnabled : "  + saveBtn.isEnabled());
	}
   
	public void verifystakeValues(String stake) {
		String value = betSlipStakeValue.getText();
		value = value.replaceAll("[^0-9]", "");
		int intValue = Integer.parseInt(value);
		test.info("BetSlip Stake value: " + Integer.toString(intValue));
	
		String expected = stake;
		String actual = Integer.toString(intValue);
		
//		if (actual == expected) {
	    if (actual.equals(expected)) { // Use .equals() for string content comparison
			test.info("Stake values match: Expected " + expected + " and Actual " + actual);
		} else {
			test.fail("Stake values do not match: Expected " + expected + " but got " + actual);
		}
	}
	

   
	 

    
}
