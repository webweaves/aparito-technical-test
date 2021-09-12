package com.csw.aparito.client;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csw.aparito.FrontendController;

public class PrimeNumbersClient {

	/*
	 * configure logging output
	 */
	Logger logger = LoggerFactory.getLogger(PrimeNumbersClient.class);

	
	private final Client client;
	private final WebTarget target;
	
	private final String baseUrl = "http://localhost:8080/primenumbers-rest-api/resources/generate-prime-numbers/999";
	
	public PrimeNumbersClient() {
		logger.info("Constructed new PrimeNumbersClient");
		
		/*
		 * build the client and define REST endpoint
		 */
		client = ClientBuilder.newClient();
		target = client.target(baseUrl);
	}
	
	public List<Integer> getPrimeNumbers() {

		final String primeNumbersResource = "primeNumbers";
		
		/*		
 		logger.info("Requesting prime numbers from REST endpoint {}", baseUrl + "/" + primeNumbersResource);
		
 		final Response response = target.path(primeNumbersResource).request(MediaType.APPLICATION_JSON_TYPE).get();
		
		List<PrimeNumber> primeNumbers = response.readEntity(new GenericType<List<PrimeNumber>>() {
			
		});
		*/
		//response.readEntity(PrimeNumber)
	
		return Arrays.asList(1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5);
	}
}
