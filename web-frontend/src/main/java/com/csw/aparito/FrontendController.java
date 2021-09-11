package com.csw.aparito;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
public class FrontendController {

	/*
	 * storage for GUI user value
	 */
	private Integer primeLimit;
	
	/**
	 * todo csw
	 * 
	 * @return
	 */
	public String primeNumberClick() {
		System.out.println("Supplied: " + getPrimeLimit());
		return null;
	}

	public Integer getPrimeLimit() {
		return primeLimit;
	}

	public void setPrimeLimit(Integer primeLimit) {
		this.primeLimit = primeLimit;
	}
}
