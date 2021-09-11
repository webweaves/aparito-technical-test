package com.csw.aparito;

import javax.inject.Named;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class FrontendController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * storage for GUI user value
	 */
	private Integer primeLimit;
	
	/*
	 * numbers in this list will be presented to the user
	 */
	private List<Integer> primeNumbersResult;
	
	/**
	 * entry point from user button click from GUI
	 * 
	 * @return
	 */
	public String primeNumberClick() {
		System.out.println("Supplied: " + getPrimeLimit());
	
		//fetch the list of prime numbers
		List<Integer> primeNumbers = fetchPrimeNumbers(getPrimeLimit());
		
		//set the result
		setPrimeNumbersResult(primeNumbers);
		
		return null;
	}

	/**
	 * interact with the prime REST API
	 * 
	 * @param maxNumber
	 * @return a list of prime numbers
	 */
	private List<Integer> fetchPrimeNumbers(Integer maxNumber) {
		return Arrays.asList(1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5,1,2,3,5);
	}

	public Integer getPrimeLimit() {
		return primeLimit;
	}

	public void setPrimeLimit(Integer primeLimit) {
		this.primeLimit = primeLimit;
	}

	public List<Integer> getPrimeNumbersResult() {
		return primeNumbersResult;
	}

	public void setPrimeNumbersResult(List<Integer> primeNumbersResult) {
		this.primeNumbersResult = primeNumbersResult;
	}
}
