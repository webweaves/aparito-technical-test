package com.csw.aparito.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.csw.aparito.PrimeNumbers;
import com.csw.aparito.PrimeNumbersController;

@Path("generate-prime-numbers")
@Produces(MediaType.APPLICATION_JSON)
public class PrimeNumbersResource {

	@Inject
	PrimeNumbersController primeNumbersController;
	
	@GET
	@Path("{maxPrimeNumber}")
	/**
	 * REST endpoint to generate a list of prime numbers
	 * 
	 * Access using http://localhost:8080/primenumbers-rest-api/resources/generate-prime-numbers/<maxPrimeNumber>
	 * 
	 * @param maxPrimeNumber
	 * @return List of prime numbers 
	 */
	public PrimeNumbers generatePrimeNumbers(@PathParam("maxPrimeNumber") String maxPrimeNumber) {
		
		//parameter validation, return 0 if parameter parsing fails
		try {
			Integer.parseInt(maxPrimeNumber);
		} catch (java.lang.NumberFormatException e) {
			return null;
		}
		
		/*
		 * generate the prime numbers then send them to a new PrimeNumbers pojo
		 */
		return new PrimeNumbers(primeNumbersController.getPrimeNumbers(Integer.parseInt(maxPrimeNumber)));
	}
}