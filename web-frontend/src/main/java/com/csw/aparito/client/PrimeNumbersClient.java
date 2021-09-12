package com.csw.aparito.client;

import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
public class PrimeNumbersClient {

	/*
	 * configure logging output
	 */
	Logger logger = LoggerFactory.getLogger(PrimeNumbersClient.class);
	
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
 			
 			logger.info("Actual raw response from REST request: {}", jsonObject.get("primeNumbers"));
 			
 			/*
 			 * iterate through results and add to the return list of integers
 			 */
 			JsonArray jsonArray = (JsonArray)jsonObject.get("primeNumbers");
 			for (int idx=0; idx < jsonArray.size(); ++idx) {
 				primeNumberResults.add(new Integer(jsonArray.get(idx).toString())); 
 			}
 			
 			logger.info("{} prime numbers obtained from REST request", primeNumberResults.size());
 			
 			return primeNumberResults;
 			
		} catch (java.lang.Exception e) {
			/*
			 * any errors fail gracefully
			 */
			logger.info("Error with REST request: {}", e.getMessage());
	
			return new ArrayList<>();
		}
	}

	/**
	 * Construct the client and target REST endpoint objects
	 * 
	 * @param string
	 */
	private void buildClient(final String finalUrl) {		
		logger.info("Constructing client and target REST endpoint objects");
		
		/*
		 * build the client and define REST endpoint
		 */
		client = ClientBuilder.newClient();
		target = client.target(finalUrl);
	}
}