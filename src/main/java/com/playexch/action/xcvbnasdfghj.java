package com.playexch.action;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class xcvbnasdfghj {
	
	public static WebDriver driver;
	public static ExtentSparkReporter spark;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public void setup() {
	  WebDriverManager.chromedriver().setup();
	  ChromeOptions option = new ChromeOptions();
	  option.addArguments("--remote-origins=*");
	  option.addArguments("--disable-dev-shm-usage");
		
      driver = new ChromeDriver(option);
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
      	
	}
	
	public static void setextent() {
		spark = new ExtentSparkReporter("Extent report.html");
		spark.config().setDocumentTitle("");
		spark.config().setReportName(null);
		spark.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(spark);		
	}
	
	public void jsclick(WebDriver driver, WebElement ele){
		JavascriptExecutor js  = (JavascriptExecutor)driver ;
		js.executeScript("argument[0].click();", ele);
		js.executeScript("arguments[0].scrollIntoview();", ele);
	}
	
	public void explicitwaitclicksble(WebDriver driver , WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		ele.click();
	}
	
   
	
	
	
	
	
	
	

}
