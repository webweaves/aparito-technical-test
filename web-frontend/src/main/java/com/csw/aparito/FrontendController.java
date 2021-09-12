package com.csw.aparito;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csw.aparito.client.PrimeNumbersClient;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;

@Named
@SessionScoped
public class FrontendController implements Serializable {

	/*
	 * configure logging output
	 */
	Logger logger = LoggerFactory.getLogger(FrontendController.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * storage for GUI user value
	 */
	private Integer primeLimit;
	
	/*
	 * storage for results size, default to 10
	 */
	private Integer resultsPageSize = 10;
	
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
		logger.info("User supplied value {}", getPrimeLimit());
	
		//fetch the list of prime numbers
		List<Integer> primeNumbers = fetchPrimeNumbers(getPrimeLimit());
		
		//set the result
		setPrimeNumbersResult(primeNumbers);
		
		return "";
	}

	/**
	 * interact with the prime REST API
	 * 
	 * @param maxNumber
	 * @return a list of prime numbers
	 */
	private List<Integer> fetchPrimeNumbers(Integer maxNumber) {
		
		/*
		 * create a new prime faces client to interact with REST endpoint
		 */
		PrimeNumbersClient pnc = new PrimeNumbersClient();
		
		/*
		 * get the prime numbers
		 */
		List<Integer> primeNumbers = pnc.getPrimeNumbers();
		
		logger.debug("Retrieved {} prime numbers from PrimeNumbersClient.getPrimeNumbers()", primeNumbers.size());
		
		return primeNumbers;
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

	public Integer getResultsPageSize() {
		return resultsPageSize;
	}

	public void setResultsPageSize(Integer resultsPageSize) {
		this.resultsPageSize = resultsPageSize;
	}
}
