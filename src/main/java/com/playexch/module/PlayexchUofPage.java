package com.playexch.module;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.playexch.action.ActionClass;
import com.playexch.library.BaseClass;

public class PlayexchUofPage extends BaseClass {
	
		ActionClass action = new ActionClass();
		
		@FindBy(xpath = "(//a[@role='tab']//following::a[1])[1]")
		private WebElement cricketBtn;
		
		@FindBy(xpath="(//div//button[contains(@class,'v-expansion-panel-header')]//a)[1]") 
		private WebElement cricketFirstMatch;
		
		@FindBy(xpath="(//div//button[contains(@class,'v-expansion-panel-header')]//a)[2]") 
		private WebElement cricketSecondMatch;
		
		@FindBy(xpath="(//div//button[contains(@class,'v-expansion-panel-header')]//a)[3]") 
		private WebElement cricketThirdMatch;
		
		@FindBy(xpath="(//div//button[contains(@class,'v-expansion-panel-header')]//a)[4]") 
		private WebElement cricketForthMatch;
		
		@FindBy(xpath = "//span[@class='match-title']")
		private  List<WebElement> cricketEvents ;
		
		@FindBy(xpath = "(//div[contains(@class,'v-window')])[4]//span[contains(@class,'premium-title')]")
		private  List<WebElement> allUOFMarkets ;
		
		@FindBy(xpath = "//div[contains(text(),'PREMIUM CRICKET')]")
		private  WebElement premiumUOF ;
		
		@FindBy(xpath = "(//input[@type='text'])[5]")
		private WebElement stakevalue;
		
		@FindBy(xpath = "//div[@class = 'v-text-field__slot']//input[1]")
		private WebElement oddvalue;
		
		@FindBy(xpath = "(//span[@class='v-btn__content'])[6]")
		private WebElement profileBtn;
		
		@FindBy (xpath ="//button[@type='submit']")  //span[text()='Place Bet ']
		private WebElement placeBtn;
		
		@FindBy(xpath = "//div[contains(@class,'v-sheet theme')]//div[@role='status']")
		private WebElement betPlaceMsg;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[2]//button[contains(@class,'btn-odds')])[1]")
		private WebElement secondMarketBack;   //(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[1]
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[3]//button[contains(@class,'btn-odds')])[3]")
		private WebElement thirdMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[4]//button[contains(@class,'btn-odds')])[1]")
		private WebElement forthMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[5]//button[contains(@class,'btn-odds')])[3]")
		private WebElement fifthMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[6]//button[contains(@class,'btn-odds')])[1]")
		private WebElement sisthMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[7]//button[contains(@class,'btn-odds')])[3]")
		private WebElement sevenMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[8]//button[contains(@class,'btn-odds')])[1]")
		private WebElement eightMarketBack;
		
		@FindBy(xpath = "(((//div[@class='v-window__container'])[2]//div[@class='pa-0 odds-category'])[9]//button[contains(@class,'btn-odds')])[1]")
		private WebElement ninethMarketBack;
		
		@FindBy(xpath = "//div//span[@class='match-title']")
		private List<WebElement> eventNames;
		
		@FindBy (xpath="(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[1] |"
				+ "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[5] |"
				+ "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[11] |"
				+ "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[3] |"
				+ "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[13] |"
				+ "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[23]")
		private List<WebElement> list;
		
		//(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[1]
		public PlayexchUofPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		public void clickPlayexchCricketbtn() throws InterruptedException {
			try {
				test.info("Cricket btn visible :"+ cricketBtn.isDisplayed());
			    cricketBtn.click();} catch (Exception e) { action.jSForClick(driver, cricketBtn);}
		}
		
		public void getPlayexchCricketEvents() {
			test.info("Total Criket Events Available : " + cricketEvents.size());
			for(WebElement ele : cricketEvents) {			
				test.info(ele.getText()); }
		}
		
