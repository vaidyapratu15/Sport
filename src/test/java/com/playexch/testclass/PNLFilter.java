package com.playexch.testclass;

import java.io.IOException;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import com.playexch.library.Utilities;
import com.playexch.testlibrary.BaseTest;

public class PNLFilter extends BaseTest {
	@Test(priority = 0)
	public void loginToWeb() throws EncryptedDocumentException, IOException, ParseException, InterruptedException {
		driver.get(Utilities.getJsonData("url"));
		loginPage.clickPlayexchSubscribe();
		loginPage.clickPlayexchLoginPagelogin();
		test.info("URL - " + driver.getCurrentUrl());
		test.info(loginPage.getPlayexchLoginPageUn(Utilities.getJsonData("username")));
		loginPage.getPlayexchLoginPagePwd(Utilities.getJsonData("password"));
		loginPage.clickPlayexchLoginPageloginBtn();
		
	}
	@Test(priority = 1)
    public void handleSportbook() throws InterruptedException {
    profilePage.clickPlayexchProfile();
    driver.findElement(By.xpath("//div[contains(text(),'Betting')]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("(//div[contains(@class,'v-input__control')])[2]")).click();
    
    List<WebElement> allGames = driver.findElements(By.xpath("//div[@role='option']"));
    test.info("Total Games: " + allGames.size());
    Thread.sleep(5000); 
    String optionToSelect = "SportsBook";
    for (WebElement optionElement : allGames) {
        String optionText = optionElement.getText();
        if (optionText.contains(optionToSelect)) {
            optionElement.click();
            test.info("Clicked on : " + optionText);
            break;
        }
    }
}

@Test(priority = 2)
public void handleSportbook2() throws InterruptedException {
    driver.findElement(By.xpath("(//div[contains(@class,'v-input__control')])[3]")).click();
    Thread.sleep(3000); // Wait for the dropdown options to appear (adjust the delay as needed)
    
    List<WebElement> allOptions = driver.findElements(By.xpath("//div[@role='option']"));
    test.info("Total Options: " + allOptions.size());
    
    String cricket = "Cricket";
//  String tennis = "Tennis";
//	String soccer = "Soccer";
    for (WebElement option : allOptions) {
        String optionText = option.getText();
        if (optionText.contains(cricket)) {
            option.click();
            test.info("Clicked on: " + optionText);
            break;
        }
    }
}


//@Test(priority = 3)
	public void CalendarFirst() throws InterruptedException {
	//	year("2023");
		Thread.sleep(2000);
	//	month("jul");
		Thread.sleep(2000);
	//	date("30");
		Thread.sleep(2000);

	}

//	@Test(priority = 4)
	public void CalendarSecond() throws InterruptedException {
	//	year("2023");
		Thread.sleep(1000);
	//	month("Aug");
		Thread.sleep(1000);
	//	date("22");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div//button//span[text()='Search']")).click();
	}

	@Test(priority = 3)	
	public void year() throws InterruptedException {
		driver.findElement(By.xpath("(//div[contains(@class,'v-input__control')])[4]")).click();
		driver.findElement(By.className("v-date-picker-header__value")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("v-date-picker-header__value")).click();

		List<WebElement> y = driver.findElements(By.xpath("//div//ul[@class='v-date-picker-years']//li"));
		test.info("year :  " + y.size());
	    String value = "2020";
		Thread.sleep(3000);
		for (WebElement yearElement : y) {
			if (yearElement.getText().contains(value)) {
				yearElement.click();
				test.info("clicked");
				break;
			}
		}
	}

	@Test(priority = 4)		
	public void month() throws InterruptedException {
		List<WebElement> m = driver.findElements(By.xpath("//div[contains(@class,'v-btn__content')]"));
		test.info("Month  : " + m.size());
		String value = "NOV";
		Thread.sleep(3000);
		for (WebElement monthElement : m) {
			if (monthElement.getText().contains(value)) {
				monthElement.click();
				test.info("clicked");
				break;
			}
		}
	}

	@Test(priority = 5)		
	public void date() throws InterruptedException {
		List<WebElement> d = driver.findElements(By.xpath("//div[contains(@class,'date')]//td"));
		test.info("date  : " + d.size());
		String value = "11";
		for (WebElement dateElement :d) {
			if (dateElement.getText().contains(value)) {
				dateElement.click();
				test.info("clicked");
				break;
			}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button//span[text()='OK']")).click();
	}

	@Test(priority = 8)
	public void verifyAllData() throws InterruptedException {
		Thread.sleep(6000);
		List<WebElement> msg = driver.findElements(By.xpath("//div[contains(@class,'row ma-auto my-0')]"));
		for (WebElement a : msg) {
			test.info(a.getText());
		}
	}
}
