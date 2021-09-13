package com.csw.aparito.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The prime numbers client is what does the work of obtaining the prime numbers
 * 
 * In this instance the numbers are obtained via REST call but this implementation 
 * could be replaced with a completely different data source.
 * 
 * @author csw
 *
 */
public class PrimeNumbersRESTClient implements Serializable {

	private static final long serialVersionUID = 4833775986037364950L;

	/*
	 * configure logging output
	 */
	Logger logger = LoggerFactory.getLogger(PrimeNumbersRESTClient.class);
	
	private Client client;
	
	private WebTarget target;
	
	private String baseUrl = "http://localhost:8080/primenumbers-rest-api/resources/generate-prime-numbers/";
		
	/**
	 * Do the actual REST request
	 * 
	 * @return list of numbers
	 */
	public List<Integer> getPrimeNumbersFromRESTendpoint(final Integer maxPrimeNumber) {
		
		/*
		 * now we know the exact endpoint URL we can build the target client
		 */
		final String finalUrl = baseUrl + maxPrimeNumber;
		
		buildClient(finalUrl);
		
		/*
		 * storage for results
		 */
		List<Integer> primeNumberResults = new ArrayList<>();
		
 		logger.info("Requesting prime numbers from REST endpoint {}", finalUrl);
		
 		/*
 		 * submit the REST request
 		 */
 		try {
 			/*
 			 * actual REST request fired off here
 			 */
 			JsonObject jsonObject = target.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
 			
 			
 			//extract numbers from JSON
 			primeNumberResults = populatePrimeListFromJson(jsonObject);
 			
 			logger.info("{} prime numbers obtained from REST request", primeNumberResults.size());
 			
 			return primeNumberResults;
 			
		} catch (java.lang.Exception e) {
			/*
			 * any errors fail gracefully
			 */
			logger.info("Error with REST request,response = {}", e.getMessage());
	
			return null;
		}
	}

	/**
	 * extract the prime numbers from the JSON object
	 * 
	 * @param jsonObject
	 * @return
	 */
	public List<Integer> populatePrimeListFromJson(JsonObject jsonObject) {
		
		List<Integer> primeNumberResults = new ArrayList<>();
		
		try {
			/*
			 * iterate through results and add to the return list of integers
			 */
			JsonArray jsonArray = (JsonArray)jsonObject.get("primeNumbers");
			for (int idx=0; idx < jsonArray.size(); ++idx) {
				primeNumberResults.add(Integer.valueOf(jsonArray.get(idx).toString())); 
			}
			
			return primeNumberResults;
		} catch (java.lang.NullPointerException e) {
			logger.info("There was a problem parsing the JSON");
			return null;
		}
	}

	/**
	 * Construct the client and target REST endpoint objects
	 * 
	 * @param string
	 */
	public void buildClient(final String finalUrl) {		
		logger.info("Constructing client and target REST endpoint objects");
		
		/*
		 * build the client and define REST endpoint
		 */
		client = ClientBuilder.newClient();
		target = client.target(finalUrl);
	}
}