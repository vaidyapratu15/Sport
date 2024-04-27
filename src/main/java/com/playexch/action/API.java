package com.playexch.action;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class API {
   	
        public static String getPostResponce(String baseURL, String token , String id) throws Exception {  
        	
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(baseURL + "/Memberdevice/saveDeviceId");
            JSONObject requestBody = new JSONObject();
            requestBody.put("_accessToken",token);
            requestBody.put("deviceId", id);
    
            StringEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(requestEntity);  //request body
    
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) { // Send the request
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                return responseString;
              
             //   System.out.println("Response: " + responseString);
            }
        
        }
       
     
}


