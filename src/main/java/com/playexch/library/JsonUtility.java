package com.playexch.library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonUtility {
	
	public static String getLatestData(String jsonfileName , String key) throws IOException, ParseException {
	    JSONParser parser = new JSONParser();
	    try (FileReader fileReader = new FileReader(jsonfileName)) {
	        Object obj = parser.parse(fileReader);
	        if (obj instanceof JSONArray) {
	            JSONArray bonusDataArray = (JSONArray) obj;
	            if (bonusDataArray.size() > 0) {
	                JSONObject latestBonusData = (JSONObject) bonusDataArray.get(bonusDataArray.size() - 1);
	                return (String) latestBonusData.get(key);  //"Bonus : "
	            }
	        }
	    } catch (FileNotFoundException e) {
	        // ignore - file does not exist yet
	    }
	    return null;
	}
	
	public static String getJsonBeforeBalanceData(String key) throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String jsonFilePath = userDir +"Json/UIJsonData/beforeBalanceInfo.json";
		JSONParser json = new JSONParser();
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(jsonFilePath));
		String value = (String) jsonObj.get(key);
		return value;
	}

	public static String getJsonAfterBalanceData(String key) throws IOException, ParseException {
		String userDir = System.getProperty("user.dir"); 
		String jsonFilePath = userDir +"Json/UIJsonData/afterBalanceInfo.json";
		JSONParser json = new JSONParser();
		JSONObject jsonObj = (JSONObject) json.parse(new FileReader(jsonFilePath));
		String value = (String) jsonObj.get(key);
		return value;
	}

	public static String getTestData(int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//ExcelData//ExcelCredential.xlsx");
		Sheet sheet = WorkbookFactory.create(file).getSheet("Sheet1");
		String value = sheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		return value;
	}
	
	
		

	
	


	

	
	

}