		public void clickPlayexchCricketFirstMatch() {
			cricketFirstMatch.click(); 
		}		
		public void clickPlayexchCricketSecondMatch() {
			try {
				cricketSecondMatch.click();}catch(Exception e) {
					action.jSForScrollDown(driver);
					cricketSecondMatch.click();}
		}		
		public void clickPlayexchCricketThirdMatch() {
			try {
			cricketThirdMatch.click();}catch(Exception e) {
				action.jSForScrollDown(driver);
				cricketThirdMatch.click();}
		}
		public void clickPlayexchCricketForthMatch() {
			try {
			cricketForthMatch.click();}catch(Exception e) {
				action.jSForScrollDown(driver);
				cricketForthMatch.click(); }
		}
		
		  public void setPlayexchPremiumMarket() throws InterruptedException {
				Thread.sleep(5000);
				action.jSForScroll(driver, premiumUOF);
					   try {
						   action.textToBePresentInElement(driver, premiumUOF, "PREMIUM CRICKET", 10);
							test.info("PREMIUM CRICKET is present : " + premiumUOF.isDisplayed());
							action.jSForClick(driver, premiumUOF); //premiumUOF.click();
						} catch (Exception e) {
							test.info("No PREMIUM CRICKET is present");
						}	
					//   action.jSForScrollDown(driver);	   
		}
		
		public void getAllUofMarket () throws InterruptedException {
			  Thread.sleep(3000);   
				test.info("Total Market Available: " + allUOFMarkets.size());
				for(WebElement ele : allUOFMarkets) {			
					test.info(ele.getText());
				}		
		}
		
		public void getPlayexchStakeValue(String entervalue) {
			stakevalue.sendKeys(entervalue);
			test.info("Stake Value : " +entervalue );
		}
		
		public void scrollUpToCricketBtn() {
			action.jSForScroll(driver, cricketBtn);
		}
		
		public void setPlayexchPageScrollUp() throws InterruptedException {
			action.jSForScroll(driver, profileBtn);
			Thread.sleep(3000);
		}

		public void clickPlayexchPlaceBtn() {
					test.info("Place Bet Button is Enabled : "+placeBtn.isEnabled());
					 try { placeBtn.click(); }catch(Exception e){ 
					action.jSForClick(driver, placeBtn);	}
					action.explicitVisiblility(driver, betPlaceMsg, 10);		
					test.info(betPlaceMsg.getText());
		}
		
		public void setScrollup() {
			action.jSForScroll(driver, profileBtn);
		}
		  public void clickPremium2Market() {
			   try {		   
			   action.explicitVisiblility(driver, thirdMarketBack, 5);
			   thirdMarketBack.click();test.info(" Back Odds Enabled : "+thirdMarketBack.isEnabled() ); 
			   if(thirdMarketBack.isEnabled()) {   test.info(" Back Odds  : "+thirdMarketBack.getText());
			   }
			   }catch (Exception e) { test.info("Back Odds NOT Visible"); }
		 }
		  
		  public void clickPremium3Market() {
			   try {		   
			   action.explicitVisiblility(driver, forthMarketBack, 5);
			   forthMarketBack.click();test.info("Back Odds Enabled : "+forthMarketBack.isEnabled()); 
			   if(forthMarketBack.isEnabled()) {   test.info(" Back Odds  : "+forthMarketBack.getText());}
			   }catch (Exception e) { test.info("Back Odds NOT Visible"); }
		 }
		 
		  public void setRandomClick() {
			  List<WebElement> list = driver.findElements(
				        By.xpath("(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[1] | "
				                + "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[5] |" 
				                + "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[11] |" 
				                + "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[3] |" 
				                + "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[13] |" 
				                + "(//div[contains(@class,'premium-cricket-tab')]//button[contains(@class,'btn-odds')])[23]"));
				Random gen = new Random();
				boolean buttonClicked = false;
				while (!buttonClicked && list.size() > 0) {
				    WebElement ele = list.get(gen.nextInt(list.size()));
				    try {	
				        ele.click();
				        test.info("Clicked Odd button text: " + ele.getText());
				        buttonClicked = true;
				    } catch (ElementNotInteractableException e) {
				    	test.info("Odds Button not interactable: " + ele.getText());
				        list.remove(ele);
				    }
				}
				if (!buttonClicked) {
					 test.info("All buttons are not interactable.");
				}
		  }
		

		  
		 
		
	

}
