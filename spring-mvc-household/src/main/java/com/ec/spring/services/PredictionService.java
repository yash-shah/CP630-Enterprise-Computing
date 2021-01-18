package com.ec.spring.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ec.spring.model.Household;

/** A Service made in Spring MVC Project that can be dependency injected
 *  Used to call the RESTful API.
 */

@Service("predictionService")
public class PredictionService {
	
	static final Logger logger = Logger.getLogger(PredictionService.class);
 
    public String Predict() {
    	BasicConfigurator.configure();
		logger.info("predictionService - predict Requested");
    String output = "";
    	try {

    		URL url = new URL("http://localhost:8080/household-rs/rest/predict?");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Accept", "application/json");

    		logger.info("predictionService - connection to REST initilizing");
    		
    		if (conn.getResponseCode() != 200) {
    			output = "Failed : HTTP error code : "
    					+ conn.getResponseCode();
    			logger.error(output);
    		}

    		BufferedReader br = new BufferedReader(new InputStreamReader(
    			(conn.getInputStream())));

    		String result;
    		System.out.println("Output from Server .... \n");
    		while ((result = br.readLine()) != null) {
    			output += result;
    			logger.debug(output);
    		}
    		
    		 
    		output = output.substring(0, output.length() - 2);
    		output = output.substring(11, output.length());
    		
    		output = new DecimalFormat("###.##").format(Double.parseDouble(output)).toString();
    		conn.disconnect();
    		logger.info("predictionService - connection desconnected");
    		return output;

    	  } catch (MalformedURLException e) {

    		  e.printStackTrace();
    		  logger.error(e.getMessage());

    	  } catch (IOException e) {

    		e.printStackTrace();
    		logger.error(e.getMessage());
    	  }
		return output;
    	
    // return "Hello from Prediction Service! " + name;
    }
    
    public Boolean Load()
    {
    	BasicConfigurator.configure();
    	logger.info("predictionService - load Requested");
        String output = "";
    	try {

    		URL url = new URL("http://localhost:8080/household-rs/rest/load?");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Accept", "application/json");

    		logger.info("predictionService - connection to REST initilizing");
    		
    		if (conn.getResponseCode() != 200) {
    			output = "Failed : HTTP error code : "
    					+ conn.getResponseCode();
    		}

    		BufferedReader br = new BufferedReader(new InputStreamReader(
    			(conn.getInputStream())));

    		String result;
    		System.out.println("Output from Server .... \n");
    		while ((result = br.readLine()) != null) {
    			output += result;
    			logger.info(output);
    		}
    		logger.info("predictionService - connection disconnecting");
    		conn.disconnect();
    		return true;

    	  } catch (MalformedURLException e) {

    		  e.printStackTrace();
    		  logger.error(e.getMessage());

    	  } catch (IOException e) {

    		e.printStackTrace();
    		logger.error(e.getMessage());
    	  }
		return false;
    }
    
    public Boolean Save(Household _house)
    {
    	BasicConfigurator.configure();
		logger.debug("Save action Requested");
        String output = "";
    	try {
    		HttpPost post = new HttpPost("http://localhost:8080/household-rs/rest/save?");
    		
    		List<NameValuePair> urlParameters = new ArrayList<>();
            

    		urlParameters.add(new BasicNameValuePair("groceryBill", Double.toString(_house.getGroceryBill())));
    		
    		urlParameters.add(new BasicNameValuePair("adults", Double.toString(_house.getAdults())));
    		urlParameters.add(new BasicNameValuePair("familyIncome", Double.toString(_house.getFamilyIncome())));
    		urlParameters.add(new BasicNameValuePair("vehicles", Double.toString(_house.getVehicles())));
    		urlParameters.add(new BasicNameValuePair("distanceToStore", Double.toString(_house.getDistanceToStore())));
    	    
    	    if(_house.getVegetarian())
    	    	urlParameters.add(new BasicNameValuePair("vegetarian", "1"));
    	    else
    	    	urlParameters.add(new BasicNameValuePair("vegetarian", "0"));

    	    urlParameters.add(new BasicNameValuePair("children", Double.toString(_house.getChildren())));

    	    post.setEntity(new UrlEncodedFormEntity(urlParameters));
    	    
    	    logger.debug(new UrlEncodedFormEntity(urlParameters).toString());
    	    logger.info("predictionService - connection to REST initilizing");
            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(post)) {

                logger.info(EntityUtils.toString(response.getEntity()));
            }
    		return true;

    	  } catch (MalformedURLException e) {

    		  e.printStackTrace();
    		  logger.error(e.getMessage());

    	  } catch (IOException e) {

    		e.printStackTrace();
    		logger.error(e.getMessage());
    	  }
		return false;
    }
}
