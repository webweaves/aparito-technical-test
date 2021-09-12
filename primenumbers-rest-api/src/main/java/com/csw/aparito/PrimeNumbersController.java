package com.csw.aparito;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.ejb.Stateless;

/**
 * the main controller to implement prime numbers business logic 
 *  
 * @author csw
 *
 */	
@Stateless
public class PrimeNumbersController {

	/**
	 * I take no credit for the implementation, this is thanks to 
	 * https://www.baeldung.com/java-generate-prime-numbers
	 * 
	 * given an integer then return all prime numbers upto and including 
	 * maxPrimeNumber
	 * 
	 * @param maxPrimeNumber
	 * @return
	 */
	public List<Integer> getPrimeNumbers(int maxPrimeNumber) {
	    return IntStream.rangeClosed(2, maxPrimeNumber)
	        .filter(x -> isPrime(x)).boxed()
	        .collect(Collectors.toList());
	}		
	
	private static boolean isPrime(int number) {
	    return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
	      .allMatch(n -> number % n != 0);
	}
}
