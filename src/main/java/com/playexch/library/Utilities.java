package com.playexch.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class Utilities {

	/*
	 * @Author: Pratiksha Vaidya.
	 * @Since : November 2022
	 * @Discription : getPropertyData this method use to getData from Property file
	 * by using parameters keys. : getTestData this method use to getData from Excel
	 * by using parameters int row & cell . : CaptureScreenshots this method use to
	 * capture failureScreenshots by using parameters testCaseName.
	 */

	public static String getJsonData(String key) throws IOException, ParseException {
		JSONParser json = new JSONParser();
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/Json/UIJsonData/data.json";
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(filePath));
		String value = (String) jsonObj.get(key);
		return value;
	}
	

	
	public static String CaptureScreenshots(String testCaseName, WebDriver driver) throws IOException {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String datestamp = new SimpleDateFormat("yyyy_MM_dd").format(new Date());
		String timestamp = new SimpleDateFormat("hh_mm_ss").format(new Date());
		File distination = new File(System.getProperty("user.dir") + "//ScreenShots//" + datestamp + "//" + testCaseName
				+ timestamp + ".png");
		FileUtils.copyFile(source, distination);
		return System.getProperty("user.dir") + "//Screenshots//" + datestamp + "//" + testCaseName + timestamp
				+ ".png";
	}
	
	
	public static String getBalanceData(String key) throws IOException {
//		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//data.properties");
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "//configuration//balanceinfo.properties");
		Properties p = new Properties();
		p.load(file);
		String value = p.getProperty(key);
		return value;
	}
	
	@SuppressWarnings("unchecked")  //used for changePwd
	public static void storeData(String key ,String value, String jsonfileName) throws IOException, ParseException {
	    String userDir = System.getProperty("user.dir");
	    String filePath = userDir + jsonfileName;

	    JSONParser parser = new JSONParser();
	    try (FileReader fileReader = new FileReader(filePath)) {
	        JSONObject jsonData = (JSONObject) parser.parse(fileReader);

	        // Update the value
	        jsonData.put(key , value);

	        try (FileWriter fileWriter = new FileWriter(filePath)) {
	            Gson gson = new GsonBuilder().setPrettyPrinting().create();
	            String formattedJsonData = gson.toJson(jsonData);
	            fileWriter.write(formattedJsonData);
	        }
	    } catch (IOException | ParseException e) {
	        e.printStackTrace();
	    }
	} 

	public static void updateJsonData() throws IOException, ParseException { //used change password
	    String userDir = System.getProperty("user.dir");
	    String jsonFilePath = userDir + "/Json/UIJsonData/data.json";
	    String jsonData = null; 
	    try {
	        jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)), StandardCharsets.UTF_8);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	    Gson gson = new GsonBuilder().create(); 
	    @SuppressWarnings("unchecked")
	    Map<String, Object> data = gson.fromJson(jsonData, Map.class);

	    String newPwd = Utilities.getJsonData("newPassword");

	    data.put("password", newPwd); // Update the  "password" key

	    // Write the updated JSON data back to the file with each key-value pair on a separate line
	    try (Writer writer = new FileWriter(jsonFilePath)) {
	        JsonWriter jsonWriter = new JsonWriter(writer);
	        jsonWriter.setIndent("  "); //for pretty printing
	        gson.toJson(data, Map.class, jsonWriter);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
   
	public static String getLocalStorageData (String key) throws IOException, ParseException {
		JSONParser json = new JSONParser();
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/Json/UIJsonData/localStorage.json";
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(filePath));
		String value = (String) jsonObj.get(key);
		return value;
	}

	public static String getStakeValues (String key) throws IOException, ParseException {
		JSONParser json = new JSONParser();
		String userDir = System.getProperty("user.dir");
		String filePath = userDir + "/Json/StakesValue/stakeValue.json";
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(filePath));
		String value = (String) jsonObj.get(key);
		return value;
	}	


	
	

	
	

}
