package com.csw.aparito.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PrimeNumbersClient {

	private final Client client;
	private final WebTarget target;
	
	private final String baseUrl = "http://localhost:8080/primenumbers-rest-api/resources/generate-prime-numbers/999";
	
	public PrimeNumbersClient() {
		client = ClientBuilder.newClient();
		target = client.target(baseUrl);
	}
	
	public List<Integer> getPrimeNumbers() {
		final Response response = target.path("primeNumbers").request(MediaType.APPLICATION_JSON_TYPE).get();
		
		List<PrimeNumber> primeNumbers = response.readEntity(new GenericType<List<PrimeNumber>>() {
			
		});
		
		//response.readEntity(PrimeNumber)
	
		return null;
	}
}
