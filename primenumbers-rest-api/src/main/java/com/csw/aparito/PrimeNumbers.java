package com.csw.aparito;

import java.util.List;

/**
 * simple pojo to store prime numbers result
 * 
 * @author csw
 *
 */
public class PrimeNumbers {
	
	private List<Integer> primeNumbers;
	
	public PrimeNumbers(List<Integer> primeNumbers) {
		this.setPrimeNumbers(primeNumbers);
	}

	public List<Integer> getPrimeNumbers() {
		return primeNumbers;
	}

	public void setPrimeNumbers(List<Integer> primeNumbers) {
		this.primeNumbers = primeNumbers;
	}
}
