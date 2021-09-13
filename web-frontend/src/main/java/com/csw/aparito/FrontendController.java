package com.csw.aparito;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csw.aparito.client.PrimeNumbersRESTClient;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@SessionScoped
public class FrontendController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6638320870456419102L;

	/*
	 * configure logging output
	 */
	Logger logger = LoggerFactory.getLogger(FrontendController.class);
		
	/*
	 * create a new prime faces client to interact with REST endpoint
	 */
	@Inject
	private PrimeNumbersRESTClient primeNumbersClient;
	
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
		
		/*
		 * if no numbers returned from REST let the user know there was an issue
		 */
		if (primeNumbers == null) {
			FacesContext.getCurrentInstance().
            	addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
            		"There was an error communicating with REST endpoint, please check server logs", null));
			
			return null;
		}
		
		//set the result if not empty
		setPrimeNumbersResult(primeNumbers);
		
		return null;
	}

	/**
	 * interact with the prime REST API
	 * 
	 * @param maxNumber
	 * @return a list of prime numbers
	 */
	public List<Integer> fetchPrimeNumbers(Integer maxPrimeNumber) {

		/*
		 * get the prime numbers from the client
		 */
		List<Integer> primeNumbers = primeNumbersClient.getPrimeNumbersFromRESTendpoint(maxPrimeNumber);
		
		if (primeNumbers == null) {
			logger.info("Nothing returned from REST API, there is a problem!");
			return null;
		}
		
		logger.info("Retrieved {} prime numbers from PrimeNumbersClient.getPrimeNumbers()", primeNumbers.size());
		
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
